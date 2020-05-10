package algorithm.dp;

import java.util.Arrays;

/**
 * @author wuhepeng
 * @date 2020/5/6
 */
public class GetLegalIpAddress {

    /**
     * 给定字符串 拆成4个255以内的数有多少拆法
     * 19216801
     */
    public static void main(String[] args) {
        // 字符串
        int[] nums = {1, 9, 2, 1, 6, 8, 0, 1};
        // 4个255
        int goal = 4;
        GetLegalIpAddress.method(nums, goal);
    }

    /**
     * dp 状态表
     * 二位数组理解:[][] 先定义的[]表示{[],[],[]...} 二维则为{[{[],[],[]...} ],[{[],[],[]...} ],[{[],[],[]...} ]...}
     * @param nums
     */
    public static void method(int[] nums, int goal) {
        int dp[][] = new int[goal][nums.length];

        // 初始化第一行
        int tempSum = 0;
        for (int i = 0; i < dp[0].length; i++) {
            tempSum = tempSum * 10 + nums[i];
            if (tempSum > 255) {
                break;
            }
            dp[0][i]++;
        }

        // 动态规划根据第一行衍生后面的结果
        for (int i = 1; i < goal; i++) {
            for (int j = 0; j < nums.length; j++) {
                dp(nums, dp, i, j);
            }
        }
        System.out.println(Arrays.deepToString(dp));
    }

    private static void dp(int[] nums, int[][] dp, int i, int j) {
        int tempPointer = j;
        int capacity = 1;
        int tempSum = 0;
        // 指针最小为1
        while (true) {
            if (tempPointer < 1) {
                break;
            }
            // 计算当前和 判断当前和满足出口条件
            tempSum += nums[tempPointer] * capacity;
            if (tempSum > 255) {
                break;
            }
            tempPointer--;
            capacity *= 10;

            // 累加结果
            dp[i][j] += dp[i - 1][tempPointer];
        }
    }
}
