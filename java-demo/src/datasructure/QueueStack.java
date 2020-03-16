package datasructure;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class QueueStack {
    public static void main(String[] args) {
        Queue<String> queue = new ArrayDeque<>();
        queue.add("1");
        queue.add("2");
        queue.add("3");
        queue.add("4");

        System.out.println(queue.poll());
        System.out.println(queue.peek());
        System.out.println(queue);
        System.out.println(queue.peek());
        System.out.println(queue);
        System.out.println(queue.poll());
        System.out.println(queue.peek());
        System.out.println(queue);

        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack);
        stack.push(4);
        System.out.println(stack.pop());
        System.out.println(stack);
    }
}
