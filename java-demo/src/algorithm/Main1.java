package algorithm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * tx2020校园招聘：解压字符串
 * 如 HG[3|B[2|CA]]F -> HGBCACABCACABCACAF
 */
public class Main1 {

    public static final String REGEX = "\\[[\\w|\\|]*\\]";
    public static final Pattern PATTERN = Pattern.compile(REGEX);

    public static void main(String[] args) {
//        String s = "SS[2|B3[2|C][2|A]";
        String s = "HG[3|B[2|CA]]F ";
        System.out.println(decompression(s));
    }

    public static String decompression(String str) {
        Matcher matcher = PATTERN.matcher(str);
        String dealFirst;
        if (matcher.find()) {
            // 处理匹配的第一个
            String group = matcher.group(0);
            dealFirst = group.substring(1, group.length() - 1);
        } else {
            return str;
        }
        String resultSub = decompressionSub(dealFirst);
        // 将处理后的结果替换
        String result = str.replaceFirst(REGEX, resultSub);
        return decompression(result);
    }

    public static String decompressionSub(String sub) {
        StringBuilder stringBuilder = new StringBuilder();

        String[] split = sub.split("\\|");
        int count = Integer.parseInt(split[0]);
        for (int i = 0; i < count; i++) {
            stringBuilder.append(split[1]);
        }
        return stringBuilder.toString();
    }

}
