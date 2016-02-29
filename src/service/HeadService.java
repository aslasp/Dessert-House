package service;

import beans.Dessert;
import beans.Plan;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by wn13 on 2016/2/17.
 */
public interface HeadService {
    ArrayList<Dessert> getAllDesserts();

    /***
     * 添加甜点，注意不仅要操作数据库，还要保存文件
     * @param dessert
     * @param srcFile
     */
    void addDessert(Dessert dessert, File srcFile,String backName);

    /***
     * 添加甜点，这个没有上传图片，所以参数里没有File
     * @param dessert
     */
    void addDessert(Dessert dessert);

    /***
     * 删除甜点，注意删除时应当检查hasImg属性，若为真则需要顺便删除图片
     * @param dname
     */
    void removeDessert(String dname);

    void editDessert(Dessert dessert);

    void editDessert(Dessert dessert,File srcFile,String backName);

    ArrayList<Plan> getAllHistoryPlans();
    ArrayList<Plan> getAllCurrentPlans();

    /***
     * 注意，增加plan的同时要更新account表
     * @param plan
     */
    void addPlan(Plan plan);

    /***
     * 注意，删除plan时要同时更新account表
     * @param pid
     */
    void removePlan(int pid);

    /***
     * 注意，修改plan时要同时更新account表
     * @param plan
     */
    void editPlan(Plan plan);
}
