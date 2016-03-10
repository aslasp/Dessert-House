package dao.daoimpl;

import beans.BonusRecord;
import beans.RechargeRecord;
import beans.User;
import dao.DaoHelper;
import dao.UserDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

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
            stmt=con.prepareStatement("insert into user (uid,uname,upswd,uage,usex,uaddr,ucard,reg_time) values (?,?,?,?,?,?,?,?);");
            stmt.setInt(1,user.getUid());
            stmt.setString(2,user.getUname());
            stmt.setString(3,user.getUpswd());
            stmt.setInt(4,user.getUage());
            stmt.setInt(5,user.getUsex());
            stmt.setString(6,user.getUaddr());
            stmt.setString(7,user.getUcard());
            stmt.setString(8,user.getReg_time());
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
                tmp.setReg_time(rs.getString(14));
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

    @Override
    public void checkStatusWhileLogin(int uid) {
        Connection con=daoHelper.getConnection();
        PreparedStatement stmt=null;
        ResultSet rs=null;
        double ubalance=1.0;
        int ustatus=1;
        String utimeStr="";
        try {
            stmt=con.prepareStatement("select ubalance,ustatus,uactivate_time from user where uid=?;");
            stmt.setInt(1,uid);
            rs=stmt.executeQuery();
            while (rs.next()){
                ubalance=rs.getDouble(1);
                ustatus=rs.getInt(2);
                utimeStr=rs.getString(3);
            }
        }catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            daoHelper.closeResult(rs);
            daoHelper.closePreparedStatement(stmt);
            daoHelper.closeConnection(con);
        }
        //-----------判断是否大于1年------------------------
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal=Calendar.getInstance();
        int between=daysBetween(utimeStr,sdf.format(cal.getTime()));
        if(ustatus==2 && between>=365*2){
            //--------停止其账号---------
            changeUstatus(uid,3);
            return;
        }
        if(between>=365 && ubalance<1.0 && ustatus==1){
            changeUstatus(uid,2);
            return;
        }
    }

    @Override
    public void useBonus(int uid, double use, double bonusNow, double balanceNow) {
        useBonus_user(uid,bonusNow,balanceNow);
        useBonus_bonus(uid,use);
    }

    @Override
    public ArrayList<RechargeRecord> getRechargeHistory(int uid) {
        Connection con = daoHelper.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<RechargeRecord> list = new ArrayList<>();
        try {
            stmt = con.prepareStatement("select * from recharge where uid=? order by rtime desc;");
            stmt.setInt(1,uid);
            rs = stmt.executeQuery();
            while (rs.next()) {
                RechargeRecord tmp = new RechargeRecord();
                tmp.setUid(rs.getInt(1));
                tmp.setRmoney(rs.getDouble(2));
                tmp.setRtime(rs.getString(3));
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
    public ArrayList<BonusRecord> getBonusHistory(int uid) {
        Connection con = daoHelper.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<BonusRecord> list = new ArrayList<>();
        try {
            stmt = con.prepareStatement("select * from use_bonus where uid=? order by utime desc;");
            stmt.setInt(1,uid);
            rs = stmt.executeQuery();
            while (rs.next()) {
                BonusRecord tmp = new BonusRecord();
                tmp.setUid(rs.getInt(1));
                tmp.setUse(rs.getDouble(2));
                tmp.setUseTime(rs.getString(3));
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
    public double getDiscountInfo(int uid) {
        Connection con=daoHelper.getConnection();
        PreparedStatement stmt=null;
        ResultSet rs=null;
        int level=0;
        try {
            stmt=con.prepareStatement("select ulevel from user where uid=?;");
            stmt.setInt(1,uid);
            rs=stmt.executeQuery();
            while (rs.next()){
                level=rs.getInt(1);
            }
        }catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            daoHelper.closeResult(rs);
            daoHelper.closePreparedStatement(stmt);
            daoHelper.closeConnection(con);
        }
        switch (level){
            case 1:return 0.9;
            case 2:return 0.8;
            case 3:return 0.75;
            default:return 1.0;
        }
    }

    @Override
    public ArrayList<User> getAllValidUsers() {
        Connection con = daoHelper.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<User> list = new ArrayList<>();
        try {
            stmt = con.prepareStatement("select * from user where ustatus=1;");
            rs = stmt.executeQuery();
            while (rs.next()) {
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
                tmp.setReg_time(rs.getString(14));
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
    public int countUserNumByAge(int low, int high) {
        Connection con=daoHelper.getConnection();
        PreparedStatement stmt=null;
        ResultSet rs=null;
        int count=0;
        try {
            stmt=con.prepareStatement("select count(*) from user where uage>=? and uage<?;");
            stmt.setInt(1,low);
            stmt.setInt(2,high);
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
    public int countUserNumBySex(int sex) {
        Connection con=daoHelper.getConnection();
        PreparedStatement stmt=null;
        ResultSet rs=null;
        int count=0;
        try {
            stmt=con.prepareStatement("select count(*) from user where usex=?;");
            stmt.setInt(1,sex);
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
    private void useBonus_user(int uid,double bonusNow,double balanceNow){
        Connection con=daoHelper.getConnection();
        PreparedStatement stmt=null;
        try {
            stmt=con.prepareStatement("update user set ubonus=?,ubalance=? where uid=?;");
            stmt.setDouble(1,bonusNow);
            stmt.setDouble(2,balanceNow);
            stmt.setInt(3,uid);
            stmt.executeUpdate();
        }catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            daoHelper.closePreparedStatement(stmt);
            daoHelper.closeConnection(con);
        }
    }

    private void useBonus_bonus(int uid,double use){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal=Calendar.getInstance();
        String date=sdf.format(cal.getTime());
        Connection con=daoHelper.getConnection();
        PreparedStatement stmt=null;
        try {
            stmt=con.prepareStatement("insert into use_bonus (uid,bonus,utime) values (?,?,?);");
            stmt.setInt(1,uid);
            stmt.setDouble(2,use);
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
     * 判断两个日期之间差多少天
     * @param smdate
     * @param bdate
     * @return
     * @throws ParseException
     */
    public static int daysBetween(String smdate,String bdate){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sdf.parse(smdate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long time1 = cal.getTimeInMillis();
        try {
            cal.setTime(sdf.parse(bdate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);

        return Integer.parseInt(String.valueOf(between_days));
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
