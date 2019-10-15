package com.wxy.stack.impl;

import com.wxy.stack.Queue;

/**
 * <p>文件描述</p>
 *
 * @author wxy
 * @version 1.0
 * @create 2019/10/15 22:05
 */
public class ArrayQueue<E> implements Queue<E> {

    private static int MAX_SIZE = 100;

    private Object[] elements;

    private int front = 0;

    private int rear = 0;

    public ArrayQueue() {
        elements = new Object[MAX_SIZE];
    }

    public ArrayQueue(int length) {
        elements = new Object[length];
        MAX_SIZE = length;
    }

    @Override
    public void clearQueue() {
        for (int i = 0; i < MAX_SIZE; i++) {
            elements[i] = null;
        }
        front = rear = 0;
    }

    @Override
    public boolean isEmpty() {
        return front == rear;
    }

    @Override
    public E getHead() {
        if (front == rear) {
            throw new RuntimeException("队列已空");
        }
        return (E) elements[front];
    }

    @Override
    public boolean add(E e) {
        if ((rear + 1) % MAX_SIZE == front) {
            throw new RuntimeException("队列已满");
        }
        elements[rear] = e;
        rear = (rear + 1) % MAX_SIZE;
        return true;
    }

    @Override
    public E remove() {
        if (front == rear) {
            throw new RuntimeException("队列已空");
        }
        E e = (E) elements[front];
        front = (front + 1) % MAX_SIZE;
        return e;
    }

    @Override
    public int size() {
        return (MAX_SIZE - front + rear) % MAX_SIZE;
    }
}
