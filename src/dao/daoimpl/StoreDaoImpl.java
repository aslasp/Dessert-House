package dao.daoimpl;

import beans.Store;
import dao.DaoHelper;
import dao.StoreDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by wn13 on 2016/2/15.
 */
public class StoreDaoImpl implements StoreDao {
    private static DaoHelper daoHelper=DaoHelperImpl.getBaseDaoInstance();
    private static StoreDaoImpl storeDao=new StoreDaoImpl();

    private StoreDaoImpl(){}

    public static StoreDaoImpl getInstance(){return storeDao;}


    @Override
    public void createNew(Store store) {
        Connection con=daoHelper.getConnection();
        PreparedStatement stmt=null;
        try {
            stmt=con.prepareStatement("insert into store (sname,saddr) values (?,?);");
            stmt.setString(1,store.getSname());
            stmt.setString(2,store.getSaddr());
            stmt.executeUpdate();
        }catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            daoHelper.closePreparedStatement(stmt);
            daoHelper.closeConnection(con);
        }
    }

    @Override
    public ArrayList<Store> getAllStores() {
        Connection con=daoHelper.getConnection();
        PreparedStatement stmt=null;
        ResultSet rs=null;
        ArrayList<Store> list=new ArrayList<>();
        try {
            stmt=con.prepareStatement("select * from store;");
            rs=stmt.executeQuery();
            while (rs.next()){
                Store tmp=new Store();
                tmp.setSname(rs.getString(1));
                tmp.setSaddr(rs.getString(2));
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
    public void removeStore(String sname) {
        removeStoreInfo(sname);
        removeAccount(sname);
        removePlans(sname);
    }

    @Override
    public void updateStore(Store store) {
        Connection con=daoHelper.getConnection();
        PreparedStatement stmt=null;
        try {
            stmt=con.prepareStatement("update store set saddr=? where sname=?;");
            stmt.setString(1,store.getSaddr());
            stmt.setString(2,store.getSname());
            stmt.executeUpdate();
        }catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            daoHelper.closePreparedStatement(stmt);
            daoHelper.closeConnection(con);
        }
    }

    private void removeStoreInfo(String sname){
        Connection con=daoHelper.getConnection();
        PreparedStatement stmt=null;
        try {
            stmt=con.prepareStatement("delete from store where sname=?;");
            stmt.setString(1,sname);
            stmt.executeUpdate();
        }catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            daoHelper.closePreparedStatement(stmt);
            daoHelper.closeConnection(con);
        }
    }

    private void removePlans(String sname){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH,-7);
        Date startDate=cal.getTime();
        String dateStr=sdf.format(startDate);

        Connection con=daoHelper.getConnection();
        PreparedStatement stmt=null;
        try {
            stmt=con.prepareStatement("delete from plan where sname=? and ptime>=?;");
            stmt.setString(1,sname);
            stmt.setString(2,dateStr);
            stmt.executeUpdate();
        }catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            daoHelper.closePreparedStatement(stmt);
            daoHelper.closeConnection(con);
        }
    }

    private void removeAccount(String sname){
        Connection con=daoHelper.getConnection();
        PreparedStatement stmt=null;
        try {
            stmt=con.prepareStatement("delete from account where account.pid in(select * from(select plan.pid from plan where plan.sname='"+sname+"') as tmp);");
            stmt.executeUpdate();
        }catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            daoHelper.closePreparedStatement(stmt);
            daoHelper.closeConnection(con);
        }
    }
}
