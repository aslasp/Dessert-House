package action.employee.head;

import beans.Plan;
import com.opensymphony.xwork2.ActionSupport;
import service.HeadService;
import service.serviceimpl.HeadServiceImpl;

/**
 * Created by wn13 on 2016/2/17.
 */
public class PlanAction extends ActionSupport {
    private HeadService service=new HeadServiceImpl();
    private Plan plan;
    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    public String addPlan(){
        plan.setPtime(plan.getPtime()+" 00:00:00");
        service.addPlan(plan);
        return SUCCESS;
    }
    public String removePlan(){
        service.removePlan(plan.getPid());
        return SUCCESS;
    }
    public String editPlan(){
        service.editPlan(plan);
        return SUCCESS;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }
}
