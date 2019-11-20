package com.wxy.search.tree.basic;

/**
 * <p>二叉树(Binary Tree)——二叉链表结构</p>
 *
 * @author wxy
 * @version 1.0
 * @create 2019/10/30 21:47
 */
public class BiTNode {

    private int data;

    private int bf;

    private BiTNode lChild;

    private BiTNode rChild;

    public BiTNode(){}

    public BiTNode(int data) {
        this.bf = 0;
        this.lChild = this.rChild = null;
        this.data = data;
    }

    public BiTNode(BiTNode lChild, int data, BiTNode rChild) {
        this.lChild = lChild;
        this.data = data;
        this.rChild = rChild;
    }

    public void setData(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public int getBf() {
        return bf;
    }

    public void setBf(int bf) {
        this.bf = bf;
    }

    public void setlChild(BiTNode lChild) {
        this.lChild = lChild;
    }

    public BiTNode getlChild() {
        return lChild;
    }

    public void setrChild(BiTNode rChild) {
        this.rChild = rChild;
    }

    public BiTNode getrChild() {
        return rChild;
    }

    public boolean isLeaf() {
        return !(null == this.lChild && null == this.rChild);
    }

    public int getHeight() {
        return getHeight(this);
    }

    /**
     * 计算以当前结点为根结点树的高度.
     * @param node 结点
     * @return 高度
     */
    private int getHeight(BiTNode node) {
        int height = 0;
        if (null != node) {
            height = 1 + Math.max(getHeight(node.lChild), getHeight(node.rChild));
        }
        return height;
    }
}
