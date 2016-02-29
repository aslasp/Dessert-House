package beans;

/**
 * Created by wn13 on 2016/2/15.
 */
public class Employee {
    private String ename,epswd,sname;
    private int etype;

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getEpswd() {
        return epswd;
    }

    public void setEpswd(String epswd) {
        this.epswd = epswd;
    }

    public int getEtype() {
        return etype;
    }

    public void setEtype(int etype) {
        this.etype = etype;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }
}
