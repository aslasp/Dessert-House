package action.employee;

import beans.Employee;
import com.opensymphony.xwork2.ActionSupport;
import service.CookieService;
import service.EmployeeService;
import service.serviceimpl.CookieServiceImpl;
import service.serviceimpl.EmployeeServiceImpl;

/**
 * Created by wn13 on 2016/2/15.
 */
public class LoginAction extends ActionSupport {
    private Employee employee;
    private EmployeeService service=new EmployeeServiceImpl();
    private CookieService cookie=new CookieServiceImpl();

    @Override
    public String execute() throws Exception {
        if(service.login(employee)){
            //-------该写这里啦！保存Cookie!(灵感，不妨把首页的cookie跳转改为点击按钮跳转)
            String etype=String.valueOf(service.getEmployeeType(employee.getEname()));
            cookie.addCookie("ename",employee.getEname());
            cookie.addCookie("etype",etype);
            return etype;
        }else{
            return ERROR;
        }
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
