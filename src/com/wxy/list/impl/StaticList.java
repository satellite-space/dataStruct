package com.wxy.list.impl;

import com.wxy.list.List;

/**
 * <p>文件描述</p>
 *
 * @author wxy
 * @version 1.0
 * @create 2019/9/26 22:35
 */
public class StaticList<E> implements List<E> {

    private static final int MAX_SIZE = 1000;

    private static final Node[] NODES = new Node[MAX_SIZE];

    static class Node {
        Object data;
        int cur;
    }

    private StaticList() {}

    public static StaticList.Node[] initList() {
        Node node = null;
        for (int i = 0; i < MAX_SIZE - 1; i++) {
            node.cur = i+1;
        }
        NODES[MAX_SIZE - 1].cur = 0;

        return NODES;
    }

    @Override
    public boolean isEmpty() {
        int j = MAX_SIZE - 1;
        for (int i = 0; i < MAX_SIZE - 1; i++) {
            if (null != NODES[j].data) {
                return false;
            }
            j = NODES[j].cur;
        }
        return true;
    }

    @Override
    public void clearList() {
        int j = MAX_SIZE - 1;
        for (int i = 0; i < MAX_SIZE - 1; i++) {
            if (null != NODES[j].data) {
                NODES[j].data = null;
            }
            j = NODES[j].cur;
        }
    }

    @Override
    public E get(int index) {
        return (E) NODES[index].data;
    }

    @Override
    public int indexOf(E element) {
        for (int i = 1; i < MAX_SIZE - 1; i++) {
            if (element.equals(NODES[i].data)) {
                return i;
            }
        }
        return -1;
    }

    @Deprecated
    @Override
    public boolean add(E element) {
        int i = getNextNodeCur();
        NODES[i].data = element;
        return true;
    }

    public boolean add(E element, int index) {
        int i = getNextNodeCur();
        int k = MAX_SIZE - 1;
        for (int j = 1; j < index - 1; j++) {
            k = NODES[k].cur;
        }

        // 交换插入节点前后的游标
        NODES[i].data = element;
        NODES[i].cur = NODES[k].cur;
        NODES[k].cur = i;
        return true;
    }

    /**
     *  获取下一个可以存放元素的节点的游标
     * @return
     */
    public int getNextNodeCur() {
        int i = NODES[0].cur;

        if (i < MAX_SIZE - 1) {
            NODES[0].cur = NODES[i].cur;
            return i;
        }
        return -1;
    }

    /**
     *  释放一个当前节点
     * @return
     */
    public int freeThisNode(int index) {
        NODES[index].cur = NODES[0].cur;
        NODES[0].cur = index;
        return -1;
    }

    @Override
    public E remove(int index) {
        int k = MAX_SIZE - 1;
        for (int i = 1; i < index - 1; i++) {
            k = NODES[k].cur;
        }
        Object obj = NODES[k].data;
        freeThisNode(k);
        return (E) obj;
    }

    @Override
    public int size() {
        int count = 0;
        int j = MAX_SIZE - 1;
        for (int i = 1; i < MAX_SIZE - 1 ; i++) {
            if (null != NODES[j].data) {
                count++;
            }
            j = NODES[j].cur;
        }
        return count;
    }
}
