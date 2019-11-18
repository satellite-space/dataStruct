package com.wxy.search;

import com.wxy.search.tree.impl.BinarySortTree;

/**
 * <p>文件描述</p>
 *
 * @author wxy
 * @version 1.0
 * @create 2019/11/18 22:48
 */
public class Main {
    public static void main(String[] args) {
        testBinaryTree();
    }

    private static void testBinaryTree() {
        int[] arr = {1,15,6,25,24,28,7,9,16,10,12,13};
        BinarySortTree tree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            tree.insertBTS(arr[i]);
        }
        tree.foreach();
    }
}
