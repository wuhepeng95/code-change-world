package datasructure.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author wuhepeng
 * @date 2020/5/21
 */
public class SeniorMethod {
    /**
     * 判断二叉树是不是平衡二叉树
     *
     * @param tree
     * @return
     */
    public static boolean isBalance(BinaryTree tree) {
        // todo
        return false;
    }

    /**
     * 广度优先遍历-基于队列 层次遍历
     */
    public static void bfsOrder(BinaryTree myTree) {
        if (myTree == null) {
            return;
        }
        Queue<BinaryTree> queue = new LinkedList<>();
        queue.offer(myTree);
        while (!queue.isEmpty()) {
            BinaryTree node = queue.poll();
            if (node.getLeft() != null) {
                queue.offer(node.getLeft());
            }
            if (node.getRight() != null) {
                queue.offer(node.getRight());
            }
            System.out.println(node.getRoot());
        }
    }

    /**
     * 深度优先遍历
     */
    public static void dfsOrder(BinaryTree tree) {
        // 即为先序遍历、中序遍历、后序遍历
    }
}
