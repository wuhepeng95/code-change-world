package proxydynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by wuhp on 2017/11/2.
 */
//动态代理必须继承InvocationHandler接口，实现invoke方法（反射）
public class MyProxy implements InvocationHandler {

    private Object obj;

    public MyProxy(Object obj) {
        this.obj = obj;
    }

    /**
     * jdk动态代理,代理的对象必须实现接口
     * method是被代理对象的接口方法
     * args是被代理接口方法的参数
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("前置工作");
        if (args != null) {
            for (Object obj : args) {
                System.out.println(obj);
            }
        }
        Object invoke = method.invoke(obj, args);
        System.out.println("后置工作");
        return invoke;
    }
}
