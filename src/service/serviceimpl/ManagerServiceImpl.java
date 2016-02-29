package service.serviceimpl;

import beans.Plan;
import dao.PlanDao;
import factory.DaoFactory;
import service.ManagerService;

import java.util.ArrayList;

/**
 * Created by wn13 on 2016/2/23.
 */
public class ManagerServiceImpl implements ManagerService {
    private PlanDao planDao= DaoFactory.getPlanDao();
    @Override
    public ArrayList<Plan> getPlansToBeApproved() {
        return planDao.getPlansToBeApproved();
    }

    @Override
    public void setPlanStatus(int pid, int pstatus) {
        planDao.setPlanStatus(pid,pstatus);
    }
}
