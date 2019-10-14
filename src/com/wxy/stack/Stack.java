package com.wxy.stack;

/**
 * <p>栈的定义接口</p>
 *
 * @author wxy
 * @version 1.0
 * @create 2019/10/13 17:16
 */
public interface Stack<E> {

    /** 清空当前栈 */
    void clearStack();

    /** 获取指定索引的值 */
    E get(int index);

    /** 栈是否为空 */
    boolean isEmpty();

    /** 获取当前栈顶元素 */
    E getTop();

    /** 在栈中插入新数据 */
    boolean push(E e);

    /** 删除栈顶元素 */
    E pop();

    /** 返回当前栈的大小 */
    int size();
}
