import java.util.Date;

/**
 * Description TODO
 *
 * @author zhaolaiyuan
 * @version 1.0
 * @Date 2019年2019/11/21 8:57
 **/
public class Domo implements Cloneable{
    private String aaa;
    private Date date;
    private String ccc;

    public String getAaa() {
        System.out.println("1");
        return aaa;
    }

    public void setAaa(String aaa) {
        this.aaa = aaa;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    protected Domo clone() throws CloneNotSupportedException {
        Domo d = (Domo) super.clone();
        d.ccc = null;
        return d;
    }

    public String getCcc() {
        return ccc;
    }

    public void setCcc(String ccc) {
        this.ccc = ccc;
    }

    @Override
    public String toString() {
        return "Domo{" +
                "aaa='" + aaa + '\'' +
                ", date=" + date +
                ", ccc='" + ccc + '\'' +
                '}';
    }
}
