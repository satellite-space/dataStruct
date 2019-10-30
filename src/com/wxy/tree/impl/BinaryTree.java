package com.wxy.tree.impl;

import com.wxy.tree.Tree;
import com.wxy.tree.TreeNode;
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

    public BinaryTree() {}

    @Override
    public Tree<E> createTree(Object[] elements, String definition) {
        BinaryTree<E> tree = new BinaryTree<>();
        if ("pre".equals(definition)) {

        }
        return tree;
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
    public TreeNode<E> getRoot() {
        return this.root;
    }

    /**
     *  前序遍历(目前只输出结点数据)
     */
    public void preOrder(BiTNode node) {
        while (null != node) {
            System.out.println(node.getData());
            preOrder(node.getlChild());
            preOrder(node.getrChild());
        }
    }

    /**
     *  中序遍历(目前只输出结点数据)
     */
    public void inOrder(BiTNode node) {
        while (null != node) {
            System.out.println(node.getData());
            inOrder(node.getlChild());
            inOrder(node.getrChild());
        }
    }

    /**
     *  前序遍历(目前只输出结点数据)
     */
    public void afterOrder(BiTNode node) {
        while (null != node) {
            System.out.println(node.getData());
            afterOrder(node.getlChild());
            afterOrder(node.getrChild());
        }
    }
}
