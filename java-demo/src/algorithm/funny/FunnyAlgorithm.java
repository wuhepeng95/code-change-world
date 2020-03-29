package algorithm.funny;

/**
 * @author wuhepeng
 * @date 2020/3/23
 */
public class FunnyAlgorithm {

    public static void main(String[] args) {
        System.out.println(getThatDay());
    }

    // 假设美股每天跌停,什么时候股市价值会变成原来的一半
    //（1-10%）x
    //（1-10%）x（1-10%）
    //（1-10%）x（1-10%）
    public static int getThatDay() {
        for (int i = 1; i < 10; i++) {
            double currentRate = Math.pow(0.9, i);
            System.out.println(currentRate);
            if (currentRate <= 0.5) {
                return i;
            }
        }
        throw new RuntimeException("can not do this");
    }

    // yys:爬塔游戏找最短路径
}
