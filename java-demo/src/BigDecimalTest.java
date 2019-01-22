import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by whp on 2018/8/31
 */
public class BigDecimalTest {
    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal("12.2000");
        BigDecimal bigDecimal2 = new BigDecimal("3.990000");
        //乘法
        System.out.println(bigDecimal.multiply(bigDecimal2));
        //除法
        System.out.println(bigDecimal.divide(bigDecimal2, 5, RoundingMode.FLOOR));
        //加法
        System.out.println(bigDecimal.add(bigDecimal2));
    }
}
