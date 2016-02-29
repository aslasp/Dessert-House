package service;

import beans.Employee;

/**
 * Created by wn13 on 2016/2/15.
 */
public interface EmployeeService {

    boolean login(Employee employee);
    int getEmployeeType(String ename);
}
