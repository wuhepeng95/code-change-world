package algorithm;

import java.util.Arrays;

public class QuickSort {

//    public static void quickSort(int[] arr, int left, int right) {
//        if (left >= right) return;
//
//        // 选定一个基准值（pivot），这里取中间值
//        int pivot = arr[(left + right) / 2];
//
//        // 分区，返回分界点索引
//        int index = partition(arr, left, right, pivot);
//
//        // 对左右子数组递归排序
//        quickSort(arr, left, index - 1);
//        quickSort(arr, index, right);
//    }
//
//    private static int partition(int[] arr, int left, int right, int pivot) {
//        while (left <= right) {
//            // 找到左边第一个比 pivot 大的
//            while (arr[left] < pivot) left++;
//
//            // 找到右边第一个比 pivot 小的
//            while (arr[right] > pivot) right--;
//
//            // 交换左右不符合要求的元素
//            if (left <= right) {
//                swap(arr, left, right);
//                left++;
//                right--;
//            }
//        }
//        // 返回新的分界点（left 是右边子数组的起始位置）
//        return left;
//    }
//
//    private static void swap(int[] arr, int i, int j) {
//        int tmp = arr[i];
//        arr[i] = arr[j];
//        arr[j] = tmp;
//    }
//
//    // 测试用例
//    public static void main(String[] args) {
//        int[] nums = {6, 3, 8, 5, 2, 7, 4, 1};
//
//        System.out.println("排序前：");
//        printArray(nums);
//
//        quickSort(nums, 0, nums.length - 1);
//
//        System.out.println("排序后：");
//        printArray(nums);
//    }
//
//    private static void printArray(int[] arr) {
//        for (int num : arr) System.out.print(num + " ");
//        System.out.println();
//    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, 2, 3, 32, 4, 1, 9};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    private static void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        int temp = arr[(left + right) / 2];
        System.out.println("当前顺序：" + Arrays.toString(arr) + "中间值: " + (left + right) / 2 + " left: " + left + " right: " + right);
        int index = part(arr, left, right, temp);

        quickSort(arr, 0, index - 1);
        quickSort(arr, index, right);

    }

    private static int part(int[] arr, int left, int right, int temp) {
        while (left <= right) {
            // 找到要交换的左右下表
            while (arr[left] < temp) left++;
            while (arr[right] > temp) right--;

            if (left <= right) {
                // 交换
                int tmp = arr[left];
                arr[left] = arr[right];
                arr[right] = tmp;

                left++;
                right--;
            }
        }
        return left;
    }
}

