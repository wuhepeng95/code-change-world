package algorithm.md5;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by whp on 2018/8/31
 */
public class JavaMd5 {
    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String encodeStr = "你好吗";
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(encodeStr.getBytes("UTF-8"));
        System.out.println(byte2Hex(md5.digest()));
    }

    private static String byte2Hex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        String temp;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                builder.append("0");
            }
            builder.append(temp);
        }
        return builder.toString();
    }
}
