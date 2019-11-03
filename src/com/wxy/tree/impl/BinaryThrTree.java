package com.wxy.tree.impl;

import com.wxy.tree.Tree;
import com.wxy.tree.node.BiTNode;
import com.wxy.tree.node.BiThrNode;

/**
 * <p>二叉树</p>
 *
 * @author wxy
 * @version 1.0
 * @create 2019/10/30 22:33
 */
public class BinaryThrTree<E> implements Tree<E> {

    private BiThrNode<E> root;
    private BiThrNode<E> pre;
    private BiThrNode<E> head = new BiThrNode<>(null);
    private int index = -1;

    public BinaryThrTree(Object[] elements, String definition) {
        this.root = createTree(elements, definition);
        head.setlChild(root);
        pre = head;
        // 构建完成后，对其进行中序线索化
        inThreading(this.root);
        // 对头结点的后继结点进行操作
        BiThrNode node = getLastNode();
        node.setrTag(1);
        node.setrChild(head);
        head.setrChild(node);
    }

    private BiThrNode getLastNode() {
        BiThrNode node = root;
        while (node.getrChild() != null) {
            node = node.getrChild();
        }
        return node;
    }

    /**  中序线索化 */
    private void inThreading(BiThrNode<E> root) {
        if (null != root) {
            inThreading(root.getlChild());
            if (null == root.getlChild()) {
                root.setlTag(1);
                root.setlChild(pre);
            }
            if (null == pre.getrChild()) {
                pre.setrTag(1);
                pre.setrChild(root);
            }
            pre = root;
            inThreading(root.getrChild());
        }
    }

    public BiThrNode<E> createTree(Object[] elements, String definition) {
        if ("preOrd".equals(definition)) {
            // 有序数组
            root = preCreate(elements);
        } else if ("preDisOrd".equals(definition)) {
            // 将一个数组构建成二叉树
            root = preDisCreate(elements, 0);
        }
        // in after省略。实现方法大同小异
        return root;
    }

    private BiThrNode<E> preCreate(Object[] elements) {
        BiThrNode<E> node;
        index++;
        if (index < elements.length) {
            if ("#".equals(elements[index])) {
                return null;
            } else {
                node = new BiThrNode<>();
                node.setData((E) elements[index]);
                node.setlChild(preCreate(elements));
                node.setrChild(preCreate(elements));
                return node;
            }
        }
        return null;
    }

    private BiThrNode<E> preDisCreate(Object[] elements, int i) {
        BiThrNode<E> node = null;
        if (i < elements.length) {
            node = new BiThrNode<>();
            node.setData((E) elements[i]);
            node.setlChild(preDisCreate(elements, 2*i+1));
            node.setrChild(preDisCreate(elements, 2*i+2));
        }
        return node;
    }

    @Override
    public void clearTree() {
        this.root = null;
    }

    @Override
    public boolean isEmptyTree() {
        return root == null;
    }

    @Override
    public int getTreeDepth() {
        return this.root.getHeight();
    }

    @Override
    public BiThrNode<E> getRoot() {
        return this.root;
    }

    /** 线索化遍历 */
    public void inOrderTraverse_Thr() {
        BiThrNode<E> node = head.getlChild();
        while (node != head) {
            while (0 == node.getlTag()) {
                node = node.getlChild();
            }
            System.out.println(node.getData());
            while (node.getrTag() == 1 && node.getrChild() != head) {
                node = node.getrChild();
                System.out.println(node.getData());
            }
            node = node.getrChild();
        }
    }
}
