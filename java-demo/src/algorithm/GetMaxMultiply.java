package algorithm;

/**
 * Created by wuhp on 2018/3/21
 * 最大子串成绩问题
 */
public class GetMaxMultiply {
    public static void main(String[] args) {
        int[] nums = {2, -1, 2, 3};
        int last_max = nums[0];
        int last_min = nums[0];
        int result = nums[0];
        int cur_max;
        int cur_min;
        for (int i = 1; i < nums.length; i++) {
            cur_max = Integer.max(nums[i], Integer.max(last_max * nums[i], last_min * nums[i]));
            cur_min = Integer.min(nums[i], Integer.min(last_max * nums[i], last_min * nums[i]));
            result = Integer.max(result, cur_max);
            last_max = cur_max;
            last_min = cur_min;
        }
        System.out.println("最大乘积为：" + result);

        GetMaxMultiply.method2();
    }

    //  if (integers.stream().filter(integer -> integer < 0).count() % 2 == 0 && !integers.contains(0)) {
    //     return integers.stream().reduce((integer, integer2) -> integer * integer2).get();
    // }

    public static void method2() {
        int[] nums = {2, -1, 2, 3};
        int mul;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            mul = 1;
            for (int j = i; j < nums.length; j++) {
                mul *= nums[j];
                System.out.println("从" + i + "到" + j + "的积为:" + mul);
                if (max < mul) {
                    max = mul;
                    // TODO: 2018/3/22 记录下标 原理一样
                }
            }
        }
        System.out.println("最大乘积为" + max);
    }
}
