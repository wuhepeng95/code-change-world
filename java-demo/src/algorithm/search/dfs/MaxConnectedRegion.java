package algorithm.search.dfs;

import java.util.*;

public class MaxConnectedRegion {

    // 方向数组：上下左右
    private static final int[] DIRECTIONS = {-1, 0, 1, 0, 0, -1, 0, 1};

    // 判断是否在矩阵范围内
    private static boolean isValid(int x, int y, int m, int n) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }

    // 使用栈来实现 DFS 并回溯
    private static void dfs(int[][] matrix, int x, int y, boolean[][] visited, List<int[]> region) {
        int m = matrix.length;
        int n = matrix[0].length;

        Stack<int[]> stack = new Stack<>();
        stack.push(new int[] {x, y});
        visited[x][y] = true;
        region.add(new int[] {x, y});
        
        while (!stack.isEmpty()) {
            int[] current = stack.pop();
            int cx = current[0];
            int cy = current[1];
            
            // 扩展四个方向
            for (int i = 0; i < 4; i++) {
                int nx = cx + DIRECTIONS[i * 2];
                int ny = cy + DIRECTIONS[i * 2 + 1];

                if (isValid(nx, ny, m, n) && matrix[nx][ny] == 1 && !visited[nx][ny]) {
                    stack.push(new int[] {nx, ny});
                    visited[nx][ny] = true;
                    region.add(new int[] {nx, ny});
                }
            }
        }
    }

    // 找到最大四连通域
    public static List<int[]> findMaxConnectedRegion(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];
        List<List<int[]>> regions = new ArrayList<>();

        // 遍历矩阵找到所有四连通域
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1 && !visited[i][j]) {
                    List<int[]> region = new ArrayList<>();
                    dfs(matrix, i, j, visited, region);
                    regions.add(region);
                }
            }
        }

        // 找到最大的四连通域
        List<int[]> maxRegion = null;
        for (List<int[]> region : regions) {
            if (maxRegion == null || region.size() > maxRegion.size()) {
                maxRegion = region;
            }
        }

        return maxRegion;
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1, 1, 0, 0, 1, 0},
            {1, 1, 0, 0, 0, 1},
            {0, 0, 0, 1, 1, 0},
            {1, 1, 0, 1, 0, 0},
            {0, 1, 1, 0, 0, 0}
        };
        
        List<int[]> maxRegion = findMaxConnectedRegion(matrix);
        
        // 输出最大连通域的坐标
        System.out.println("最大四连通域的坐标：");
        for (int[] coord : maxRegion) {
            System.out.println(Arrays.toString(coord));
        }
    }
}
