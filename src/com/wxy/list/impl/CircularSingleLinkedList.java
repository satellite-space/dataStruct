package com.wxy.list.impl;

import com.wxy.list.List;

/**
 * <p>文件描述</p>
 *
 * @author wxy
 * @version 1.0
 * @create 2019/9/28 21:15
 */
// TODO 目前头插法完成。尾插待改进
public class CircularSingleLinkedList<E> implements List<E> {

    private int size = 0;

    /* 记录最后一个节点，便于操作数据 */
    private Node<E> last;

    private static class Node<E> {
        E element;
        Node<E> next;

        public Node(E e, Node<E> next) {
            this.element = e;
            this.next = next;
        }
    }

    public CircularSingleLinkedList() {
        Node<E> node = new Node<>(null, null);
        node.next = node;
        this.last = node;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clearList() {
        for (; last != last.next; ) {
            last.next = last.next.next;  // 头插法销毁线性表，从前往后销毁,由于是单向链表。尾插法的销毁方式和此相同
        }
        size = 0;

        // 以下是尾插法的后续销毁动作
        // last.element = null;
    }

    @Override
    public E get(int index) {
        if (index < 0 || size <= index) {
            throw new RuntimeException();
        }

        Node<E> node = last;
        for (int i = 0; i < size - index; i++) {
            node = node.next;
        }
        return node.element;
    }

    @Override
    public int indexOf(E element) {

        Node<E> node = last;
        int index = 0;
        for (int i = 0; node != node.next; ) {
            if (element.equals(node.element)) {
                index = i;
            }
            i++;
            node = node.next;
        }

        if (index > 0) {
            return size - index;
        }
        return -1;
    }

    @Override
    public boolean add(E element) {
//        linkHead(element);
        linkTail(element);
        return true;
    }

    /**
     * 尾插法
     *
     * @param element
     */
    private void linkTail(E element) {
        Node<E> node = new Node<>(element, last.next);
        last.next = node;
        this.last = node;
        size++;
    }

    /**
     * 头插法
     *
     * @param element
     */
    private void linkHead(E element) {
        Node<E> node = new Node<>(element, last.next);
        last.next = node;
        size++;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || size <= index) {
            throw new RuntimeException();
        }

        Node<E> node = last;
        E element = null;
        for (int i = 1; i < size - index; i++) {
            node = node.next;
            element = node.next.element;
        }
        node.next = node.next.next;

        size--;
        return element;
    }

    @Override
    public int size() {
        return size;
    }
}
