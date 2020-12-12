import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalTest {

    public static void main(String[] args) {
        BigDecimal b1 = new BigDecimal(20);
        BigDecimal b2 = new BigDecimal(20.000000000000000000);

        System.out.println("比较结果" + b1.compareTo(b2));// -1 b1小于b2
        System.out.println("比较结果" + b1.equals(b2));// -1 b1小于b2
        System.out.println(b1 == b2);// -1 b1小于b2
        System.out.println(System.identityHashCode(b1));// -1 b1小于b2
        System.out.println(System.identityHashCode(b2));// -1 b1小于b2

        System.out.println("------------------------------------------------------------------");

        BigDecimal bd1 = new BigDecimal("20.00");
        // 取精度
        System.out.println(bd1.setScale(1, RoundingMode.HALF_UP).multiply(new BigDecimal(5)));


    }
}
