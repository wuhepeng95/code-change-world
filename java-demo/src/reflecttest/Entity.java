package reflecttest;

/**
 * Created by wuhp on 2017/11/2.
 */
public class Entity {
    private String str;
    private Integer i;

    public Entity() {
    }

    public Entity(String str, Integer i) {
        this.str = str;
        this.i = i;
    }

    public String getStr() {

        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}