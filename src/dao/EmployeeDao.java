package dao;

import beans.Employee;

import java.util.ArrayList;

/**
 * Created by wn13 on 2016/2/15.
 */
public interface EmployeeDao {

    /***
     * 注册新员工
     * @param employee
     */
    void createNew(Employee employee);

    /***
     * 根据名字删除员工
     * @param ename
     */
    void removeEmployee(String ename);

    /***
     * 根据名字更新指定项的信息
     * @param ename
     * @param value
     * @param col
     */
    void updateByName(String ename, ArrayList<Object> value, ArrayList<String> col);

    void updateEmployee(Employee employee);

    /***
     * 登录检查，返回1表示登录成功
     * @param ename
     * @param epswd
     * @return
     */
    int loginCheck(String ename,String epswd);

    /***
     * 获取员工类型
     * 0系统管理员 1经理 2总店服务员(headSeller) 3分店服务员(branchSeller)
     * @param ename
     * @return
     */
    int getEmployeeType(String ename);

    ArrayList<Employee> getAllEmployee();

//    int countAllEmployee();
//
//    int countAdminNum();
//
//    int countManagerNum();
//
//    int countHeadSellerNum();
//
//    int countBranchSellerNum();
}
