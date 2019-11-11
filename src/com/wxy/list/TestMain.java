package com.wxy.list;

import com.wxy.list.impl.CircularSingleLinkedList;
import com.wxy.list.impl.LinkedList;
import com.wxy.list.impl.SingleLinkedList;

/**
 * <p>文件描述</p>
 *
 * @author wxy
 * @version 1.0
 * @create 2019/9/25 21:22
 */
public class TestMain {
    public static void main(String[] args) {
        List list = new SingleLinkedList<>();
//        list.add("test");
//        list.add("test1");
//        list.add("test2");
//        System.out.println(list.remove(0));
//        System.out.println(list.get(0));
//        System.out.println(list.get(1));
//        System.out.println(list.indexOf("test"));
//        System.out.println(list.indexOf("test2"));
//        list.clearList();
//        list.add("test");
//        System.out.println(list.size() + list.get(0));

        list = new LinkedList();
        list.add(15);
        list.add(16);
        list.add(17);
        list.add(18);
        System.out.println(list.size());
        System.out.println(list.get(0));
        list.remove(2);
        System.out.println(list.get(2));
    }
}
