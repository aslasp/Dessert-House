package service.serviceimpl;

import beans.Dessert;
import beans.Plan;
import com.opensymphony.xwork2.ActionContext;
import dao.DessertDao;
import dao.PlanDao;
import factory.DaoFactory;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import service.HeadService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by wn13 on 2016/2/18.
 */
public class HeadServiceImpl implements HeadService {
    private DessertDao dessertDao= DaoFactory.getDessertDao();
    private PlanDao planDao=DaoFactory.getPlanDao();
    @Override
    public ArrayList<Dessert> getAllDesserts() {
        return dessertDao.getAllDesserts();
    }

    @Override
    public void addDessert(Dessert dessert, File srcFile,String backName) {
        dessertDao.addDessert(dessert);
        File destFile=new File("/dhImg/",dessert.getDname()+backName);
        try {
            FileUtils.copyFile(srcFile,destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addDessert(Dessert dessert) {
        dessertDao.addDessert(dessert);
    }

    @Override
    public void removeDessert(String dname) {
        dessertDao.removeDessert(dname);
//        File file=new File("E:/dessertImg/",dname+".jpeg");
//        if (file.exists()){
//            file.delete();
//        }

    }

    @Override
    public void editDessert(Dessert dessert) {
        dessertDao.editDessert(dessert);
    }

    @Override
    public void editDessert(Dessert dessert, File srcFile,String backName) {
        dessertDao.editDessert(dessert);
        File destFile=new File("/dhImg/",dessert.getDname()+backName);
        try {
            FileUtils.copyFile(srcFile,destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Plan> getAllHistoryPlans() {
        return planDao.getAllHistoryPlans();
    }

    @Override
    public ArrayList<Plan> getAllCurrentPlans() {
        return planDao.getAllCurrentPlans();
    }

    @Override
    public void addPlan(Plan plan) {
        planDao.addPlan(plan);
    }

    @Override
    public void removePlan(int pid) {
        planDao.removePlan(pid);
    }

    @Override
    public void editPlan(Plan plan) {
        planDao.editPlan(plan);
    }
}
