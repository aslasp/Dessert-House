package dao;

import beans.Store;

import java.util.ArrayList;

/**
 * Created by wn13 on 2016/2/15.
 */
public interface StoreDao {

    void createNew(Store store);

    ArrayList<Store> getAllStores();

    /***
     * 删除分店，注意：删除此店时会将最近的销售计划同时删除
     * @param sname
     */
    void removeStore(String sname);

    void updateStore(Store store);
}
