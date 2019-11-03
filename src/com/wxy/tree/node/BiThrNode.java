package com.wxy.tree.node;

import com.wxy.tree.TreeNode;

/**
 * <p>线索化二叉树结点</p>
 *
 * @author wxy
 * @version 1.0
 * @create 2019/11/3 17:46
 */
public class BiThrNode<E> implements TreeNode<E> {

    private E data;
    private BiThrNode<E> lChild;
    private BiThrNode<E> rChild;
    private int lTag = 0;   // 标志为0时，指向左孩子，标志为1时，指向前驱元素
    private int rTag = 0;   // 标志为0时，指向右孩子，标志为1时，指向后继元素

    public BiThrNode() {}

    public BiThrNode(E data) {
        this.data = data;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public BiThrNode<E> getlChild() {
        return lChild;
    }

    public void setlChild(BiThrNode<E> lChild) {
        this.lChild = lChild;
    }

    public BiThrNode<E> getrChild() {
        return rChild;
    }

    public void setrChild(BiThrNode<E> rChild) {
        this.rChild = rChild;
    }

    public int getlTag() {
        return lTag;
    }

    public void setlTag(int lTag) {
        this.lTag = lTag;
    }

    public int getrTag() {
        return rTag;
    }

    public void setrTag(int rTag) {
        this.rTag = rTag;
    }

    @Override
    public boolean isLeaf() {
        return lTag == 1 && rTag == 1;
    }

    @Override
    public int getHeight() {
        return getHeight(this);
    }

    private int getHeight(BiThrNode<E> node) {
        int height = 0;
        if (!node.isLeaf()) {
            height = 1 + Math.max(getHeight(node.lChild), getHeight(node.rChild));
        }
        return height;
    }
}
