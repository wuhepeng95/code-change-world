package algorithm.dp;

import java.util.Arrays;

/**
 * Created by wuhp on 2018/3/21
 * 子串最大和问题
 */
public class GetMaxSum {

    public static void main(String[] args) {
        GetMaxSum.method2();
    }

    /**
     * 蛮力法 n+(n-1)+(n-2)+(n-3)+...+1=(n+1)n/2 ≈ n² 时间复杂度为O(n²)
     */
    public static void method1() {
        int[] nums = {12, -19, 3, -1, -4};
        int sum;
        int max = 0;
        int start = 0;
        int end = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                System.out.println("从" + i + "到" + j + "的和为:" + sum);
                if (sum > max) {
                    start = i;
                    end = j;
                    max = sum;
                }
            }
        }
        start++;
        end++;
        System.out.println("最大值为" + max + " 下标为：" + start + "到" + end);
    }

    /**
     * 动态规划方法(DP) 时间复杂度为O(n)
     * 原理：
     * 对应状态转化方程 stats[] = [2, 5, 3, 6, 10, 1, 5]
     */
    public static void method2() {
        int[] nums = {2, 3, -2, 3, 4, -9, 4};
        int max = nums[0];
        int start = 0;
        int end = 0;
        int temp = 0;

        int[] stats = new int[nums.length];
        stats[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (stats[i - 1] > 0) {
                //状态为正(0更不行) 累加
                stats[i] = stats[i - 1] + nums[i];
            } else {
                //状态位负 前面的不在需要加后面的了
                stats[i] = nums[i];
                temp = i;
            }
            //如果较大 记录位置
            if (stats[i] > max) {
                max = stats[i];
                start = temp;
                end = i;
            }
        }
        start++;
        end++;
        System.out.println(Arrays.toString(stats));
        System.out.println("最大值为" + max + " 下标为：" + start + "到" + end);
    }
}
