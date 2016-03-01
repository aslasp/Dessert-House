package action.user;

import beans.Order;
import com.opensymphony.xwork2.ActionSupport;
import service.CookieService;
import service.SaleService;
import service.serviceimpl.CookieServiceImpl;
import service.serviceimpl.SaleServiceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by wn13 on 2016/2/24.
 * 历史订单
 */
public class OrderAction extends ActionSupport {
    private Order order;
    private double ubalance;
    private SaleService saleService=new SaleServiceImpl();
    private CookieService cookieService=new CookieServiceImpl();
    @Override
    public String execute() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        String date = sdf.format(cal.getTime())+" 00:00:00.0";
        ArrayList<Order> olist=saleService.getAllOrders(Integer.parseInt(cookieService.getCookie("uid")));
        for(int i=0;i<olist.size();i++){
            Order tmp=olist.get(i);
            if(tmp.getOtype()==0 && tmp.getOtime().equals(date)){
                tmp.setOtype(2);
                saleService.updateOrderType(tmp);
            }
        }
        return SUCCESS;
    }

    public String cancelOrder(){
        order.setOtype(3);
        saleService.cancelOrder(order,ubalance);
        return SUCCESS;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public double getUbalance() {
        return ubalance;
    }

    public void setUbalance(double ubalance) {
        this.ubalance = ubalance;
    }
}
