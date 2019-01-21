package algorithm;

/**
 * Created by wuhp on 2018/2/24
 * 汉诺塔问题
 */
public class Towers {

    private static int count = 1;

    public static void main(String[] args) {
        doTowers(4, 'A', 'B', 'C');
    }

    private static void doTowers(int topN, char from, char inter, char to) {
        if (topN == 1) {
            System.out.println("第" + count++ + "次" + "Disk 1 from " + from + " to " + to);
        } else {
            doTowers(topN - 1, from, to, inter);
            System.out.println("第" + count++ + "次" + "Disk " + topN + " from " + from + " to " + to);
            doTowers(topN - 1, inter, from, to);
        }
    }
}