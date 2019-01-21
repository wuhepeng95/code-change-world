package proxystatic;

/**
 * Created by wuhp on 2017/11/2.
 */
public class HireHouseProxy implements HireHouse {
    private HireHouse hh;

    public HireHouseProxy(HireHouse hh) {
        super();
        this.hh = hh;
    }

    @Override
    public void hire() {
        System.out.println("收取中介费");
        hh.hire();
        System.out.println("完成租房！");
    }

}
