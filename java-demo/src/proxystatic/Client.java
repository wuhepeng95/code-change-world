package proxystatic;

import proxydynamic.MyInvocationHandler;

/**
 * Created by wuhp on 2017/11/2.
 */
public class Client {
    public static void main(String[] args) {
        //自己去租房，不使用代理
        HireHouse hh = new HireHouseImpl();
        hh.hire();
        //使用代理
        HireHouse php = (HireHouse) new MyInvocationHandler(new HireHouseImpl());
        php.hire();
        //创建接口的实现类
    }
}
