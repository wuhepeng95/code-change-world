package datasructure.tree;

import java.util.Stack;

/**
 * @author wuhepeng
 * @date 2020/5/21
 */
public class OrderMethod {
    /**
     * 先序遍历
     */
    public static void preOrder(BinaryTree tree) {
        if (tree == null) {
            return;
        }
        // Do Something with root
        System.out.print(tree.getRoot() + " ");
        if (tree.getLeft() != null) {
            preOrder(tree.getLeft());
        }
        if (tree.getRight() != null) {
            preOrder(tree.getRight());
        }
    }

    /**
     * 中序遍历
     */
    public static void inOrder(BinaryTree tree) {
        if (tree == null) {
            return;
        }
        if (tree.getLeft() != null) {
            inOrder(tree.getLeft());
        }
        // Do Something with root
        System.out.print(tree.getRoot() + " ");
        if (tree.getRight() != null) {
            inOrder(tree.getRight());
        }
    }

    /**
     * 后序遍历
     */
    public static void postOrder(BinaryTree tree) {
        if (tree == null) {
            return;
        }
        if (tree.getLeft() != null) {
            postOrder(tree.getLeft());
        }
        if (tree.getRight() != null) {
            postOrder(tree.getRight());
        }
        // Do Something with root
        System.out.print(tree.getRoot() + " ");
    }

    /**
     * 先序遍历-非递归 基于栈
     */
    public static void preOrderNotRecursion(BinaryTree root) {
        Stack<BinaryTree> stack = new Stack<>();
        BinaryTree current = root;
        // 当前遍历节点不为空 或者 栈不为空 继续
        while (current != null || !stack.isEmpty()) {
            if (current != null) {
                System.out.print(current.getRoot() + " ");
                // 将刚才的入栈
                stack.push(current);
                current = current.getLeft();
            } else {
                current = stack.pop().getRight();
            }
        }
    }

    /**
     * 中序遍历-非递归 基于栈
     */
    public static void inOrderNotRecursion(BinaryTree root) {
        Stack<BinaryTree> stack = new Stack<>();
        BinaryTree current = root;
        while (current != null || !stack.isEmpty()) {
            if (current != null) {
                stack.push(current);
                current = current.getLeft();
            } else {
                current = stack.pop();
                System.out.print(current.getRoot() + " ");
                current = current.getRight();
            }
        }
    }

    /**
     * 后序遍历-非递归 基于栈
     */
    public static void postOrderNotRecursion(BinaryTree root) {
        if (root == null) {
            return;
        }
        Stack<BinaryTree> stack = new Stack<>();
        BinaryTree current = root;
        stack.push(current);
        // 每个结点 push 两次，这样可以简单的判断出哪些结点是否处理过
        stack.push(current);
        while (!stack.isEmpty()) {
            current = stack.pop();
            if (!stack.isEmpty() && current == stack.peek()) {
                if (current.getRight() != null) {
                    stack.push(current.getRight());
                    stack.push(current.getRight());
                }
                if (current.getLeft() != null) {
                    stack.push(current.getLeft());
                    stack.push(current.getLeft());
                }
            } else {
                System.out.print(current.getRoot() + " ");
            }
        }
    }
}
