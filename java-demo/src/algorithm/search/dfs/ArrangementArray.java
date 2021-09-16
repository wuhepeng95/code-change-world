package algorithm.search.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个数组['A','B','C']
 * 给出所有的不重复的排列
 * PS：
 * C-Combination 组合数
 * A-Arrangement 排列数
 */
public class ArrangementArray {

    public static void main(String[] args) {
        char[] chars = {'A', 'B', 'C', 'D'};
        int[] flags = {0, 0, 0, 0};
        ArrangementArray.dfs(chars, flags, 1, new ArrayList<>());
    }

    public static void dfs(char[] arrays, int[] flags, int level, List<Character> res) {
        // 截止条件 层级
        if (level == arrays.length + 1) {
            res.forEach(System.out::print);
            System.out.println("出口");
            return;
        }

        // 遍历候选节点
        for (int i = 0; i < arrays.length; i++) {
            char temp = arrays[i];
            // 筛选
            if (flags[i] == 0) {
                res.add(temp);
                flags[i] = 1;
                dfs(arrays, flags, level + 1, res);
                res.remove(res.size() - 1); // res.pop()
                flags[i] = 0;
            }
        }
    }

}
