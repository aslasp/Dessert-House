package beans;

/**
 * Created by wn13 on 2016/2/13.
 */
public class User {
    private int uid,uage,usex,ustatus,ulevel;
    private String uname,upswd,uaddr,ucard,uactivate_time,reg_time;
    private double ubonus,ubalance,utotal_recharge;

    public int getUage() {
        return uage;
    }

    public void setUage(int uage) {
        this.uage = uage;
    }

    public int getUsex() {
        return usex;
    }

    public void setUsex(int usex) {
        this.usex = usex;
    }

    public int getUstatus() {
        return ustatus;
    }

    public void setUstatus(int ustatus) {
        this.ustatus = ustatus;
    }

    public int getUlevel() {
        return ulevel;
    }

    public void setUlevel(int ulevel) {
        this.ulevel = ulevel;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpswd() {
        return upswd;
    }

    public void setUpswd(String upswd) {
        this.upswd = upswd;
    }

    public String getUaddr() {
        return uaddr;
    }

    public void setUaddr(String uaddr) {
        this.uaddr = uaddr;
    }

    public String getUcard() {
        return ucard;
    }

    public void setUcard(String ucard) {
        this.ucard = ucard;
    }

    public String getUactivate_time() {
        return uactivate_time;
    }

    public void setUactivate_time(String uactivate_time) {
        this.uactivate_time = uactivate_time;
    }

    public double getUbonus() {
        return ubonus;
    }

    public void setUbonus(double ubonus) {
        this.ubonus = ubonus;
    }

    public double getUbalance() {
        return ubalance;
    }

    public void setUbalance(double ubalance) {
        this.ubalance = ubalance;
    }

    public double getUtotal_recharge() {
        return utotal_recharge;
    }

    public void setUtotal_recharge(double utotal_recharge) {
        this.utotal_recharge = utotal_recharge;
    }

    public String getReg_time() {
        return reg_time;
    }

    public void setReg_time(String reg_time) {
        this.reg_time = reg_time;
    }
}
