package dao.daoimpl;

import beans.Commodity;
import beans.Dessert;
import beans.Order;
import beans.Plan;
import dao.DaoHelper;
import dao.SaleDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by wn13 on 2016/2/29.
 */
public class SaleDaoImpl implements SaleDao {
    private static DaoHelper daoHelper = DaoHelperImpl.getBaseDaoInstance();
    private static SaleDaoImpl saleDao=new SaleDaoImpl();
    private SaleDaoImpl(){}
    public static SaleDaoImpl getInstance(){
        return saleDao;
    }
    @Override
    public ArrayList<Commodity> getCommoditiesInStore(String sname) {
        ArrayList<Plan> plans=getCurrentPlansOfStore(sname);
        ArrayList<Commodity> list=new ArrayList<>();
        for(int i=0;i<plans.size();i++){
            Plan tmpPlan=plans.get(i);
            int numRemain=tmpPlan.getNum_limit()-getSoldNum(tmpPlan.getPid());
            if(numRemain==0){
                continue;
            }else{
                Dessert tmpDessert=findDessert(tmpPlan.getDname());
                Commodity tmpCommodity=new Commodity();
                tmpCommodity.setDname(tmpPlan.getDname());
                tmpCommodity.setdIntro(tmpDessert.getDintro());
                tmpCommodity.setPrice(tmpPlan.getPrice());
                tmpCommodity.setRemainNum(numRemain);
                tmpCommodity.setSname(sname);
                tmpCommodity.setHasImg(tmpDessert.getHasImg());
                list.add(tmpCommodity);
            }
        }
        return list;
    }

    @Override
    public void createNewOrder(Order order,double nb) {
        updateUserBalance(order.getUid(),nb);
        updateAccount(order.getOnum(),order.getDname(),order.getSname());
        Connection con = daoHelper.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("insert into orders (otype,sname,dname,uid,oprice,onum,ototal,otime) values (?,?,?,?,?,?,?,?);");
            stmt.setInt(1, order.getOtype());
            stmt.setString(2,order.getSname());
            stmt.setString(3,order.getDname());
            stmt.setInt(4, order.getUid());
            stmt.setDouble(5,order.getOprice());
            stmt.setInt(6,order.getOnum());
            stmt.setDouble(7,order.getOtotal());
            stmt.setString(8,order.getOtime());
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
    public ArrayList<Order> getAllOrders(int uid) {
        Connection con = daoHelper.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Order> list = new ArrayList<>();
        try {
            stmt = con.prepareStatement("select * from orders where uid=? order by otype asc,otime desc;");
            stmt.setInt(1,uid);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Order tmp = new Order();
                tmp.setOid(rs.getInt(1));
                tmp.setOtype(rs.getInt(2));
                tmp.setSname(rs.getString(3));
                tmp.setDname(rs.getString(4));
                tmp.setUid(rs.getInt(5));
                tmp.setOprice(rs.getDouble(6));
                tmp.setOnum(rs.getInt(7));
                tmp.setOtotal(rs.getDouble(8));
                tmp.setOtime(rs.getString(9));
                list.add(tmp);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            daoHelper.closeResult(rs);
            daoHelper.closePreparedStatement(stmt);
            daoHelper.closeConnection(con);
        }
        return list;
    }

    @Override
    public void updateOrderType(Order order) {
        Connection con = daoHelper.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("update orders set otype=? where oid=?;");
            stmt.setInt(1, order.getOtype());
            stmt.setInt(2, order.getOid());
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
    public void cancelOrder(Order order,double nb) {
        updateUserBalance(order.getUid(),nb);
        updateOrderType(order);
    }

    private ArrayList<Plan> getCurrentPlansOfStore(String sname){
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
            stmt=con.prepareStatement("select * from plan where ptime>=? and pstatus=2 and sname=?;");
            stmt.setString(1,dateStr);
            stmt.setString(2,sname);
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
    private int getSoldNum(int pid){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal=Calendar.getInstance();
        String date=sdf.format(cal.getTime());
        date=date+" 00:00:00";
        Connection con=daoHelper.getConnection();
        PreparedStatement stmt=null;
        ResultSet rs=null;
        int soldNum=0;
        try {
            stmt=con.prepareStatement("select asold from account where pid=? and adate=?;");
            stmt.setInt(1,pid);
            stmt.setString(2,date);
            rs=stmt.executeQuery();
            while (rs.next()){
                soldNum=rs.getInt(1);
            }
        }catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            daoHelper.closeResult(rs);
            daoHelper.closePreparedStatement(stmt);
            daoHelper.closeConnection(con);
        }
        return soldNum;
    }
    private Dessert findDessert(String dname){
        Connection con=daoHelper.getConnection();
        PreparedStatement stmt=null;
        ResultSet rs=null;
        Dessert dessert=new Dessert();
        try {
            stmt=con.prepareStatement("select * from dessert where dname=?;");
            stmt.setString(1,dname);
            rs=stmt.executeQuery();
            while (rs.next()){
                dessert.setDname(rs.getString(1));
                dessert.setDintro(rs.getString(2));
                dessert.setHasImg(rs.getInt(3));
            }
        }catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            daoHelper.closeResult(rs);
            daoHelper.closePreparedStatement(stmt);
            daoHelper.closeConnection(con);
        }
        return dessert;
    }

    private void updateUserBalance(int uid,double ub){
        Connection con = daoHelper.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("update user set ubalance=? where uid=?;");
            stmt.setDouble(1, ub);
            stmt.setInt(2, uid);
            stmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            daoHelper.closePreparedStatement(stmt);
            daoHelper.closeConnection(con);
        }
    }

    private void updateAccount(int num,String dname,String sname){
        int pid=findPid(dname,sname);
        int soldnum=getSoldNum(pid)+num;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal=Calendar.getInstance();
        String date=sdf.format(cal.getTime());
        date=date+" 00:00:00";
        Connection con = daoHelper.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("update account set asold=? where adate=? and pid=?;");
            stmt.setInt(1, soldnum);
            stmt.setString(2,date);
            stmt.setInt(3,pid);
            stmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            daoHelper.closePreparedStatement(stmt);
            daoHelper.closeConnection(con);
        }
    }
    private int findPid(String dname,String sname){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH,-7);
        Date startDate=cal.getTime();
        String dateStr=sdf.format(startDate);
        Connection con=daoHelper.getConnection();
        PreparedStatement stmt=null;
        ResultSet rs=null;
        int pid=-1;
        try {
            stmt=con.prepareStatement("select pid from plan where dname=? and sname=? and ptime>=?;");
            stmt.setString(1,dname);
            stmt.setString(2,sname);
            stmt.setString(3,dateStr);
            rs=stmt.executeQuery();
            while (rs.next()){
                pid=rs.getInt(1);
            }
        }catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            daoHelper.closeResult(rs);
            daoHelper.closePreparedStatement(stmt);
            daoHelper.closeConnection(con);
        }
        return pid;
    }

}
