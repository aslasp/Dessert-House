package service.serviceimpl;

import beans.Commodity;
import beans.Order;
import dao.SaleDao;
import factory.DaoFactory;
import service.SaleService;

import java.util.ArrayList;

/**
 * Created by wn13 on 2016/2/29.
 */
public class SaleServiceImpl implements SaleService {
    private SaleDao saleDao= DaoFactory.getSaleDao();
    @Override
    public ArrayList<Commodity> getCommoditiesInStore(String sname) {
        return saleDao.getCommoditiesInStore(sname);
    }

    @Override
    public void createNewOrder(Order order,double ubalance) {
        System.out.println("service:"+order.getOtype());
        System.out.println("service:"+order.getOtime());
        System.out.println("service:"+order.getOtotal());
        saleDao.createNewOrder(order,(ubalance-order.getOtotal()));
    }

    @Override
    public ArrayList<Order> getAllOrders(int uid) {
        return saleDao.getAllOrders(uid);
    }
}
