package lamdbatest;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @Desc: 四大常用函数式接口 Supplier 、Consumer、Predicate、Function
 * @Author: hepeng.wu@going-link.com
 * @Date: 2021-12-01 10:51
 */
public class FunctionTest {

    public static void main(String[] args) {
        System.out.println(null + "&");

// ---------------------------------------Supplier供给型接口--------------------------------------

        //无输入参数，返回T类型的一个结果。
        new Supplier<String>() {
            @Override
            public String get() {
                return null;
            }
        };
        Supplier<String> supplier = () -> "Test supplier";
        System.out.println(supplier.get());

// ---------------------------------------Consumer消费型接口--------------------------------------

        //接受一个T类型的参数，无返回。
        new Consumer<String>() {

            @Override
            public void accept(String s) {

            }

            @Override
            public Consumer<String> andThen(Consumer<? super String> after) {
                return null;
            }
        };
        Consumer<String> consumer = (x) -> {
            System.out.println(x);
        };
        //Consumer<String> consumer = System.out::println;
        consumer.accept("Test consumer"); //void | 控制台打印 "Test consumer"

// ---------------------------------------Predicate断言型接口--------------------------------------

        //接受一个T类型的参数，返回布尔值。
        new Predicate<String>() {

            @Override
            public boolean test(String s) {
                return false;
            }

            @Override
            public Predicate<String> and(Predicate<? super String> other) {
                return null;
            }

            @Override
            public Predicate<String> negate() {
                return null;
            }

            @Override
            public Predicate<String> or(Predicate<? super String> other) {
                return null;
            }
        };
        Predicate<String> predicate = x -> x.contains("predicate");
        System.out.println(predicate.test("Test predicate"));


// ----------------------------------------Function函数式接口-------------------------------------

        //接受一个T类型的参数，返回R类型结果。
        new Function<String, Integer>() {

            @Override
            public Integer apply(String s) {
                return null;
            }

            @Override
            public <V> Function<V, Integer> compose(Function<? super V, ? extends String> before) {
                return null;
            }

            @Override
            public <V> Function<String, V> andThen(Function<? super Integer, ? extends V> after) {
                return null;
            }
        };
        Function<Integer, String> function = x -> "This is Integer:" + x;
        System.out.println(function.apply(100));

// -----------------------------------------------------------------------------
    }
}
