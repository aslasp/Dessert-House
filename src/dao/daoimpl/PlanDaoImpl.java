package dao.daoimpl;

import beans.Plan;
import dao.DaoHelper;
import dao.PlanDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by wn13 on 2016/2/18.
 */
public class PlanDaoImpl implements PlanDao {
    private static PlanDaoImpl planDao=new PlanDaoImpl();
    private static DaoHelper daoHelper = DaoHelperImpl.getBaseDaoInstance();
    private PlanDaoImpl(){}
    public static PlanDaoImpl getInstance(){
        return planDao;
    }

    @Override
    public void addPlan(Plan plan) {
        writeAddPlan(plan);
        int pid=-1;
        ArrayList<Plan> plans=findPlan(plan.getPtime(),plan.getSname(),plan.getDname());
        if (plans.size()>0) {
            pid = plans.get(0).getPid();
        }
        else{
            System.out.println("增加plan时，获取pid出错");
        }
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate=null;
        Calendar cal=Calendar.getInstance();
        try {
            startDate=sdf.parse(plan.getPtime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for(int i=0;i<7;i++){
            cal.setTime(startDate);
            cal.set(Calendar.DATE,cal.get(Calendar.DATE)+i);
            writeAddAccount(pid,sdf.format(cal.getTime()));
        }

    }

    @Override
    public void removePlan(int pid) {
        writeDelPlan(pid);
        writeDelAccount(pid);
    }

    @Override
    public void editPlan(Plan plan) {
        Connection con = daoHelper.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("update plan set price=?,num_limit=?,pstatus=? where pid=?;");
            stmt.setDouble(1, plan.getPrice());
            stmt.setInt(2,plan.getNum_limit());
            stmt.setInt(3,0);
            stmt.setInt(4,plan.getPid());
            stmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            daoHelper.closePreparedStatement(stmt);
            daoHelper.closeConnection(con);
        }
    }

    @Override
    public ArrayList<Plan> getAllHistoryPlans() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH,-7);
        Date startDate=cal.getTime();
        String dateStr=sdf.format(startDate);

