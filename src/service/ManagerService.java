package service;

import beans.Plan;

import java.util.ArrayList;

/**
 * Created by wn13 on 2016/2/23.
 */
public interface ManagerService {
    ArrayList<Plan> getPlansToBeApproved();
    void setPlanStatus(int pid,int pstatus);

    /***
     * 获得统计数据的Json字符串
     * 包含--每个月--
     * 0 user总人数
     * 1 user各年龄段人数（20-,30-,40-,50-,50+）
     * 2 user不同性别人数
     * 3 user各省份人数*
     * 4 平均消费金额
     * 5 卡未激活/有效/暂停/停止人数
     * 6 按店面，预订成功数，总额
     * 7 按店面，销售成功数，总额
     * 8 热卖产品前3名
     * @return
     */
    String getStatsJson();



}
