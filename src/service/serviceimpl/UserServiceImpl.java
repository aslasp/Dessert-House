package service.serviceimpl;

import beans.BonusRecord;
import beans.RechargeRecord;
import beans.User;
import dao.UserDao;
import factory.DaoFactory;
import service.UserService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by wn13 on 2016/2/13.
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = DaoFactory.getUserDao();

    @Override
    public void register(User user) {
        user.setUid(generateUid());
        userDao.createNew(user);
    }

    @Override
    public boolean login(User user) {
        return (userDao.loginCheck(user.getUid(), user.getUpswd()) == 1);
    }

    @Override
    public ArrayList<User> findUser(String col, Object value) {
        return userDao.find(col, value);
    }

    @Override
    public boolean changePassword(int uid, String opswd, String npswd) {
        if (userDao.loginCheck(uid, opswd) == 1) {
            userDao.changePassword(uid, npswd);
            return true;
        }
        return false;
    }

    @Override
    public void changeProfile(User user) {
        userDao.updateProfileById(user);
    }

    @Override
    public void destoryUser(int uid) {
        userDao.changeUstatus(uid, 3);
    }

    @Override
    public void recharge(User user, double money) {
        ArrayList<String> col = new ArrayList<>();
        ArrayList<Object> val = new ArrayList<>();
        //----step1,更新uactivate_time------
        col.add("uactivate_time");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        String timeNow = sdf.format(now);
        val.add(timeNow);
        //----step2,增加积分-----------------
        col.add("ubonus");
        val.add(user.getUbonus() + money);
        //----step3,增加总充值钱数,判断等级---
        double utotal_recharge = user.getUtotal_recharge() + money;
        col.add("utotal_recharge");
        val.add(utotal_recharge);
        if (utotal_recharge >= 0 && utotal_recharge < 1000) {
            col.add("ulevel");
            val.add(1);
        }
        if (utotal_recharge >= 1000 && utotal_recharge < 2000) {
            col.add("ulevel");
            val.add(2);
        }
        if (utotal_recharge >= 2000) {
            col.add("ulevel");
            val.add(3);
        }
        //------step4,激活------------
        if (user.getUstatus() == 0 && money >= 200.0) {
            col.add("ustatus");
            val.add(1);
        }
        if (user.getUstatus() == 2 && money > 0) {
            col.add("ustatus");
            val.add(1);
        }
        //------step5，增加余额---------
        col.add("ubalance");
        val.add(user.getUbalance() + money);

        //------step6,更新user表和recharge表--
        userDao.updateById(user.getUid(), val, col);
        userDao.updateRechargeTable(user.getUid(), money, timeNow);
    }

    @Override
    public void checkStatus(int uid) {
        userDao.checkStatusWhileLogin(uid);
    }

    @Override
    public boolean useBonus(User user, double useBonus) {
        if (user.getUbonus() < useBonus)
            return false;
        else {
            userDao.useBonus(user.getUid(),useBonus,(user.getUbonus()-useBonus),(user.getUbalance()+(useBonus/100.0)));
            return true;
        }
    }

    @Override
    public ArrayList<RechargeRecord> getRechargeHistory(int uid) {
        return userDao.getRechargeHistory(uid);
    }

    @Override
    public ArrayList<BonusRecord> getBonusHistory(int uid) {
        return userDao.getBonusHistory(uid);
    }

    private int generateUid() {
        return 1000000 + userDao.countUserNum();
    }
}
