package callbacktest;

/**
 * Created by whp on 2018/9/17
 */
public class SimpleTest {
    public static void main(String[] args) {
        new C2().m2(new C1() {
            @Override
            public void m1() {
                System.out.println("m1");
            }
        });
    }
}

interface C1 {
    void m1();
}

class C2 {
    void m2(C1 c1) {
        c1.m1();
        System.out.println("m2");
    }
}