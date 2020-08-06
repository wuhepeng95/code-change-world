package jvm;

public class JavapOfFunnyCode {

    public static void main(String[] args) {
        int i = 0;
        int j = 1;
        if (i != (i = j)) {
            System.out.println(i++);
        }
        // 结果输出了1
    }

    /**
     * 0: iconst_0 // 0入栈，此时栈[0]
     * 1: istore_1 // 栈顶弹出，赋值给【变量1】（即i），此时i值为0，栈[]
     * 2: iconst_1 // 1入栈，此时栈[1]
     * 3: istore_2 // 栈顶弹出，赋值给【变量2】（即j），此时j值为1，栈[]
     * 4: iload_1 // 【局部变量1】（即i）的值入栈，此时栈[0]
     * 5: iload_2 // 【局部变量2】（即j）的值入栈，此时栈[0, 1]
     * 6: dup // 复制栈顶的值（即1），将其入栈，此时栈[0, 1, 1]
     * 7: istore_1 // 栈顶弹出，赋值给【变量1】（即i），此时i值为1，栈[0, 1]
     * 8: if_icmpeq 18 // 栈顶弹出两个元素（即1,0），进行比较，如果相等，跳转到18行，就是return。很明显，这里不相等，所以会继续往下走，即调用System.out.println打印。此时栈[]
     */
}
