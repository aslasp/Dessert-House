package dao.daoimpl;

import beans.Dessert;
import dao.DaoHelper;
import dao.DessertDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by wn13 on 2016/2/18.
 */
public class DessertDaoImpl implements DessertDao {
    private static DaoHelper daoHelper = DaoHelperImpl.getBaseDaoInstance();
    private static DessertDaoImpl dessertDao = new DessertDaoImpl();

    private DessertDaoImpl() {
    }

    public static DessertDaoImpl getInstance() {
        return dessertDao;
    }

    @Override
    public void addDessert(Dessert dessert) {
        Connection con = daoHelper.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("insert into dessert (dname,dintro,hasImg) values (?,?,?);");
            stmt.setString(1, dessert.getDname());
            stmt.setString(2, dessert.getDintro());
            stmt.setInt(3, dessert.getHasImg());
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
    public void removeDessert(String dname) {
        writeDelDessert(dname);
        writeDelAccount(dname);
        writeDelPlan(dname);

    }

    @Override
    public void editDessert(Dessert dessert) {
        Connection con = daoHelper.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("update dessert set dintro=?,hasImg=? where dname=?;");
            stmt.setString(1, dessert.getDintro());
            stmt.setInt(2, dessert.getHasImg());
            stmt.setString(3, dessert.getDname());
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
    public ArrayList<Dessert> getAllDesserts() {
        Connection con = daoHelper.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Dessert> list = new ArrayList<>();
        try {
            stmt = con.prepareStatement("select * from dessert;");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Dessert tmp = new Dessert();
                tmp.setDname(rs.getString(1));
                tmp.setDintro(rs.getString(2));
                tmp.setHasImg(rs.getInt(3));
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

    private void writeDelDessert(String dname){
        Connection con = daoHelper.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("delete from dessert where dname=?;");
            stmt.setString(1, dname);
            stmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            daoHelper.closePreparedStatement(stmt);
            daoHelper.closeConnection(con);
        }
    }

    private void writeDelAccount(String dname){
        Connection con = daoHelper.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("delete from account where account.pid in (select * from(select plan.pid from plan where plan.dname='"+dname+"') as tmp);");
            stmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            daoHelper.closePreparedStatement(stmt);
            daoHelper.closeConnection(con);
        }
    }

    private void writeDelPlan(String dname){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH,-7);
        Date startDate=cal.getTime();
        String dateStr=sdf.format(startDate);

        Connection con=daoHelper.getConnection();
        PreparedStatement stmt=null;
        try {
            stmt=con.prepareStatement("delete from plan where dname=? and ptime>=?;");
            stmt.setString(1,dname);
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
}
