import java.util.Arrays;

public class Find {
    public static void main(String[] args) {
        int[] nums = new int[]{12, 324, 435, 23, 123};
        Arrays.sort(nums);
        System.out.println();
    }

    public void conver2JO(int[] nums, int goal) {

    }

    public void sortTemp(int[] nums) {
        int aLen = nums.length;

        int temp = aLen % 2 == 0 ? aLen / 2 : aLen / 2 + 1;
        for (int i = 0; i < temp; i++) {
            if (nums[i] > nums[i+1]){

            }
        }
    }

    public static void swap(int a,int b){
        int temp = a;
        a = b;
        b = temp;
    }

    private int find(int[] nums, int goal){
        Arrays.sort(nums);


        return nums.length;
    }

}
