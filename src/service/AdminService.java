package service;

import beans.Employee;
import beans.Store;

import java.util.ArrayList;

/**
 * Created by wn13 on 2016/2/16.
 */
public interface AdminService {

    ArrayList<Store> getAllStores();

    void createNewStore(Store store);

    void removeStore(String sname);

    void updateStoreInfo(Store store);

    void createNewEmployee(Employee employee);

    void removeEmployee(String ename);

    void updateEmployeeInfo(Employee employee);

    ArrayList<Employee> getAllEmployee();
}
