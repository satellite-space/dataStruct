package com.wxy.stack.impl;

/**
 * <p>两栈共享空间——共享栈</p>
 *
 * @author wxy
 * @version 1.0
 * @create 2019/10/13 17:39
 */
public class DoubleStack {

    private final static int MAX_SIZE = 1000;

    private Object[] elements;

    private int top1 = -1;

    private int top2;

    public DoubleStack() {
        elements = new Object[MAX_SIZE];
        top2 = MAX_SIZE;
    }

    public DoubleStack(int length) {
        elements = new Object[length];
        top2 = length;
    }

    public void clearStack() {
        if (top1 == -1 && top2 == elements.length) {
            throw new RuntimeException("栈已空");
        }

        for (int i = 0; i <= top1; i++) {
            elements[i] = null;
        }

        for (int i = elements.length - 1; i >= top2; i--) {
            elements[i] = null;
        }
        elements = null;
    }

    public boolean isEmpty() {
        return top1 == -1 && top2 == elements.length;
    }

    public Object getTop(int stackNum) {
        if (stackNum == 1) {
            if (top1 == -1) {
                throw new RuntimeException("栈已空");
            }
            return elements[top1];
        }

        if (stackNum == 2) {
            if (top2 == elements.length) {
                throw new RuntimeException("栈已空");
            }
            return elements[top2];
        }
        throw new RuntimeException("请输入正确的栈序号");
    }

    public boolean push(Object e, int stackNum) {
        if (top1 + 1 == top2) {
            throw new RuntimeException("栈已满");
        }

        if (stackNum == 1) {
            elements[++top1] = e;
        } else if (stackNum == 2) {
            elements[--top2] = e;
        }

        return true;
    }

    public Object pop(int stackNum) {
        if (stackNum == 1) {
            if (-1 == top1) {
                throw new RuntimeException("栈1已空");
            }
            return elements[top1--];
        } else if (stackNum == 2) {
            if (elements.length == top2) {
                throw new RuntimeException("栈2已空");
            }
            return elements[top2++];
        }
        throw new RuntimeException("请输入正确的栈序号");
    }

    public int size() {
        return (top1 == -1 && top2 == elements.length) ? 0 : (elements.length - top2 + top1 + 1);
    }
}
