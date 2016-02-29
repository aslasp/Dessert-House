package action.employee.manager;

import com.opensymphony.xwork2.ActionSupport;
import service.ManagerService;
import service.serviceimpl.ManagerServiceImpl;

/**
 * Created by wn13 on 2016/2/23.
 */
public class ApprovalAction extends ActionSupport {
    private int pid;
    private int pstatus;
    private ManagerService service=new ManagerServiceImpl();
    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    public String setStatus(){
        service.setPlanStatus(pid,pstatus);
        return SUCCESS;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getPstatus() {
        return pstatus;
    }

    public void setPstatus(int pstatus) {
        this.pstatus = pstatus;
    }
}
