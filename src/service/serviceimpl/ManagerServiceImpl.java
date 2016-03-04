package service.serviceimpl;

import beans.Plan;
import beans.Store;
import beans.StoreStats;
import dao.PlanDao;
import dao.SaleDao;
import dao.StoreDao;
import dao.UserDao;
import factory.DaoFactory;
import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;
import service.ManagerService;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by wn13 on 2016/2/23.
 */
public class ManagerServiceImpl implements ManagerService {
    private PlanDao planDao= DaoFactory.getPlanDao();
    private UserDao userDao=DaoFactory.getUserDao();
    private SaleDao saleDao=DaoFactory.getSaleDao();
    private StoreDao storeDao=DaoFactory.getStoreDao();
    @Override
    public ArrayList<Plan> getPlansToBeApproved() {
        return planDao.getPlansToBeApproved();
    }

    @Override
    public void setPlanStatus(int pid, int pstatus) {
        planDao.setPlanStatus(pid,pstatus);
    }

    @Override
    public Map<String, Object> getStatsMap() {
        Map<String, Object> map = new HashMap<>();
        int userNumAll = userDao.countUserNum();
        //-----0总人数-------------------------
        map.put("userNumAll", userNumAll);
        //-----1各年龄段人数--------------------
        map.put("userNumAgeS20", userDao.countUserNumByAge(0, 20));
        map.put("userNumAgeS30", userDao.countUserNumByAge(20, 30));
        map.put("userNumAgeS40", userDao.countUserNumByAge(30, 40));
        map.put("userNumAgeS50", userDao.countUserNumByAge(40, 50));
        map.put("userNumAgeS70", userDao.countUserNumByAge(50, 70));
        map.put("userNumAgeB70", userDao.countUserNumByAge(70, 200));
        //-----2不同性别人数--------------------
        map.put("userNumMale", userDao.countUserNumBySex(1));
        map.put("userNumFemale", userDao.countUserNumBySex(0));
        //-----3各省份人数----------------------
        //-----4近6个月平均消费金额--------------
        map.put("monthNow", (Calendar.getInstance().get(Calendar.MONTH) + 1));
        ArrayList<Double> sixMonthAvgMoney = new ArrayList<>();
        ArrayList<Double> sixMonthTotalMoney=new ArrayList<>();
        ArrayList<String> sixMonthName=new ArrayList<>();
        //---统计各店情况------------------------
        ArrayList<StoreStats> storeStates = new ArrayList<>();
        ArrayList<Store> stores = storeDao.getAllStores();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        for (int i = 11; i >=0; i--) {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - i);
            String tmp1 = sdf.format(cal.getTime());
            sixMonthName.add(tmp1);
            tmp1 += "-01 00:00:00";
            cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
            String tmp2 = sdf.format(cal.getTime());
            tmp2 += "-01 00:00:00";
            sixMonthTotalMoney.add(new BigDecimal(saleDao.countAllMoneyByDate(tmp1, tmp2)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
            sixMonthAvgMoney.add(new BigDecimal(saleDao.countAllMoneyByDate(tmp1, tmp2) / userNumAll).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
            for (int j = 0; j < stores.size(); j++) {
                storeStates.add(saleDao.reportStats(stores.get(j).getSname(), tmp1, tmp2));
            }
        }
        map.put("sixMonthName",sixMonthName);
        map.put("sixMonthTotalMoney",sixMonthTotalMoney);
        map.put("sixMonthAvgMoney", sixMonthAvgMoney);
        //-----5卡未激活有效暂停停止人数------------
        map.put("inactivatedUserNum", userDao.countInactivatedNum());
        map.put("activatedUserNum", userDao.countActivatedNum());
        map.put("frozenUserNum", userDao.countFrozenNum());
        map.put("invalidUserNum", userDao.countInvalidNum());
        //-----678按店面预订/销售成功数和总额热销产品--------------
        map.put("storeStats", storeStates);
        return map;
    }
}
