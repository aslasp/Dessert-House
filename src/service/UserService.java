package service;

import beans.BonusRecord;
import beans.RechargeRecord;
import beans.User;

import java.util.ArrayList;

/**
 * Created by wn13 on 2016/2/13.
 */
public interface UserService {
    /***
     * 注册新用户
     * @param user
     */
    void register(User user);

    /***
     * 用户登录，返回true表示登录成功
     * @param user
     * @return
     */
    boolean login(User user);

    /***
     * 传入某列的一个值，寻找符合条件的所有用户
     * @param col
     * @param value
     * @return
     */
    ArrayList<User> findUser(String col,Object value);

    boolean changePassword(int uid,String opswd,String npswd);
    void changeProfile(User user);
    void destoryUser(int uid);
    void recharge(User user,double money);

    /***
     * 登录时，检查用户最近一次激活时间，若距今1年并且目前余额小于1.0则状态变为暂停/停止
     * @param uid
     */
    void checkStatus(int uid);

    boolean useBonus(User user,double useBonus);

    ArrayList<RechargeRecord> getRechargeHistory(int uid);
    ArrayList<BonusRecord> getBonusHistory(int uid);
}
