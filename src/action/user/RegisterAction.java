package action.user;

import beans.User;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import service.CookieService;
import service.UserService;
import service.serviceimpl.CookieServiceImpl;
import service.serviceimpl.UserServiceImpl;

import java.io.IOException;

/**
 * Created by wn13 on 2016/2/13.
 */
public class RegisterAction extends ActionSupport {
    private User user;
    private UserService service=new UserServiceImpl();
    private CookieService cookie=new CookieServiceImpl();

    @Override
    public String execute(){
        String method = ServletActionContext.getRequest().getMethod();
        boolean isPostMethod = "POST".equalsIgnoreCase(method);
        if(isPostMethod){
            service.register(user);
            cookie.addCookie("uname",user.getUname());
            cookie.addCookie("uid",String.valueOf(user.getUid()));
            return SUCCESS;
        }else{
//            try {
//                ServletActionContext.getResponse().sendRedirect("/index.html");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
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
