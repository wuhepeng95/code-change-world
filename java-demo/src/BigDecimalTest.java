import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalTest {

    public static void main(String[] args) {
        BigDecimal b1 = new BigDecimal("20.000");
        BigDecimal b2 = new BigDecimal("20.000000000000000000");

        System.out.println("比较结果" + b1.compareTo(b2));// 0 b1等于b2
        System.out.println("比较结果" + b1.equals(b2));// -1 b1不等于b2
        System.out.println(b1 == b2);// false 不等
        System.out.println("HashCode:" + b1.hashCode() + " identityHashCode: " + System.identityHashCode(b1));// -1 b1小于b2
        System.out.println("HashCode:" + b2.hashCode() + " identityHashCode: " + System.identityHashCode(b2));// -1 b1小于b2

        System.out.println("------------------------------------------------------------------");

        BigDecimal bd1 = new BigDecimal("20.00");
        // 取精度
        System.out.println(bd1.setScale(1, RoundingMode.HALF_UP).multiply(new BigDecimal(5)));


    }
}
