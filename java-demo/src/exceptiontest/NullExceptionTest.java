package exceptiontest;

/**
 * Created by wuhp on 2018/3/12
 */
public class NullExceptionTest {
    //优化jvm参数OmitStackTraceInFastThrow后，可以不打这么多堆栈
    public static void main(String[] args) {
        int youcount = 0;
        int wucount = 0;
        for (int i = 0; i < 100000; i++) {
            String a = null;
            try {
                a.toLowerCase();
            } catch (Exception e) {
                if (e.getStackTrace().length != 0) {
                    e.printStackTrace();
                    youcount++;
                } else {
                    wucount++;

                }
            }
        }
        System.out.println("youcount" + youcount);
        System.out.println("wucount" + wucount);
    }
}