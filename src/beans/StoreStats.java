package beans;

import java.util.ArrayList;

/**
 * Created by wn13 on 2016/3/3.
 */
public class StoreStats {
    private String sname;
    private ArrayList<String> top3;
    private int month,orderNum,sellNum;
    private double orderMoney,sellMoney;

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public ArrayList<String> getTop3() {
        return top3;
    }

    public void setTop3(ArrayList<String> top3) {
        this.top3 = top3;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public int getSellNum() {
        return sellNum;
    }

    public void setSellNum(int sellNum) {
        this.sellNum = sellNum;
    }

    public double getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(double orderMoney) {
        this.orderMoney = orderMoney;
    }

    public double getSellMoney() {
        return sellMoney;
    }

    public void setSellMoney(double sellMoney) {
        this.sellMoney = sellMoney;
    }
}
