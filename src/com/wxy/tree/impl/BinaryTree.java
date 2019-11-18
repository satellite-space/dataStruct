package com.wxy.tree.impl;

import com.wxy.tree.Tree;
import com.wxy.tree.node.BiTNode;

/**
 * <p>二叉树</p>
 *
 * @author wxy
 * @version 1.0
 * @create 2019/10/30 22:33
 */
public class BinaryTree<E> implements Tree<E> {

    private BiTNode<E> root;

    private int index = -1;

    public BinaryTree(Object[] elements, String definition) {
        this.root = createTree(elements, definition);
    }

    public BiTNode<E> createTree(Object[] elements, String definition) {
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

    private BiTNode<E> preDisCreate(Object[] elements, int i) {
        BiTNode<E> node = null;
        if (i < elements.length) {
            node = new BiTNode<>();
            node.setData((E) elements[i]);
            node.setlChild(preDisCreate(elements, 2*i+1));
            node.setrChild(preDisCreate(elements, 2*i+2));
        }
        return node;
    }

    private BiTNode<E> preCreate(Object[] elements) {
        BiTNode<E> node;
        index++;
        if (index < elements.length) {
            if ("#".equals(elements[index])) {
                return null;
            } else {
                node = new BiTNode<>();
                node.setData((E) elements[index]);
                node.setlChild(preCreate(elements));
                node.setrChild(preCreate(elements));
                return node;
            }
        }
        return null;
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
    public BiTNode<E> getRoot() {
        return this.root;
    }

    /**
     *  前序遍历(目前只输出结点数据)
     */
    public void preOrder(BiTNode node) {
        if (null != node) {
            System.out.print(node.getData() + ", ");
            preOrder(node.getlChild());
            preOrder(node.getrChild());
        }
    }

    /**
     *  中序遍历(目前只输出结点数据)
     */
    public void inOrder(BiTNode node) {
        if (null != node) {
            inOrder(node.getlChild());
            System.out.print(node.getData());
            inOrder(node.getrChild());
        }
    }

    /**
     *  前序遍历(目前只输出结点数据)
     */
    public void afterOrder(BiTNode node) {
        if (null != node) {
            afterOrder(node.getlChild());
            afterOrder(node.getrChild());
            System.out.print(node.getData());
        }
    }
}
