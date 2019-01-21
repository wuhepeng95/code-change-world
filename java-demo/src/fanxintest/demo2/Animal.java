package fanxintest.demo2;

import java.lang.reflect.ParameterizedType;

/**
 * Created by wuhp on 2018/3/29
 */
public class Animal<T> {

    public T fanxin;

    private Class<T> entityClazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    String getT() {
        return entityClazz.getName();
    }
}
