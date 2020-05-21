package datasructure.tree;

/**
 * 二叉树
 *
 * @author wuhepeng
 * @date 2020/3/13
 */
public class BinaryTree {

    private int root;
    private BinaryTree left;
    private BinaryTree right;

    public BinaryTree(int root, BinaryTree left, BinaryTree right) {
        this.root = root;
        this.left = left;
        this.right = right;
    }

    public int getRoot() {
        return root;
    }

    public void setRoot(int root) {
        this.root = root;
    }

    public BinaryTree getLeft() {
        return left;
    }

    public void setLeft(BinaryTree left) {
        this.left = left;
    }

    public BinaryTree getRight() {
        return right;
    }

    public void setRight(BinaryTree right) {
        this.right = right;
    }
}
