package beans;

/**
 * Created by wn13 on 2016/2/29.
 * 订单类，用于储存交易记录
 */
public class Order {
    private int oid,otype,uid,onum;
    private double oprice,ototal;
    private String sname,dname,otime;

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public int getOtype() {
        return otype;
    }

    public void setOtype(int otype) {
        this.otype = otype;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getOnum() {
        return onum;
    }

    public void setOnum(int onum) {
        this.onum = onum;
    }

    public double getOprice() {
        return oprice;
    }

    public void setOprice(double oprice) {
        this.oprice = oprice;
    }

    public double getOtotal() {
        return ototal;
    }

    public void setOtotal(double ototal) {
        this.ototal = ototal;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getOtime() {
        return otime;
    }

    public void setOtime(String otime) {
        this.otime = otime;
    }
}
