package com.wxy.stack.impl;

import com.wxy.stack.Stack;

/**
 * <p>顺序栈</p>
 *
 * @author wxy
 * @version 1.0
 * @create 2019/10/13 17:22
 */
public class ArrayStack<E> implements Stack<E> {

    private final static int MAX_SIZE = 1000;

    private Object[] elements;

    private int top = -1; /* 栈顶指针 */

    public ArrayStack() {
        elements = new Object[MAX_SIZE];
    }

    public ArrayStack(int length) {
        elements = new Object[length];
    }

    @Override
    public void clearStack() {
        for (int i = 0; i < elements.length; i++) {
            elements[i] = null;
        }
        elements = null;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index > top) {
            throw new RuntimeException("下标越界");
        }
        return (E) elements[index];
    }

    @Override
    public boolean isEmpty() {
        return top != -1;
    }

    @Override
    public E getTop() {
        if (top == -1) {
            throw new RuntimeException("栈已空");
        }

        return (E) elements[top];
    }

    @Override
    public boolean push(E e) {
        if (top + 1 == elements.length) {
            throw new RuntimeException("栈已满");
        }
        elements[++top] = e;
        return true;
    }

    @Override
    public E pop() {
        if (top == -1) {
            throw new RuntimeException("栈已空");
        }
        return (E) elements[top--];
    }

    @Override
    public int size() {
        return top + 1;
    }
}
