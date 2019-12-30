package algorithm;

import java.util.Arrays;

/**
 * @author wuhepeng
 * @date 2019/12/25
 * <p>
 * 必会排序算法：时间复杂度 空间复杂度 是否可靠
 */
public class Sort {

    public static void main(String[] args) {
        int[] nums = new int[]{13, 34, 12, -10, 34, 56, 23};
        // 从小到大
//        new Sort().bubbleSort(nums);
        new Sort().insertSort(nums);

        System.out.println(Arrays.toString(nums));
    }

    /**
     * 冒泡排序 o(n²) o(1) 可靠
     */
    public void bubbleSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[j + 1] < nums[j]) {
                    // 交换
                    int temp = nums[j + 1];
                    nums[j + 1] = nums[j];
                    nums[j] = temp;
                }
            }
        }
    }

    /**
     * 插入排序
     */

    public void insertSort(int[] nums) {

        for (int i = 0; i < nums.length; i++) {

        }
    }
    /**
     * 二分排序
     */

    /**
     * 快速排序
     */

    /**
     * 归并排序
     */

    /**
     * 堆排序
     */
}
