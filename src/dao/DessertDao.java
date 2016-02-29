package dao;

import beans.Dessert;

import java.util.ArrayList;

/**
 * Created by wn13 on 2016/2/18.
 */
public interface DessertDao {
    void addDessert(Dessert dessert);

    /***
     * 删除甜点时，要顺便删除近期销售计划
     * @param dname
     */
    void removeDessert(String dname);
    void editDessert(Dessert dessert);
    ArrayList<Dessert> getAllDesserts();
}
