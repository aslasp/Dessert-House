package dao;

import beans.User;

import java.util.ArrayList;

/**
 * Created by wn13 on 2016/2/13.
 */
public interface UserDao {

    /***
     * 向user表中插入一个新用户
     * @param user
     */
    void createNew(User user);

    /***
     * 查找User表中符合条件的所有User结果
     * @param col
     * @param value
     * @return
     */
    ArrayList<User> find(String col, Object value);

    /***
     * 根据uid更新user个人资料，包括：uname,uage,usex,uaddr,ucard
     * @param user
     */
    void updateProfileById(User user);

    /***
     * 根据uid更新指定的几项记录
     * @param uid
     * @param value
     * @param col
     */
    void updateById(int uid, ArrayList<Object> value, ArrayList<String> col);

    /***
     * 统计用户总数（包括未激活、已激活、暂停、停止的所有用户）
     * @return
     */
    int countUserNum();

    /***
     * 统计处于“已激活”状态的用户总数
     * @return
     */
    int countActivatedNum();

    /***
     * 统计处于“未激活”状态的用户总数
     * @return
     */
    int countInactivatedNum();

    /***
     * 统计处于“暂停”状态的用户总数
     * @return
     */
    int countFrozenNum();

    /***
     * 统计处于“停止”状态的用户总数
     * @return
     */
    int countInvalidNum();

    /***
     * 检查用户ID和密码是否匹配，若匹配返回1，否则返回0
     * @param uid
     * @param pswd
     * @return
     */
    int loginCheck(int uid,String pswd);

    void changePassword(int uid,String npswd);

    void changeUstatus(int uid,int ustatus);

    void updateRechargeTable(int uid,double money,String date);

    void checkStatusWhileLogin(int uid);
}
