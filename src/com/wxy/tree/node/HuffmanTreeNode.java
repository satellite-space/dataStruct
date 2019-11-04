package com.wxy.tree.node;

import com.wxy.tree.TreeNode;

import java.util.Objects;

/**
 * <p>赫夫曼树结点</p>
 *
 * @author wxy
 * @version 1.0
 * @create 2019/11/4 18:48
 */
public class HuffmanTreeNode<E> implements TreeNode<E>, Comparable<HuffmanTreeNode> {

    private String value;
    private int count;
    private HuffmanTreeNode<E> lChild;
    private HuffmanTreeNode<E> rChild;

    public HuffmanTreeNode(){}

    public HuffmanTreeNode(String value, int count){
        this.value = value;
        this.count = count;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public HuffmanTreeNode<E> getlChild() {
        return lChild;
    }

    public void setlChild(HuffmanTreeNode<E> lChild) {
        this.lChild = lChild;
    }

    public HuffmanTreeNode<E> getrChild() {
        return rChild;
    }

    public void setrChild(HuffmanTreeNode<E> rChild) {
        this.rChild = rChild;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HuffmanTreeNode<?> that = (HuffmanTreeNode<?>) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public boolean isLeaf() {
        return lChild == null && rChild == null;
    }

    @Override
    public int getHeight() {
        return getHeight(this);
    }

    private int getHeight(HuffmanTreeNode node) {
        int height = 0;
        if (null != node) {
            height = 1 + Math.max(getHeight(node.getlChild()), getHeight(node.getrChild()));
        }
        return height;
    }

    @Override
    public int compareTo(HuffmanTreeNode o) {
        return this.count - o.count;
    }
}
