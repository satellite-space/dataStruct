package com.wxy.stack.impl;

import com.wxy.stack.Queue;

/**
 * <p>文件描述</p>
 *
 * @author wxy
 * @version 1.0
 * @create 2019/10/15 22:13
 */
public class LinkQueue<E> implements Queue<E> {

    private final class Node<E> {
        private E data;
        private Node<E> next;

        Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    private int count = 0;

    private Node<E> front, rear;

    public LinkQueue() {
        Node<E> node = new Node<>(null, null); // 头结点
        front = rear = node;
    }

    @Override
    public void clearQueue() {
        for (Node<E> x = front.next; x != null; ) {
            Node<E> node = x.next;
            x.data = null;
            x.next = null;
            x = node;
        }
        rear = front;
        count = 0;
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
        return front.next.data;
    }

    @Override
    public boolean add(E e) {
        addLast(e);
        return true;
    }

    private void addLast(E e) {
        Node<E> node = new Node<>(e, null);
        rear.next = node;
        rear = node;
        count++;
    }

    @Override
    public E remove() {
        Node<E> node = front.next;
        front.next = node.next;
        if (node == rear) {
            rear = front;
        }
        return node.data;
    }

    @Override
    public int size() {
        return count;
    }
}
