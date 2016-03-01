package action.user;

import beans.Commodity;
import com.opensymphony.xwork2.ActionSupport;
import dao.SaleDao;
import factory.DaoFactory;
import org.apache.struts2.json.annotations.JSON;

import java.util.ArrayList;

/**
 * Created by wn13 on 2016/3/1.
 */
public class AjaxGetCommodityAction extends ActionSupport {
    private SaleDao saleDao= DaoFactory.getSaleDao();
    private ArrayList<Commodity> commodities;
    private String sname;
    public String ajaxGetCommodity(){
        commodities=new ArrayList<>();
        commodities=saleDao.getCommoditiesInStore(sname);
        return SUCCESS;
    }

    @JSON(serialize = true)
    public ArrayList<Commodity> getCommodities() {
        return commodities;
    }

    public void setCommodities(ArrayList<Commodity> commodities) {
        this.commodities = commodities;
    }

    @JSON(serialize = false)
    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }
}
