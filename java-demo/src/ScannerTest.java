import java.util.Scanner;

/**
 * Created by wuhp on 2018/3/15
 */
public class ScannerTest {
    public static void main(String[] args) {

        int i;
        String s;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("请输入：");
            i = scanner.nextInt();
            System.out.println("你输入的数是" + i);

            //清除缓冲区留下的空格/换行  nextLine()方法会自动清理掉后边的空白符
            //scanner.nextLine();

            System.out.println("是否继续？ n/N退出");
            s = scanner.nextLine();
            System.out.println("你输入的操作代码是" + s);
            if ("n".equals(s) || "N".equals(s)) {
                break;
            }
        }
        System.out.println("程序结束");
    }

}