package dao;

import beans.Commodity;
import beans.Order;

import java.util.ArrayList;

/**
 * Created by wn13 on 2016/2/29.
 */
public interface SaleDao {
    ArrayList<Commodity> getCommoditiesInStore(String sname);
    void createNewOrder(Order order,double nb);
    ArrayList<Order> getAllOrders(int uid);
}