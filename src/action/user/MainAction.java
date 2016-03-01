package action.user;

import beans.Order;
import com.opensymphony.xwork2.ActionSupport;
import service.SaleService;
import service.serviceimpl.SaleServiceImpl;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by wn13 on 2016/2/14.
 */
public class MainAction extends ActionSupport {
    private SaleService saleService = new SaleServiceImpl();
    private Order order;
    private double ubalance;

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    public String buyNow() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        String date = sdf.format(cal.getTime());
        order.setOtime(date);
        order.setOtype(1);
        saleService.createNewOrder(order, ubalance);
        return SUCCESS;

    }

    public String orderNow() {
        order.setOtime(order.getOtime()+" 00:00:00");
        order.setOtype(0);
        saleService.createNewOrder(order,ubalance);
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
