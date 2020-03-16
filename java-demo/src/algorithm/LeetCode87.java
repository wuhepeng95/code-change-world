package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author wuhepeng
 * @date 2020/3/12
 * 扰乱字符串
 */
public class LeetCode87 {

    public static void main(String[] args) {
        System.out.println(new LeetCode87().isScramble("abcde", "caebd"));
    }

    public boolean isScramble(String s1, String s2) {
        if (s2.length() != s1.length()) {
            return false;
        }
        List<Byte> arrayList = new ArrayList<>();
        for (byte aByte : s2.getBytes()) {
            arrayList.add(aByte);
        }
        for (byte aByte : s1.getBytes()) {
            if (!arrayList.contains(aByte)) {
                return false;
            }
        }

        return true;
    }

    /**
     * 动态规划法求解
     * dynamic programming
     */
    public boolean isScrambleD(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        if (s1.equals(s2)) {
            return true;
        }

        int[] letters = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            letters[s1.charAt(i) - 'a']++;
            letters[s2.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (letters[i] != 0) {
                return false;
            }
        }

        int length = s1.length();
        boolean[][][] dp = new boolean[length + 1][length][length];
        //遍历所有的字符串长度
        for (int len = 1; len <= length; len++) {
            //S1 开始的地方
            for (int i = 0; i + len <= length; i++) {
                //S2 开始的地方
                for (int j = 0; j + len <= length; j++) {
                    //长度是 1 无需切割
                    if (len == 1) {
                        dp[len][i][j] = s1.charAt(i) == s2.charAt(j);
                    } else {
                        //遍历切割后的左半部分长度
                        for (int q = 1; q < len; q++) {
                            dp[len][i][j] = dp[q][i][j] && dp[len - q][i + q][j + q]
                                    || dp[q][i][j + len - q] && dp[len - q][i + q][j];
                            //如果当前是 true 就 break，防止被覆盖为 false
                            if (dp[len][i][j]) {
                                break;
                            }
                        }
                    }
                }
            }
        }
        return dp[length][0][0];
    }
}
