package algorithm;

import java.util.Scanner;

/**
 * tx2020校园招聘：最少休息多少天（可以工作和健身，但是不能俩天同时做一件事）
 * 如：输入 4
 *         1 1 0 0 (工作？)
 *         0 1 1 0 (健身？)
 *     输出 2
 */
public class Main5 {
    public static void main(String[] args) {
        Scanner scanner =  new Scanner(System.in);
        // 放假天数
        int n = scanner.nextInt();
        // 工司情况
        int[] work = new int[n];
        for (int i = 0; i < n; i++) {
            work[i] = scanner.nextInt();
        }

        // 健身房情况
        int[] fitness = new int[n];
        for (int i = 0; i < n; i++) {
            fitness[i] = scanner.nextInt();
        }
//        // 放假天数
//        int n = 4;
////      // 工司情况
//        int[] work = new int[]{0, 1, 1, 1};
//        // 健身房情况
//        int[] fitness = new int[]{0, 1, 0, 0};

        int result = 0;
        int workFlag = 0;
        int fitFlag = 0;
        for (int i = 0; i < n; i++) {
            // 俩件事都不能做
            if (work[i] + fitness[i] == 0) {
                result++;
                workFlag = 0;
                fitFlag = 0;
            }
            // 可以做一件事
            if (work[i] + fitness[i] == 1) {
                if (work[i] == 1 & workFlag == 0) {
                    //进行工作
                    workFlag = 1;
                    continue;
                }
                if (fitness[i] == 1 & fitFlag == 0) {
                    //进行健身
                    fitFlag = 1;
                    continue;
                }
                result++;
            }
            // 两件事都可以做
            if (work[i] + fitness[i] == 2) {
                // 前一天工作
                if (workFlag == 1 & fitFlag == 0) {
                    //进行健身
                    fitFlag = 1;
                    continue;
                }
                // 前一天健身
                if (workFlag == 0 & fitFlag == 1) {
                    //进行工作
                    workFlag = 1;
                    continue;
                }
                // 前一天休息
                if (workFlag == 0 & fitFlag == 0) {
                    // 进行工作还是健身看下一天
                    // 如果有下一天
                    if (i + 1 < n) {
                        // 下一天只可以做一样
                        if (work[i + 1] + fitness[i + 1] == 1) {
                            if (work[i + 1] == 1) {
                                //今天进行健身
                                fitFlag = 1;
                                continue;
                            }
                            if (fitness[i + 1] == 1) {
                                //今天进行工作
                                workFlag = 1;
                                continue;
                            }
                        }
                    }

                    //今天进行工作
                    workFlag = 1;
                    continue;

                }
            }
        }
        System.out.println(result);
    }

}
