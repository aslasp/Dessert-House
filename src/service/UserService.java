package service;

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
}
