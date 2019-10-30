package com.wxy.tree.node;

import com.wxy.tree.TreeNode;

/**
 * <p>二叉树(Binary Tree)——二叉链表结构</p>
 *
 * @author wxy
 * @version 1.0
 * @create 2019/10/30 21:47
 */
public class BiTNode<E> implements TreeNode<E> {

    private E data;

    private BiTNode<E> lChild;

    private BiTNode<E> rChild;

    public BiTNode(){}

    public BiTNode(BiTNode<E> lChild, E data, BiTNode<E> rChild) {
        this.lChild = lChild;
        this.data = data;
        this.rChild = rChild;
    }

    public void setData(E data) {
        this.data = data;
    }

    public E getData() {
        return data;
    }

    public void setlChild(BiTNode<E> lChild) {
        this.lChild = lChild;
    }

    public BiTNode<E> getlChild() {
        return lChild;
    }

    public void setrChild(BiTNode<E> rChild) {
        this.rChild = rChild;
    }

    public BiTNode<E> getrChild() {
        return rChild;
    }

    @Override
    public boolean isLeaf() {
        return !(null == this.lChild && null == this.rChild);
    }

    @Override
    public int getHeight() {
        return getHeight(this);
    }

    /**
     * 计算以当前结点为根结点树的高度.
     * @param node 结点
     * @return 高度
     */
    private int getHeight(BiTNode<E> node) {
        int height = 0;
        if (null != node) {
            height = 1 + Math.max(getHeight(node.lChild), getHeight(node.rChild));
        }
        return height;
    }
}
