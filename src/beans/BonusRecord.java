package beans;

/**
 * Created by wn13 on 2016/2/29.
 */
public class BonusRecord {
    private int uid;
    private double use;
    private String useTime;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUseTime() {
        return useTime;
    }

    public void setUseTime(String useTime) {
        this.useTime = useTime;
    }

    public double getUse() {
        return use;
    }

    public void setUse(double use) {
        this.use = use;
    }
}
