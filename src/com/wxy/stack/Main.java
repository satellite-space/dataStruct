package com.wxy.stack;

import com.wxy.stack.impl.ArrayQueue;
import com.wxy.stack.impl.ArrayStack;
import com.wxy.stack.impl.LinkQueue;
import com.wxy.stack.impl.LinkedStack;

/**
 * <p>文件描述</p>
 *
 * @author wxy
 * @version 1.0
 * @create 2019/10/13 21:34
 */
public class Main {
    public static void main(String[] args) {
//        testStack();
        testQueue();
    }

    private static void testQueue() {
        Queue<String> queue = new ArrayQueue<>(5);
        queue = new LinkQueue<>();
        queue.add("a");
        queue.add("b");
        queue.add("c");
        queue.add("d");
        System.out.println(queue.getHead());
        System.out.println(queue.size());
        System.out.println(queue.remove());
        System.out.println(queue.size());
        queue.clearQueue();
        System.out.println(queue.isEmpty());
        queue.add("1");
        queue.add("2");
        queue.add("3");
        queue.add("4");
        queue.add("5");
        System.out.println(queue.size());
    }

    private static void testStack() {
        Stack<String> stack = new ArrayStack<>(5);
        stack = new LinkedStack<>();
        stack.push("a");
        stack.push("b");
        stack.push("c");
        stack.push("d");
        stack.push("e");
        System.out.println(stack.size());
        System.out.println(stack.getTop());
        System.out.println(stack.pop());
        stack.clearStack();
        System.out.println(stack.isEmpty());
        stack.push("as");
    }
}
