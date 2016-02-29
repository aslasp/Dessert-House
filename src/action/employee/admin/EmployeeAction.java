package action.employee.admin;

import beans.Employee;
import com.opensymphony.xwork2.ActionSupport;
import service.AdminService;
import service.serviceimpl.AdminServiceImpl;

/**
 * Created by wn13 on 2016/2/17.
 */
public class EmployeeAction extends ActionSupport {

    private AdminService service=new AdminServiceImpl();
    private Employee employee;

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    public String addEmployee(){
        service.createNewEmployee(employee);
        return SUCCESS;
    }

    public String removeEmployee(){
        service.removeEmployee(employee.getEname());
        return SUCCESS;
    }

    public String editEmployee(){
        service.updateEmployeeInfo(employee);
        return SUCCESS;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
