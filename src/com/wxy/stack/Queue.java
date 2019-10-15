package com.wxy.stack;

/**
 * <p>队列接口</p>
 *
 * @author wxy
 * @version 1.0
 * @create 2019/10/14 22:59
 */
public interface Queue<E> {

    /** 清空队列 */
    void clearQueue();

    /** 判断队列是否为空 */
    boolean isEmpty();

    /** 获取队头元素 */
    E getHead();

    /** 在队尾添加元素 */
    boolean add(E e);

    /** 删除队头元素 */
    E remove();

    /** 队列长度 */
    int size();
}
