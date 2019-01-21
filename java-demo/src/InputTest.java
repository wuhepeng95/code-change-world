import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by wuhp on 2018/1/31
 */
public class InputTest {
    public static void main(String[] args) throws IOException {

        // 1、最简单，最强大的
//        Scanner sc = new Scanner(System.in);
//        String nextLine = sc.nextLine();

        // 2、但是System.out.read()只能针对一个字符的获取，同时，获取进来的变量的类型只能是char
        int in;
        char[] chars = new char[200];
        int i = 0;
        while ((in = System.in.read()) != 10) {
            chars[i++] = (char) in;
            System.out.print(in + "\t");
        }
        System.out.println("最终结果" + String.valueOf(chars));

        //3、从控制台接收一个字符串，然后将其打印出来
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        System.out.println("Enter your value:");
        str = br.readLine();
        System.out.println("your value is :" + str);
    }
}