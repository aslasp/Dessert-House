package action.employee.admin;

import beans.Store;
import com.opensymphony.xwork2.ActionSupport;
import service.AdminService;
import service.serviceimpl.AdminServiceImpl;

/**
 * Created by wn13 on 2016/2/15.
 */
public class StoreAction extends ActionSupport {
    private AdminService service=new AdminServiceImpl();
    private Store store;

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    public String executeRemoveStore(){
        service.removeStore(store.getSname());
        return SUCCESS;
    }

    public String editStore(){
        service.updateStoreInfo(store);
        return SUCCESS;
    }

    public String addStore(){
        service.createNewStore(store);
        return SUCCESS;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
