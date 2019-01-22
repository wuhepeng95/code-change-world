import sockettest.URLDemo;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * Created by whp on 2018/8/31
 *
 * @see URLDemo
 */
public class EncodingTest {
    public static void main(String[] args) throws UnsupportedEncodingException, MalformedURLException {
        System.out.println("-------------------------获取常见编码格式的编码的字符数组--------------------------------");
        String strs = "a";
        System.out.println("Unicode编码:" + Arrays.toString(strs.getBytes(Charset.forName("Unicode"))));//Unicode也有UCS-4规范，就是用 4个字节来编码字符
        System.out.println("ISO-8859-1编码:" + Arrays.toString(strs.getBytes(Charset.forName("ISO-8859-1"))));//单字节编码
        System.out.println("GB2312编码:" + Arrays.toString(strs.getBytes(Charset.forName("GB2312"))));//1-2
        System.out.println("GBK编码:" + Arrays.toString(strs.getBytes(Charset.forName("GBK"))));//1-2
        System.out.println("UTF-8:" + Arrays.toString(strs.getBytes(Charset.forName("UTF-8"))));//1-4
        System.out.println("UTF-16:" + Arrays.toString(strs.getBytes(Charset.forName("UTF-16"))));
        System.out.println("UTF-32:" + Arrays.toString(strs.getBytes(Charset.forName("UTF-32"))));//四字节编码

        System.out.println("-------------------------1、将GBK转码为UTF-8 -> 中文乱码，反转回来 -> 无法复原，中文乱码--------------------------------");
        String gbk = new String("123abc我爱你".getBytes("GBK"), "UTF-8");
        System.out.println(gbk);
        System.out.println(new String(gbk.getBytes("UTF-8"), "GBK"));

        System.out.println("-------------------------2、将Unicode码转为纯二进制，\\u后的4个字节可以验证-------------------------------");
        String shang = "\u4e0a";//上
        byte[] bytes = shang.getBytes("Unicode");
        System.out.print("编码转为二进制:[");
        for (byte b : bytes) {
            System.out.print(byteToBinaryString(b) + " ");
        }
        System.out.println("]");
        int hex = 0x4e0a;
        System.out.println("十六进制转为二进制:" + Integer.toBinaryString(hex));
        System.out.println("&#19978;纯十进制:" + Integer.valueOf("100111000001010", 2));

        /**
         * URL参数编码
         */
    }

    public static String byteToBinaryString(byte b) {
        int dight = (int) b;
        String binary;
        String intBinStr = Integer.toBinaryString(b);
        int strLen = intBinStr.length();
        if (strLen > 8) {
            binary = intBinStr.substring(strLen - 8, strLen);
        } else {
            StringBuilder temp = new StringBuilder();
            for (int i = 0; i < 8 - strLen; i++) {
                temp.append("0");
            }
            binary = temp.append(intBinStr).toString();
        }
        return binary + "(" + dight + ")";
    }
}
