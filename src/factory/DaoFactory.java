package factory;

import dao.*;
import dao.daoimpl.*;

/**
 * Created by wn13 on 2016/2/13.
 */
public class DaoFactory {

    public static UserDao getUserDao() {
        return UserDaoImpl.getInstance();
    }

    public static EmployeeDao getEmployeeDao() {
        return EmployeeDaoImpl.getInstance();
    }

    public static StoreDao getStoreDao() {
        return StoreDaoImpl.getInstance();
    }

    public static DessertDao getDessertDao(){
        return DessertDaoImpl.getInstance();
    }

    public static PlanDao getPlanDao(){
        return PlanDaoImpl.getInstance();
    }
}
