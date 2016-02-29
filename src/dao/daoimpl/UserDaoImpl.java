package dao.daoimpl;

import beans.User;
import dao.DaoHelper;
import dao.UserDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by wn13 on 2016/2/13.
 */
public class UserDaoImpl implements UserDao {


    private static UserDaoImpl userDao=new UserDaoImpl();
    private static DaoHelper daoHelper=DaoHelperImpl.getBaseDaoInstance();

    private UserDaoImpl(){}

    public static UserDaoImpl getInstance(){
        return userDao;
    }

    @Override
    public void createNew(User user) {
        Connection con=daoHelper.getConnection();
        PreparedStatement stmt=null;
        try {
            stmt=con.prepareStatement("insert into user (uid,uname,upswd,uage,usex,uaddr,ucard) values (?,?,?,?,?,?,?);");
            stmt.setInt(1,user.getUid());
            stmt.setString(2,user.getUname());
            stmt.setString(3,user.getUpswd());
            stmt.setInt(4,user.getUage());
            stmt.setInt(5,user.getUsex());
            stmt.setString(6,user.getUaddr());
            stmt.setString(7,user.getUcard());
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
    public ArrayList<User> find(String col, Object value) {
        Connection con=daoHelper.getConnection();
        PreparedStatement stmt=null;
        ResultSet rs=null;
        ArrayList<User> list=new ArrayList<>();
        try {
            stmt=con.prepareStatement("select * from user WHERE "+col+"=?;");
            stmt.setObject(1,value);
            rs=stmt.executeQuery();
            while (rs.next()){
                User tmp=new User();
                tmp.setUid(rs.getInt(1));
                tmp.setUname(rs.getString(2));
                tmp.setUpswd(rs.getString(3));
                tmp.setUage(rs.getInt(4));
                tmp.setUsex(rs.getInt(5));
                tmp.setUaddr(rs.getString(6));
                tmp.setUcard(rs.getString(7));
                tmp.setUbalance(rs.getDouble(8));
                tmp.setUtotal_recharge(rs.getDouble(9));
                tmp.setUlevel(rs.getInt(10));
                tmp.setUbonus(rs.getDouble(11));
                tmp.setUstatus(rs.getInt(12));
                tmp.setUactivate_time(rs.getString(13));
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
    public void updateProfileById(User user) {
        Connection con=daoHelper.getConnection();
        PreparedStatement stmt=null;
        try {
            stmt=con.prepareStatement("update user set uname=?,uage=?,uaddr=?,ucard=? where uid=?;");
            stmt.setString(1,user.getUname());
            stmt.setInt(2,user.getUage());
            stmt.setString(3,user.getUaddr());
            stmt.setString(4,user.getUcard());
            stmt.setInt(5,user.getUid());
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
    public void updateById(int uid,ArrayList<Object> value, ArrayList<String> col) {
        Connection con=daoHelper.getConnection();
        PreparedStatement stmt=null;
        try {
            int size=col.size()-1;
            String str="update user set ";
            for(int i=0;i<size;i++){
                str=str+col.get(i)+"=?,";
            }
            str=str+col.get(size)+"=? where uid=?;";
            stmt=con.prepareStatement(str);
            for(int i=1;i<=col.size();i++){
                stmt.setObject(i,value.get(i-1));
            }
            stmt.setInt(col.size()+1,uid);
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
    public int countUserNum() {
        Connection con=daoHelper.getConnection();
        PreparedStatement stmt=null;
        ResultSet rs=null;
        int count=0;
        try {
            stmt=con.prepareStatement("select count(*) from user;");
            rs=stmt.executeQuery();
            while (rs.next()){
                count=rs.getInt(1);
            }
        }catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            daoHelper.closeResult(rs);
            daoHelper.closePreparedStatement(stmt);
            daoHelper.closeConnection(con);
        }
        return count;
    }

    @Override
    public int countActivatedNum() {
        return countSpecificNum(1);
    }

    @Override
    public int countInactivatedNum() {
        return countSpecificNum(0);
    }

    @Override
    public int countFrozenNum() {
        return countSpecificNum(2);
    }

    @Override
    public int countInvalidNum() {
        return countSpecificNum(3);
    }

    @Override
    public int loginCheck(int uid, String pswd) {
        Connection con=daoHelper.getConnection();
        PreparedStatement stmt=null;
        ResultSet rs=null;
        int count=0;
        try {
            stmt=con.prepareStatement("select count(*) from user where ustatus<>3 and uid=? and upswd=?;");
            stmt.setInt(1,uid);
            stmt.setString(2,pswd);
            rs=stmt.executeQuery();
            while (rs.next()){
                count=rs.getInt(1);
            }
        }catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            daoHelper.closeResult(rs);
            daoHelper.closePreparedStatement(stmt);
            daoHelper.closeConnection(con);
        }
        return count;
    }

    @Override
    public void changePassword(int uid, String npswd) {
        Connection con=daoHelper.getConnection();
        PreparedStatement stmt=null;
        try {
            stmt=con.prepareStatement("update user set upswd=? where uid=?;");
            stmt.setString(1,npswd);
            stmt.setInt(2,uid);
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
    public void changeUstatus(int uid, int ustatus) {
        Connection con=daoHelper.getConnection();
        PreparedStatement stmt=null;
        try {
            stmt=con.prepareStatement("update user set ustatus=? where uid=?;");
            stmt.setInt(1,ustatus);
            stmt.setInt(2,uid);
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
    public void updateRechargeTable(int uid, double money, String date) {
        Connection con=daoHelper.getConnection();
        PreparedStatement stmt=null;
        try {
            stmt=con.prepareStatement("insert into recharge (uid,rmoney,rtime) values (?,?,?);");
            stmt.setInt(1,uid);
            stmt.setDouble(2,money);
            stmt.setString(3,date);
            stmt.executeUpdate();
        }catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            daoHelper.closePreparedStatement(stmt);
            daoHelper.closeConnection(con);
        }
    }

    /***
     * 根据请求的状态获得用户数量
     * 参数值说明 0未激活 1激活 2暂停 3停止
     * @param ustatus
     * @return
     */
    private int countSpecificNum(int ustatus){
        Connection con=daoHelper.getConnection();
        PreparedStatement stmt=null;
        ResultSet rs=null;
        int count=0;
        try {
            stmt=con.prepareStatement("select count(*) from user where ustatus=?;");
            stmt.setInt(1,ustatus);
            rs=stmt.executeQuery();
            while (rs.next()){
                count=rs.getInt(1);
            }
        }catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            daoHelper.closeResult(rs);
            daoHelper.closePreparedStatement(stmt);
            daoHelper.closeConnection(con);
        }
        return count;
    }
}
