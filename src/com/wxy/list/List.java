package com.wxy.list;

/**
 * <p>List接口顶层</p>
 *
 * @author wxy
 * @version 1.0
 * @create 2019/9/23 22:12
 */
public interface List<E> {

    List initList();

    boolean isEmpty();

    void clearList();

    E get(int index);

    int indexOf(E element);

    boolean add(E element);

    E remove(int index);

    int size();
}
