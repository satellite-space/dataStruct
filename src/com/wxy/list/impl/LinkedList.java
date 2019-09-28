package com.wxy.list.impl;

import com.wxy.list.List;

/**
 * <p>文件描述</p>
 *
 * @author wxy
 * @version 1.0
 * @create 2019/9/28 22:30
 */
public class LinkedList<E> implements List<E> {

    private int size = 0;

    private Node<E> frist;

    private Node<E> last;

    private static class Node<E> {
        E data;
        Node<E> prior;
        Node<E> next;

        Node(E data, Node<E> prior, Node<E> next) {
            this.data = data;
            this.prior = prior;
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
            x.prior = null;
            x.data = null;
            x.next = null;
            x = next;
        }
        frist = last = null;
        size = 0;
    }

    @Override
    public E get(int index) {
        if (index < 0 || size <= index)
            throw new RuntimeException();

        Node<E> node = frist;
        for (int i = 0; i <index; i++) {
            node = node.next;
        }
        return node.data;
    }

    @Override
    public int indexOf(E element) {
        int index = 0;

        for (Node<E> node = frist; node != null; ) {
            if (element.equals(node.data))
                return index;
            node = node.next;
            index++;
        }
        return -1;
    }

    @Override
    public boolean add(E element) {
//        linkHead(element);
        linkLast(element);
        return false;
    }

    private void linkLast(E element) {
        Node<E> l = last;
        Node<E> node = new Node<>(element, l, null);
        last = node;
        if (l == null) {
            frist = node;
        } else {
            l.next = node;
        }
        size++;
    }

    /**
     *  头插法
     * @param element
     */
    private void linkHead(E element) {
        Node<E> f = frist;
        Node<E> node = new Node<>(element, null, f);
        frist = node;
        if (f == null) {
            last = node;
        } else {
            f.prior = node;
        }
        size++;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || size <= index)
            throw new RuntimeException();

        E data = null;
        if (index == 1) {
            data = frist.data;
            frist = frist.next;
        }

        Node<E> node = frist;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        data = node.data;
        node.prior.next = node.next;
        node.next = node.prior;
        return data;
    }

    @Override
    public int size() {
        return size;
    }
}
