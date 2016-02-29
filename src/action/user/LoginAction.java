package action.user;

import beans.User;
import com.opensymphony.xwork2.ActionSupport;
import service.CookieService;
import service.UserService;
import service.serviceimpl.CookieServiceImpl;
import service.serviceimpl.UserServiceImpl;

/**
 * Created by wn13 on 2016/2/14.
 */
public class LoginAction extends ActionSupport {
    private UserService service=new UserServiceImpl();
    private CookieService cookie=new CookieServiceImpl();
    private User user;

    @Override
    public String execute() throws Exception {
        if(service.login(user)){
            cookie.addCookie("uname",service.findUser("uid",user.getUid()).get(0).getUname());
            cookie.addCookie("uid",String.valueOf(user.getUid()));
            return SUCCESS;
        }else{
            return ERROR;
        }

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
