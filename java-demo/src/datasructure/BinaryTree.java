package datasructure;

/**
 *       4
 *     /  \
 *    3    6
 *   / \  /
 *  1  2 5
 *
 * 二叉树
 * @author wuhepeng
 * @date 2020/3/13
 */
public class BinaryTree {

    public int root;
    public BinaryTree left;
    public BinaryTree right;

    public static void main(String[] args) {
        BinaryTree myTree = new BinaryTree();
        myTree.root = 4;

        // left
        BinaryTree left = new BinaryTree();
        left.root = 3;
        BinaryTree leftLeft = new BinaryTree();
        leftLeft.root = 1;
        left.left = leftLeft;
        BinaryTree leftRight = new BinaryTree();
        leftRight.root = 2;
        left.right = leftRight;
        myTree.left = left;

        // right
        BinaryTree right = new BinaryTree();
        right.root = 6;
        BinaryTree rightLeft = new BinaryTree();
        rightLeft.root = 5;
        right.left = rightLeft;
        right.right = null;
        myTree.right =right;

        printTree(myTree);

    }

    public static void printTree(BinaryTree tree){
        if (tree == null){
            return;
        }
        System.out.println(tree.root);
        printTree(tree.left);
        printTree(tree.right);

    }

    /**
     * 输出二叉树的高度
     * @param tree
     * @return
     */
    public static int length(BinaryTree tree){
        if (tree == null){
            return 0;
        }else {
            return tree.left.left.root;
        }
    }

    /**
     * 判断二叉树是不是平衡二叉树
     * @param tree
     * @return
     */
    public static boolean isBanlance(BinaryTree tree){
        return false;
    }

    /**
     * 先序遍历
     */

    /**
     * 中序遍历
     */

    /**
     * 后序遍历
     */

}
