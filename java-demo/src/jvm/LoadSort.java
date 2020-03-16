package jvm;

/**
 * @author wuhepeng
 * @date 2020/2/25
 */
public class LoadSort {

    public static int i = 10;

    public LoadSort() {
        i = 20;
    }

    static {
        System.out.println("static" + " i=" + i);
    }

    public static void main(String[] args) {
        System.out.println("main" + " i=" + i);
    }
}
