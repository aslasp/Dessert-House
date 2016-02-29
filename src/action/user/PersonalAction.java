package action.user;

import beans.User;
import com.opensymphony.xwork2.ActionSupport;
import service.CookieService;
import service.UserService;
import service.serviceimpl.CookieServiceImpl;
import service.serviceimpl.UserServiceImpl;

/**
 * Created by wn13 on 2016/2/24.
 * 未完成！！！！需要给change开头的方法调用service！
 */
public class PersonalAction extends ActionSupport {
    private User user;
    private int uid;
    private String opswd;
    private String npswd;
    private UserService service=new UserServiceImpl();
    private CookieService cookieService=new CookieServiceImpl();

    public String executeProfile(){
        return SUCCESS;
    }
    public String executePassword(){
        return SUCCESS;
    }
    public String changePassword(){
        if(service.changePassword(uid,opswd,npswd)){
            return SUCCESS;
        }
        else
            return ERROR;
    }
    public String changeProfile(){
        service.changeProfile(user);
        return SUCCESS;
    }

    public String destory(){
        service.destoryUser(uid);
        return SUCCESS;
    }



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getOpswd() {
        return opswd;
    }

    public void setOpswd(String opswd) {
        this.opswd = opswd;
    }

    public String getNpswd() {
        return npswd;
    }

    public void setNpswd(String npswd) {
        this.npswd = npswd;
    }
}