        Connection con=daoHelper.getConnection();
        PreparedStatement stmt=null;
        ResultSet rs=null;
        ArrayList<Plan> list=new ArrayList<>();
        try {
            stmt=con.prepareStatement("select * from plan where ptime<? order by pstatus;");
            stmt.setString(1,dateStr);
            rs=stmt.executeQuery();
            while (rs.next()){
                Plan tmp=new Plan();
                tmp.setPid(rs.getInt(1));
                tmp.setPtime(rs.getString(2));
                tmp.setDname(rs.getString(3));
                tmp.setSname(rs.getString(4));
                tmp.setPrice(rs.getDouble(5));
                tmp.setNum_limit(rs.getInt(6));
                tmp.setPstatus(rs.getInt(7));
                list.add(tmp);
            }
        }catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            daoHelper.closeResult(rs);
            daoHelper.closePreparedStatement(stmt);
            daoHelper.closeConnection(con);
        }
        return list;
    }

    @Override
    public ArrayList<Plan> getAllCurrentPlans() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH,-7);
        Date startDate=cal.getTime();
        String dateStr=sdf.format(startDate);

        Connection con=daoHelper.getConnection();
        PreparedStatement stmt=null;
        ResultSet rs=null;
        ArrayList<Plan> list=new ArrayList<>();
        try {
            stmt=con.prepareStatement("select * from plan where ptime>=? order by pstatus;");
            stmt.setString(1,dateStr);
            rs=stmt.executeQuery();
            while (rs.next()){
                Plan tmp=new Plan();
                tmp.setPid(rs.getInt(1));
                tmp.setPtime(rs.getString(2));
                tmp.setDname(rs.getString(3));
                tmp.setSname(rs.getString(4));
                tmp.setPrice(rs.getDouble(5));
                tmp.setNum_limit(rs.getInt(6));
                tmp.setPstatus(rs.getInt(7));
                list.add(tmp);
            }
        }catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            daoHelper.closeResult(rs);
            daoHelper.closePreparedStatement(stmt);
            daoHelper.closeConnection(con);
        }
        return list;
    }

    @Override
    public ArrayList<Plan> getPlansToBeApproved() {
        ArrayList<Plan> current=getAllCurrentPlans();
        ArrayList<Plan> list=new ArrayList<>();
        for(int i=0;i<current.size();i++){
            Plan tmp=current.get(i);
            if(tmp.getPstatus()==0){
                list.add(tmp);
            }
        }
        return list;
    }

    @Override
    public void setPlanStatus(int pid, int pstatus) {
        Connection con = daoHelper.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("update plan set pstatus=? where pid=?;");
            stmt.setInt(1, pstatus);
            stmt.setInt(2,pid);
            stmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            daoHelper.closePreparedStatement(stmt);
            daoHelper.closeConnection(con);
        }
    }

    private void writeAddPlan(Plan plan){
        Connection con=daoHelper.getConnection();
        PreparedStatement stmt=null;
        try {
            stmt=con.prepareStatement("insert into plan (ptime,dname,sname,price,num_limit,pstatus) values (?,?,?,?,?,?);");
            stmt.setString(1,plan.getPtime());
            stmt.setString(2,plan.getDname());
            stmt.setString(3,plan.getSname());
            stmt.setDouble(4,plan.getPrice());
            stmt.setInt(5,plan.getNum_limit());
            stmt.setInt(6,0);
            stmt.executeUpdate();
        }catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            daoHelper.closePreparedStatement(stmt);
            daoHelper.closeConnection(con);
        }
    }
    private void writeAddAccount(int pid,String adate){
        Connection con=daoHelper.getConnection();
        PreparedStatement stmt=null;
        try {
            stmt=con.prepareStatement("insert into account (pid,adate) values (?,?);");
            stmt.setInt(1,pid);
            stmt.setString(2,adate);
            stmt.executeUpdate();
        }catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            daoHelper.closePreparedStatement(stmt);
            daoHelper.closeConnection(con);
        }
    }
    private void writeDelPlan(int pid){
        Connection con=daoHelper.getConnection();
        PreparedStatement stmt=null;
        try {
            stmt=con.prepareStatement("delete from plan where pid=?;");
            stmt.setInt(1,pid);
            stmt.executeUpdate();
        }catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            daoHelper.closePreparedStatement(stmt);
            daoHelper.closeConnection(con);
        }
    }
    private void writeDelAccount(int pid){
        Connection con=daoHelper.getConnection();
        PreparedStatement stmt=null;
        try {
            stmt=con.prepareStatement("delete from account where pid=?;");
            stmt.setInt(1,pid);
            stmt.executeUpdate();
        }catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            daoHelper.closePreparedStatement(stmt);
            daoHelper.closeConnection(con);
        }
    }

    private ArrayList<Plan> findPlan(String ptime,String sname,String dname){
        Connection con=daoHelper.getConnection();
        PreparedStatement stmt=null;
        ResultSet rs=null;
        ArrayList<Plan> list=new ArrayList<>();
        try {
            stmt=con.prepareStatement("select * from plan where ptime=? and sname=? and dname=?;");
            stmt.setString(1,ptime);
            stmt.setString(2,sname);
            stmt.setString(3,dname);
            rs=stmt.executeQuery();
            while (rs.next()){
                Plan tmp=new Plan();
                tmp.setPid(rs.getInt(1));
                tmp.setPtime(rs.getString(2));
                tmp.setDname(rs.getString(3));
                tmp.setSname(rs.getString(4));
                tmp.setPrice(rs.getDouble(5));
                tmp.setNum_limit(rs.getInt(6));
                tmp.setPstatus(rs.getInt(7));
                list.add(tmp);
            }
        }catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            daoHelper.closeResult(rs);
            daoHelper.closePreparedStatement(stmt);
            daoHelper.closeConnection(con);
        }
        return list;
    }
}
