package algorithm;

/**
 * Created by wuhp on 2018/2/26
 * 八皇后问题 回溯法
 */
public class Queen {/*同栏是否有皇后，1表示有*/
    private int[] column;/*右上至左下是否有皇后*/
    private int[] rup;/*左上至右下是否有皇后*/
    private int[] lup;/*解答*/
    private int[] queen;/*解答编号*/
    private int num;

    private Queen() {
        column = new int[8 + 1];
        rup = new int[(2 * 8) + 1];
        lup = new int[(2 * 8) + 1];
        for (int i = 1; i <= 8; i++) column[i] = 0;
        for (int i = 1; i <= (2 * 8); i++) rup[i] = lup[i] = 0;  /*初始定义全部无皇后*/
        queen = new int[8 + 1];
    }

    private void backtrack(int i) {
        if (i > 8) showAnswer();
        else for (int j = 1; j <= 8; j++)
            if ((column[j] == 0) && (rup[i + j] == 0) && (lup[i - j + 8] == 0)) {/*若无皇后*/
                queen[i] = j;/*设定为占用*/
                column[j] = rup[i + j] = lup[i - j + 8] = 1;
                backtrack(i + 1);  /*循环调用*/
                column[j] = rup[i + j] = lup[i - j + 8] = 0;
            }
    }

    private void showAnswer() {
        num++;
        System.out.println("\n解答" + num);
        for (int y = 1; y <= 8; y++) {
            for (int x = 1; x <= 8; x++)
                if (queen[y] == x) System.out.print("Q");
                else System.out.print(".");
            System.out.println();
        }
    }

    public static void main(String[] args) {
//        Queen queen = new Queen();
//        queen.backtrack(-1);
        int[][] chess = new int[8][8];
//        chess[0][2] = 1;

        for (int i = 0; i < chess.length; i++) {
            int column = 0;
            for (int j = 0; j < chess[i].length; j++) {
                if (chess[i][j] == 0){
                    column = j;
                    break;
                }
            }
            if (column >= chess[i].length){
                throw new RuntimeException("failed");
            }
            // ensure
            chess[i][column] = 1;
            // mark
            markChess(i, column, chess);

        }
        printChess(chess);
    }

    public static void markChess(int row, int column, int[][] chess) {
        for (int i = row + 1; i < chess.length; i++) {
            for (int j = column; j < chess[i].length; j++) {
                if (i == row) {
                    chess[i][j] = -1;
                    continue;
                }
                if (j == column) {
                    chess[i][j] = -1;
                    continue;
                }
                if (i - j == row - column) {
                    chess[i][j] = -1;
                    continue;
                }
                if (i + j == row + column) {
                    chess[i][j] = -1;
                }
            }
        }
        printChess(chess);
    }

    public static void printChess(int[][] chess) {
        System.out.println("-------begin-------");
        for (int[] ints : chess) {
            for (int anInt : ints) {
                if (anInt == -1) {
                    System.out.print(0 + "");
                } else {
                    System.out.print(anInt + "");
                }
            }
            System.out.println();
        }
        System.out.println("-------end-------");
    }
}
