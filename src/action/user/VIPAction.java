package action.user;

import beans.User;
import com.opensymphony.xwork2.ActionSupport;
import service.UserService;
import service.serviceimpl.UserServiceImpl;

/**
 * Created by wn13 on 2016/2/24.
 */
public class VIPAction extends ActionSupport {
    private User user;
    private double money;
    private double useBonus;
    private UserService service=new UserServiceImpl();
    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    public String recharge(){
        service.recharge(user,money);
        return SUCCESS;
    }

    public String useBonus(){
        if(service.useBonus(user,useBonus)){
            return SUCCESS;
        }else
            return ERROR;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getUseBonus() {
        return useBonus;
    }

    public void setUseBonus(double useBonus) {
        this.useBonus = useBonus;
    }
}
