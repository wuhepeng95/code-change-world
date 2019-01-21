package overwritetest;

import java.io.IOException;

/**
 * Created by wuhp on 2017/11/3.
 */
public class Test {
    public static void main(String[] args) throws IOException {
        // 成员变量看左边(声明)
        // 成员函数看右边
        // 静态函数 --- 编译运行都看 = 左边。
        Son s = new Son();
        System.out.println(s.str);
        s.method();

        Father f = new Father();
        System.out.println(f.str);
        f.method();

        //Son s = (Son)new Father();无法创建
        Father fs = (Father) new Son();
        System.out.println(fs.str);
        fs.method();
    }
}
