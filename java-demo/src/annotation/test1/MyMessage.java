package annotation.test1;

import java.lang.annotation.*;

/**
 * @author sam
 * @since 2017/7/13
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface MyMessage {

    String name() default "default name";

    int num() default 110;

    String desc() default "default desc";
}