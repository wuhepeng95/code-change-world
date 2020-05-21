package datasructure.tree;

/**
 * @author wuhepeng
 * @date 2020/5/21
 */
public class TestMain {
    /* my tree
     *       4
     *     /   \
     *    3     6
     *   / \   /
     *  1   2 5
     */

    public static void main(String[] args) {
        BinaryTree one = new BinaryTree(1, null, null);
        BinaryTree two = new BinaryTree(2, null, null);
        BinaryTree three = new BinaryTree(3, one, two);
//        BinaryTree nine = new BinaryTree(9, null, null);
        BinaryTree five = new BinaryTree(5, null, null);
        BinaryTree six = new BinaryTree(6, five, null);
        BinaryTree myTree = new BinaryTree(4, three, six);

        // 基础方法
        System.out.print("树的层数（高度&深度）：");
        System.out.print(BaseMethod.length(myTree));
        System.out.println();
        BaseMethod.printTree(myTree);
        System.out.println();

        // 遍历方法
        System.out.print("pre先序遍历：");
        OrderMethod.preOrder(myTree);
        System.out.println();
        System.out.print("in中序遍历：");
        OrderMethod.inOrder(myTree);
        System.out.println();
        System.out.print("post后序遍历：");
        OrderMethod.postOrder(myTree);
        System.out.println();

        // 高级方法
        System.out.println("bfs广度优先遍历:");
        SeniorMethod.bfsOrder(myTree);

    }
}
