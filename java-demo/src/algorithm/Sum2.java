package algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 2sum问题 target = 5
 *
 * @author wuhepeng
 * @date 2019/12/23
 */
public class Sum2 {

    public static void main(String[] args) {
        int[] arr = {3, 2, 4};
//        // 蛮力法：重复组合问题，被重复使用问题？？？
//        for (int i = 0; i < arr.length; i++) {
//            for (int j = 1; j < arr.length; j++) {
//                if (arr[i] + arr[j] == 5) {
//                    System.out.println(arr[i] + "+" + arr[j] + "=" + (arr[i] + arr[j]));
//                }
//            }
//        }

        // 排序之后是不是思路就明确了
//        Arrays.sort(arr);
//        // [-3, -2, 1, 2, 3, 3, 4, 8]
//        System.out.println(Arrays.toString(arr));
//
//        int count = 1;
//        int start = arr.length - 1;
//        int i = 0;
//        while (i < start) {
//            // 外层循环
//            for (int j = start; j > i; j--) {
//                System.out.println("内层循环第" + (count++) + "次,i=" + i + ",j=" + j);
//                if (arr[i] + arr[j] == 5) {
//                    start = j - 1;
//                    System.out.println(arr[i] + "+" + arr[j] + "=" + (arr[i] + arr[j]));
//                    break;
//                }
//            }
//
//            i++;
//        }
        System.out.println(Arrays.toString(new Sum2().twoSum(arr, 6)));

    }

//    public int[] twoSum(int[] nums, int target) {
//        int[] result = new int[2];
//
//        int count = 1;
//        int start = nums.length - 1;
//        int i = 0;
//        while (i < start) {
//            // 外层循环
//            if (nums[i] > target) {
//                break;
//            }
//            System.out.println("外层循环第" + (count++) + "次,i=" + i);
//            for (int j = start; j > i; j--) {
//                System.out.println("内层循环第" + (count++) + "次,i=" + i + ",j=" + j);
//                if (nums[i] + nums[j] == target) {
//                    start = j - 1;
//                    result[0] = i;
//                    result[1] = j;
//                    break;
//                }
//            }
//            i++;
//        }
//        return result;
//    }

    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];

        Map<Integer, Integer> maps = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (maps.containsKey(nums[i])) {
                result[0] = maps.get(nums[i]);
                result[1] = i;
                return result;
            }
            maps.put(target - nums[i], i);
        }

        return null;
    }
}
