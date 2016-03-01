package service;

import beans.Commodity;
import beans.Order;

import java.util.ArrayList;

/**
 * Created by wn13 on 2016/2/29.
 */
public interface SaleService {
    ArrayList<Commodity> getCommoditiesInStore(String sname);
    void createNewOrder(Order order,double ubalance);
    ArrayList<Order> getAllOrders(int uid);
    void updateOrderType(Order order);
    void cancelOrder(Order order,double ubalance);
}
