/**
 * Created by wuhp on 2018/1/31
 */
public class SwitchTest {
    public static void main(String[] args) {
        int i = 1;
        // 1、先匹配 没有匹配到就default
        // 2、匹配后 向下执行语句直到遇到break
        switch (i) {
            case 1:
                System.out.println("case1");
            case 2:
                System.out.println("case2");
                break;
            default:
                System.out.println("default");
            case 3:
                System.out.println("case3");
            case 4:
                System.out.println("case4");
                break;
        }
    }
}
