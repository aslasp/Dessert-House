package service.serviceimpl;

import beans.Employee;
import beans.Store;
import dao.EmployeeDao;
import dao.StoreDao;
import factory.DaoFactory;
import service.AdminService;

import java.util.ArrayList;

/**
 * Created by wn13 on 2016/2/16.
 */
public class AdminServiceImpl implements AdminService {
    private StoreDao storeDao= DaoFactory.getStoreDao();
    private EmployeeDao employeeDao=DaoFactory.getEmployeeDao();

    @Override
    public ArrayList<Store> getAllStores() {
        return storeDao.getAllStores();
    }

    @Override
    public void createNewStore(Store store) {
        storeDao.createNew(store);
    }

    @Override
    public void removeStore(String sname) {
        storeDao.removeStore(sname);
    }

    @Override
    public void updateStoreInfo(Store store) {
        storeDao.updateStore(store);
    }

    @Override
    public void createNewEmployee(Employee employee) {
        employeeDao.createNew(employee);
    }

    @Override
    public void removeEmployee(String ename) {
        employeeDao.removeEmployee(ename);
    }

    @Override
    public void updateEmployeeInfo(Employee employee) {
        employeeDao.updateEmployee(employee);
    }

    @Override
    public ArrayList<Employee> getAllEmployee() {
        return employeeDao.getAllEmployee();
    }
}
