package com.wxy.list.impl;

import com.wxy.list.List;

/**
 * <p>文件描述</p>
 *
 * @author wxy
 * @version 1.0
 * @create 2019/9/23 22:13
 */
public class ArrayList<E> implements List<E> {

    private int size;

    private Object[] elementData;

    private static final Object[] EMPTY_LIST = {};

    public ArrayList() {
        this.elementData = new Object[10];
    }

    @Override
    public List<E> initList() {
        return new ArrayList<>();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clearList() {
        for (int i = 0; i < size; i++){
            elementData[i] = null;
        }
        this.size = 0;
    }

    @Override
    public E get(int index) {
        if(index >= size) {
            throw new RuntimeException("下标越界");
        }
        return (E)this.elementData[index];
    }

    @Override
    public int indexOf(Object o) {
        if(o == null) {
            for(int i = 0; i < size; i++) {
                if(null == elementData[i]) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                System.out.println(elementData[i]);
                if(o.equals(elementData[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public boolean add(Object o) {
        elementData[size++] = o;
        return true;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new RuntimeException("下标越界");
        }

        E oldValue = (E) elementData[index];

        for (; index < size;) {
            elementData[index] = elementData[++index];
        }
        elementData[index] = null;
        size--;

        return oldValue;
    }

    @Override
    public int size() {
        return size;
    }
}
