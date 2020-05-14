package algorithm.search;

/**
 * @author wuhepeng
 * @date 2019/12/25
 */
public class Find {
    public static void main(String[] args) {
        // 二分搜索只对【有序数组】有效
        int[] nums = new int[]{12, 23, 32, 55, 75, 77, 123, 324, 435};
        int target = 55;
        System.out.println(binarySearch(nums, 0, nums.length - 1, target));
    }

    /**
     * 二分查找 二分搜索只对【有序数组】有效
     * 0 1 2 3 4 5 6 7 8
     * 9 => 5
     * 8 => 5
     */
    public static int binarySearch(int[] nums, int start, int end, int target) {
        if (start > end) {
            throw new RuntimeException("not find");
        }
        // 中间值
        int center = start + (end - start) / 2;
        if (nums[center] == target) {
            return center;
        }
        // 判断
        if (nums[center] > target) {
            // 左边
            return binarySearch(nums, 0, center - 1, target);
        } else {
            // 右边
            return binarySearch(nums, center + 1, end, target);
        }
    }

/*
//Java 递归
public static int binarySearch(int[] arr, int start, int end, int hkey){
    if (start > end)
        return -1;

    int mid = start + (end - start)/2;    //防止溢位
    if (arr[mid] > hkey)
        return binarySearch(arr, start, mid - 1, hkey);
    if (arr[mid] < hkey)
        return binarySearch(arr, mid + 1, end, hkey);
    return mid;

}
// while 循环
public static int binarySearch(int[] arr, int start, int end, int hkey){
    int result = -1;

    while (start <= end){
        int mid = start + (end - start)/2;    //防止溢位
        if (arr[mid] > hkey)
            end = mid - 1;
        else if (arr[mid] < hkey)
            start = mid + 1;
        else {
            result = mid ;
            break;
        }
    }

    return result;

}
 */
}
