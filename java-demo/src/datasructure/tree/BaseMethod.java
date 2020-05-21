package datasructure.tree;

/**
 * @author wuhepeng
 * @date 2020/5/21
 */
public class BaseMethod {
    /**
     * 输出
     *
     * @param tree
     */
    public static void printTree(BinaryTree tree) {
        if (tree == null) {
            return;
        }
        System.out.println(tree.getRoot());
        System.out.println("|¯¯|");
        if (tree.getLeft() != null) {
            System.out.print(tree.getLeft().getRoot() + "  ");
        }
        if (tree.getRight() != null) {
            System.out.print(tree.getRight().getRoot());
        }
    }

    /**
     * 输出二叉树的层数
     *
     * @param tree
     * @return
     */
    public static int length(BinaryTree tree) {
        return tree == null ? 0 : 1 + Math.max(length(tree.getLeft()), length(tree.getRight()));
    }
}
