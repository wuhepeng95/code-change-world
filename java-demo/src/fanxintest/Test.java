package fanxintest;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by wuhp on 2018/1/29
 */
public class Test<T> {
    private Class<T> clazz;
    //= (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    public Test() {
        // 获取当前运行类泛型父类类型，即为参数化类型，有所有类型公用的高级接口Type接收!
        Type type = this.getClass().getGenericSuperclass();
        // 强转为“参数化类型”
        //ParameterizedType参数化类型，即泛型
        ParameterizedType pt = (ParameterizedType) type; // BaseDao
        // 获取参数化类型中，实际类型的定义
        Type[] ts = pt.getActualTypeArguments();
        // 转换
        this.clazz = (Class) ts[0];
    }

    public void method(T t) {
        System.out.println(clazz.getName() + t.toString());
    }

    public static void main(String[] args) {
        new Test1().method("abc");
        new Test2().method(123);
    }
}

class Test1 extends Test<String> {

}

class Test2 extends Test<Integer> {

}
