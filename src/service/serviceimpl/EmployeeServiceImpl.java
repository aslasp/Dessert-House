package service.serviceimpl;

import beans.Employee;
import dao.EmployeeDao;
import factory.DaoFactory;
import service.EmployeeService;

/**
 * Created by wn13 on 2016/2/15.
 */
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeDao employeeDao= DaoFactory.getEmployeeDao();
    @Override
    public boolean login(Employee employee) {
        return (employeeDao.loginCheck(employee.getEname(),employee.getEpswd())==1);
    }

    @Override
    public int getEmployeeType(String ename) {
        return employeeDao.getEmployeeType(ename);
    }
}
