package exceptiontest;

public class Test {
    public static void main(String[] args) {
        try {
            test1();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("main结束");
    }

    public static void test1() {
        System.out.println("抛出异常前");
        Exception exception = new Exception("自定义异常");
        try {
            throw exception;
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("抛出异常后");
    }

    public static void test2() {
        System.out.println("抛出异常前");
        try {
            int a = 2 / 0;
            System.out.println("23");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("抛出异常后");
    }

    public static void test3() throws Exception {
        System.out.println("抛出异常前");
        int a = 2 / 0;
        System.out.println(a);
        System.out.println("抛出异常后");
    }
}
