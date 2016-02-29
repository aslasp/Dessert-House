package dao.daoimpl;

import beans.Employee;
import dao.DaoHelper;
import dao.EmployeeDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by wn13 on 2016/2/15.
 */
public class EmployeeDaoImpl implements EmployeeDao{
    private static EmployeeDaoImpl employeeDao=new EmployeeDaoImpl();
    private static DaoHelper daoHelper=DaoHelperImpl.getBaseDaoInstance();

    private EmployeeDaoImpl(){}

    public static EmployeeDaoImpl getInstance(){
        return employeeDao;
    }

    @Override
    public void createNew(Employee employee) {
        Connection con=daoHelper.getConnection();
        PreparedStatement stmt=null;
        try {
            stmt=con.prepareStatement("insert into employee (ename,epswd,etype,sname) values (?,?,?,?);");
            stmt.setString(1,employee.getEname());
            stmt.setString(2,employee.getEpswd());
            stmt.setInt(3,employee.getEtype());
            stmt.setString(4,employee.getSname());
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
    public void removeEmployee(String ename) {
        Connection con=daoHelper.getConnection();
        PreparedStatement stmt=null;
        try {
            stmt=con.prepareStatement("delete from employee where ename=?;");
            stmt.setString(1,ename);
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
    public void updateByName(String ename, ArrayList<Object> value, ArrayList<String> col) {
        Connection con=daoHelper.getConnection();
        PreparedStatement stmt=null;
        try {
            int size=col.size()-1;
            String str="update employee set ";
            for(int i=0;i<size;i++){
                str=str+col.get(i)+"=?,";
            }
            str=str+col.get(size)+"=? where ename=?;";
            stmt=con.prepareStatement(str);
            for(int i=1;i<=col.size();i++){
                stmt.setObject(i,value.get(i));
            }
            stmt.setString(col.size()+1,ename);
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
    public void updateEmployee(Employee employee) {
        Connection con=daoHelper.getConnection();
        PreparedStatement stmt=null;
        try {
            stmt=con.prepareStatement("update employee set epswd=?,etype=?,sname=? where ename=?;");
            stmt.setString(1,employee.getEpswd());
            stmt.setInt(2,employee.getEtype());
            stmt.setString(3,employee.getSname());
            stmt.setString(4,employee.getEname());
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
    public int loginCheck(String ename, String epswd) {
        Connection con=daoHelper.getConnection();
        PreparedStatement stmt=null;
        ResultSet rs=null;
        int count=0;
        try {
            stmt=con.prepareStatement("select count(*) from employee where ename=? and epswd=?;");
            stmt.setString(1,ename);
            stmt.setString(2,epswd);
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
    public int getEmployeeType(String ename) {
        Connection con=daoHelper.getConnection();
        PreparedStatement stmt=null;
        ResultSet rs=null;
        int etype=0;
        try {
            stmt=con.prepareStatement("select etype from employee where ename=?;");
            stmt.setString(1,ename);
            rs=stmt.executeQuery();
            while (rs.next()){
                etype=rs.getInt(1);
            }
        }catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            daoHelper.closeResult(rs);
            daoHelper.closePreparedStatement(stmt);
            daoHelper.closeConnection(con);
        }
        return etype;
    }

    @Override
    public ArrayList<Employee> getAllEmployee() {
        Connection con=daoHelper.getConnection();
        PreparedStatement stmt=null;
        ResultSet rs=null;
        ArrayList<Employee> list=new ArrayList<>();
        try {
            stmt=con.prepareStatement("select * from employee order by etype,sname;");
            rs=stmt.executeQuery();
            while (rs.next()){
                Employee tmp=new Employee();
                tmp.setEname(rs.getString(1));
                tmp.setEpswd(rs.getString(2));
                tmp.setEtype(rs.getInt(3));
                tmp.setSname(rs.getString(4));
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
