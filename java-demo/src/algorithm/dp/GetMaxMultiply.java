package algorithm.dp;

/**
 * Created by wuhp on 2018/3/21
 * 子串最大乘积问题
 */
public class GetMaxMultiply {
    public static void main(String[] args) {
        int[] nums = {2, -3, -2, 3, -4, 9, 4};
        GetMaxMultiply.method1(nums);
        GetMaxMultiply.method2(nums);
    }

    /**
     * 动态规划方法
     * dp[0]存最大值,dp[1]存最小值:通过dp状态表能求出最大值和最小值
     * [2,-3,12,36,  24,  216,  864]
     * [2,-6,-2,-6,-144,-1296,-5184]
     * @param nums
     */
    public static void method1(int[] nums) {
        int last_max = nums[0];
        int last_min = nums[0];
        int result = nums[0];
        int cur_max;
        int cur_min;
        // 动态规划
        for (int i = 1; i < nums.length; i++) {
            cur_max = Integer.max(nums[i], Integer.max(last_max * nums[i], last_min * nums[i]));
            cur_min = Integer.min(nums[i], Integer.min(last_max * nums[i], last_min * nums[i]));
            result = Integer.max(result, cur_max);
            last_max = cur_max;
            last_min = cur_min;
        }
        System.out.println("最大乘积为：" + result);
    }

    /**
     * 蛮力法
     * @param nums
     */
    public static void method2(int[] nums) {
        int mul;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            mul = 1;
            for (int j = i; j < nums.length; j++) {
                mul *= nums[j];
                System.out.println("从" + i + "到" + j + "的积为:" + mul);
                // 如果当前乘积比max大，max设为当前乘积
                if (mul > max) {
                    max = mul;
                }
            }
        }
        System.out.println("最大乘积为" + max);
    }
}
