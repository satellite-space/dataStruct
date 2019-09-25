package com.wxy.list.impl;

import com.wxy.list.List;

/**
 * <p>文件描述</p>
 *
 * @author wxy
 * @version 1.0
 * @create 2019/9/24 22:39
 */
public class SingleLinkedList<E> implements List<E> {

    private int size;

    private Node<E> frist;

    public SingleLinkedList() {
    }

    /**
     * 私有节点
     * @param <E>
     */
    private static class Node<E> {
        E item;
        Node<E> next;

        Node(Node<E> next, E item) {
            this.item = item;
            this.next = next;
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clearList() {
        for (Node<E> x = frist; x != null; ) {
            Node<E> next = x.next;
            x.item = null;
            x.next = null;
            x = next;
        }
        frist = null;
        size = 0;
    }

    @Override
    public E get(int index) {
        if (index < 0 && size <= index) {
            throw new RuntimeException();
        }

        if (frist.next == null) {
            return frist.item;
        }

        Node<E> node = frist;
        for (int i = 1; i <= index; i++) {
            node = node.next;
        }
        return node.item;
    }

    @Override
    public int indexOf(E element) {
        Node<E> node = frist;
        for (int i = 0; i < size; i++) {
            if (node.item.equals(element)) {
                return i;
            }
            node = node.next;
        }
        return -1;
    }

    @Override
    public boolean add(E element) {
        linkFrist(element);
//        linkLast(element);
        return true;
    }

    /**
     * 头插法
     * @param element 待插入元素
     */
    private void linkFrist(E element) {
        Node<E> newValue = new Node<>(frist, element);
        if (null == frist) {
            frist = newValue;
            size++;
            return;
        }

        frist = newValue;
        size++;
    }

    /**
     * 尾插法
     * @param element 待插入元素
     */
    private void linkLast(E element) {
        Node<E> newValue = new Node<>(null, element);
        if (size == 0) {
            frist = newValue;
            size++;
            return;
        }

        Node<E> node = frist;
        for (Node<E> x = frist; x != null; ) {
            node = x;
            x = x.next;
        }
        node.next = newValue;
        size++;
    }

    @Override
    public E remove(int index) {
        if (index < 0 && size <= index) {
            throw new RuntimeException();
        }

        if (0 == index) {
            Node<E> node = frist;
            frist = frist.next;
            size--;
            return node.item;
        }

        Node<E> node = frist;
        Node<E> prev = frist;
        Node<E> last = frist;
        for (int i = 1; i <= index; i++) {
            prev = node;
            node = node.next;
            last = node.next;
        }
        prev.next = last;
        size--;
        return node.item;
    }

    @Override
    public int size() {
        return size;
    }
}
