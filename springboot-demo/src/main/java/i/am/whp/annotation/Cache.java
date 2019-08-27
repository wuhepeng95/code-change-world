package i.am.whp.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wuhepeng
 * @date 2019/8/27
 */
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Cache {
    // 缓存的key
    String keyName() default "";

    // 过期时间 默认1分钟(60s)
    int expireTime() default 60;
}
