package beans;

/**
 * Created by wn13 on 2016/2/29.
 * 商品类，用于预订、销售时商品的列举
 */
public class Commodity {
    private String dname,sname,dIntro;
    private int remainNum,hasImg;
    private double price;

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getdIntro() {
        return dIntro;
    }

    public void setdIntro(String dIntro) {
        this.dIntro = dIntro;
    }

    public int getRemainNum() {
        return remainNum;
    }

    public void setRemainNum(int remainNum) {
        this.remainNum = remainNum;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getHasImg() {
        return hasImg;
    }

    public void setHasImg(int hasImg) {
        this.hasImg = hasImg;
    }
}
