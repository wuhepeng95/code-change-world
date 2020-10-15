package algorithm.search.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个数字的字符串，给数字中间加3个点，能组成多少合法的ip
 * 例：19216801
 * 3种：19.216.80.1、192.16.80.1、192.168.0.1
 *
 * @author wuhepeng
 * @date 2020/5/6
 * @see algorithm.dp.GetLegalIpAddress
 */
public class GetLegalIpAddress {

    public static void main(String[] args) {
        String string = "1921681";
        GetLegalIpAddress.dfs(string, -1, 1, new ArrayList<>());
    }

    public static void dfs(String string, int index, int level, List<String> res) {
        // 截止条件 层级
        if (level == 4 + 1 || index == string.length() - 1) {
            if (level == 4 + 1 && index == string.length() - 1) {
                System.out.print(String.join(".", res));
                System.out.println("出口");
            }
            return;
        }

        // 遍历候选节点
        for (int i = 1; i < 4; i++) {
            String x = string.substring(index + 1, Math.min(index + 1 + i, string.length()));
            // 筛选
            if (!"".equals(x)
                    && Integer.parseInt(x) < 256
                    && ("0".equals(x) || !x.startsWith("0"))) {
                res.add(x);
                dfs(string, index + i, level + 1, res);
                res.remove(res.size() - 1); // res.pop()
            }
        }
    }
}
