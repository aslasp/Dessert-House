package dao;

import beans.Plan;

import java.util.ArrayList;

/**
 * Created by wn13 on 2016/2/18.
 */
public interface PlanDao {
    /***
     * 不要忘了除了plan表，还要增加account表
     * @param plan
     */
    void addPlan(Plan plan);
    /***
     * 不要忘了除了plan表，还要删除account表
     * @param pid
     */
    void removePlan(int pid);
    /***
     * 不要忘了除了plan表，还要修改account表
     * @param plan
     */
    void editPlan(Plan plan);
    ArrayList<Plan> getAllHistoryPlans();

    /***
     * 获得从7天前开始到未来的所有计划
     * @return
     */
    ArrayList<Plan> getAllCurrentPlans();

    /***
     * 获得从7天前开始到未来仍未审批的计划
     * @return
     */
    ArrayList<Plan> getPlansToBeApproved();

    void setPlanStatus(int pid,int pstatus);
}
