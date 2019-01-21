package proxydynamic;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuhp on 2017/11/2.
 */
public class Client {
    public static void main(String[] args) {

        HireHouse hhi = new HireHouseImpl();
        hhi.hire();//不使用代理
        //创建代理对象
        HireHouse hh = (HireHouse) Proxy.newProxyInstance(
                //获取被代理对象的类加载器
                hhi.getClass().getClassLoader(),
                //获取被代理对象的全部接口
                hhi.getClass().getInterfaces(),
                //自定义代理类的对象
                new MyProxy(hhi));
        hh.hire();//使用代理

        //List被代理 所有方法都可用
        List lsi = new ArrayList();
        //创建代理对象
        List ls = (List) Proxy.newProxyInstance(
                //获取被代理对象的类加载器
                lsi.getClass().getClassLoader(),
                //获取被代理对象的全部接口
                lsi.getClass().getInterfaces(),
                //自定义代理类的对象
                new MyProxy(lsi));
        //这样的话，就会走代理了 invoke方法，反射调用方法
        ls.add("HelloProxy");
        ls.get(0);
    }
}
