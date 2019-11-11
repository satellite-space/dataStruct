package com.wxy.tree;

import com.wxy.tree.impl.BinaryThrTree;
import com.wxy.tree.impl.BinaryTree;
import com.wxy.tree.impl.HuffmanTree;

/**
 * <p>文件描述</p>
 *
 * @author wxy
 * @version 1.0
 * @create 2019/11/3 16:51
 */
public class TestMain {
    public static void main(String[] args) {
        // testBinaryTree();
        // testBinaryThrTree();
        testHuffman();
    }

    private static void testHuffman() {
        HuffmanTree tree = new HuffmanTree("goodgoodd");
        System.out.println(tree.getEncodeString());
        System.out.println(tree.getDecodeString(tree.getRoot(), tree.getEncodeString()));
    }

    private static void testBinaryThrTree() {
        Object[] arr = new Object[]{1, 2, 4, "#", "#", 5, "#", "#", 3, 6, "#", "#", 7, "#", "#"};
        arr = new Object[]{1, 2, 3, 4, 5, 6, 7};
//        BinaryThrTree tree = new BinaryThrTree(arr, "preOrd");
        BinaryThrTree tree = new BinaryThrTree(arr, "preDisOrd");
        tree.inOrderTraverse_Thr();
    }

    private static void testBinaryTree() {
        Object[] arr = new Object[]{1, 2, 4, "#", "#", 5, "#", "#", 3, 6, "#", "#", 7, "#", "#"};
        arr = new Object[]{1, 2, 3, 4, 5, 6, 7};
        BinaryTree tree = new BinaryTree(arr, "preOrd");
        tree = new BinaryTree(arr, "preDisOrd");
        tree.preOrder(tree.getRoot());
        tree.inOrder(tree.getRoot());
        tree.afterOrder(tree.getRoot());
    }
}
