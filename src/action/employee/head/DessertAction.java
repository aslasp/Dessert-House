package action.employee.head;

import beans.Dessert;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import service.HeadService;
import service.serviceimpl.HeadServiceImpl;

import java.io.File;

/**
 * Created by wn13 on 2016/2/18.
 */
public class DessertAction extends ActionSupport {
    private Dessert dessert;
    private File img;
    private String imgContentType;
    private HeadService service=new HeadServiceImpl();
    private int newImgUploaded;
    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    public String addDessert(){
        if(dessert.getHasImg()==0)
            service.addDessert(dessert);
        else{
            String backName="."+imgContentType.substring(imgContentType.indexOf('/')+1);
            service.addDessert(dessert,img,backName);
        }

        return SUCCESS;
    }

    public String removeDessert(){
        service.removeDessert(dessert.getDname());
        return SUCCESS;
    }

    public String editDessert(){
        if(newImgUploaded==1) {
            String backName="."+imgContentType.substring(imgContentType.indexOf('/')+1);
            dessert.setHasImg(1);
            service.editDessert(dessert, img, backName);
        }
        else
            service.editDessert(dessert);
        return SUCCESS;
    }

    public Dessert getDessert() {
        return dessert;
    }

    public void setDessert(Dessert dessert) {
        this.dessert = dessert;
    }

    public File getImg() {
        return img;
    }

    public void setImg(File img) {
        this.img = img;
    }

    public String getImgContentType() {
        return imgContentType;
    }

    public int getNewImgUploaded() {
        return newImgUploaded;
    }

    public void setNewImgUploaded(int newImgUploaded) {
        this.newImgUploaded = newImgUploaded;
    }

    public void setImgContentType(String imgContentType) {
        this.imgContentType = imgContentType;
    }
}
