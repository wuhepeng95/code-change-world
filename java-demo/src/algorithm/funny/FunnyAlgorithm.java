package algorithm.funny;

/**
 * @author wuhepeng
 * @date 2020/3/23
 */
public class FunnyAlgorithm {

    public static void main(String[] args) {
        System.out.println(getThatDay());
        System.out.println(getDogFoodCount(7));
    }

    // 假设美股每天跌停,什么时候股市价值会变成原来的一半
    //（1-10%）x
    //（1-10%）x（1-10%）
    //（1-10%）x（1-10%）

    public static int getThatDay() {
        for (int i = 1; i < 10; i++) {
            double currentRate = Math.pow(0.9, i);
//            System.out.println(currentRate);
            if (currentRate <= 0.5) {
                return i;
            }
        }
        throw new RuntimeException("can not do this");
    }

    // yys:一个六星需要多少狗粮
    // 三星 = 二星*2 + 满二星 = 1 * 2 + 1 = 1 * （2 + 1） = 3
    // 四星 = 三星*3 + 满三星 = 3 * 3 + 3 = 3 * （3 + 1） = 12
    // 五星 = 四星*3 + 满四星 = 12* 4 + 12= 12* （4 + 1） = 60
    // 六星 = 五星*5 + 满五星 = 60* 5 + 60= 60* （5 + 1） = 360 一将名成万骨枯
    // 2520  n!/2 原来是阶乘

    public static int getDogFoodCount(int starNum) {
        if (starNum < 1) {
            throw new RuntimeException("no star one");
        }
        if (starNum == 2) {
            return 1;
        }
        return getDogFoodCount(starNum - 1) * starNum;
    }

    // yys:怎样充值最省钱
    // 充值金额 6 12 30 68 128 328 648
    // 奖励金额 1 88 288 1888 3288 5000 6666
    // 引申为凑硬币问题 @see MinCoinDP
    // 问题1、至少需要多少个硬币
    // 问题2、在不超过的情况下，怎样凑法最接近目标值
    // 引申 0-1简单背包、完全背包、多重背包？ dp

    public static int[] getLeastPay(int target) {
        int[] singlePay = {6, 12, 30, 68, 128, 328, 648};
        for (int maxPay : singlePay) {
            if (target == maxPay) {
                return new int[]{maxPay};
            }
            if (target < maxPay) {

            }
        }
        return new int[]{};
    }
}
