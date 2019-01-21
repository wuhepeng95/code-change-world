package callbacktest.demo2;

/**
 * Created by whp on 2018/9/8
 */

public class Test {
    public static void main(String[] args) {
        /**
         * 实例化小超 new一个小超
         */
        Xc xc = new Xc();

        /**
         * 调用小徐的构造方法，加上小超的引用
         */
        Xu xu = new Xu(xc);

        /**
         * 小徐问小超的问题
         */
        xu.askQuestion("做我女朋友好吗？？？？");
    }
}