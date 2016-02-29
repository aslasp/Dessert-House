package service;

import beans.Plan;

import java.util.ArrayList;

/**
 * Created by wn13 on 2016/2/23.
 */
public interface ManagerService {
    ArrayList<Plan> getPlansToBeApproved();
    void setPlanStatus(int pid,int pstatus);
}
