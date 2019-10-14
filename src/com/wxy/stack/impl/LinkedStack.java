package com.wxy.stack.impl;

import com.wxy.stack.Stack;

/**
 * <p>文件描述</p>
 *
 * @author wxy
 * @version 1.0
 * @create 2019/10/13 21:13
 */
public class LinkedStack<E> implements Stack<E> {

    private class Node<E> {
        private E data;
        private Node<E> next;

        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node<E> top;

    private int count = 0;

    public LinkedStack() {}

    @Override
    public void clearStack() {
        if (null == top) {
            throw new RuntimeException("栈已空");
        }
        for (Node x = top; x != null; ) {
            Node node = x.next;
            x.data = null;
            x.next = null;
            x = node;
        }
        top = null;
        count = 0;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index > count - 1) {
            throw new RuntimeException("栈已空");
        }
        Node node = top;
        for (int i = count - 1; i >= index; i--) {
            node = node.next;
        }
        return (E) node.data;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public E getTop() {
        if (null == top) {
            throw new RuntimeException("栈已空");
        }
        return top.data;
    }

    @Override
    public boolean push(Object o) {
        Node newNode = new Node(o, top);
        top = newNode;
        count++;
        return true;
    }

    @Override
    public E pop() {
        if (null == top) {
            throw new RuntimeException("栈已空");
        }
        return top.data;
    }

    @Override
    public int size() {
        return count;
    }
}
