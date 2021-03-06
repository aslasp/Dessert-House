package dao;

import beans.Commodity;
import beans.Order;
import beans.Store;
import beans.StoreStats;

import java.util.ArrayList;

/**
 * Created by wn13 on 2016/2/29.
 */
public interface SaleDao {
    ArrayList<Commodity> getCommoditiesInStore(String sname);
    void createNewOrder(Order order,double nb);
    ArrayList<Order> getAllOrders(int uid);
    void cancelOrder(Order order,double nb);
    void updateOrderType(Order order);

    Store findStoreOfBranch(String ename);

    ArrayList<Order> getAllOrdersWithoutUid();

    double countAllMoneyByDate(String date1,String date2);
    StoreStats reportStats(String sname,String date1,String date2);
}
