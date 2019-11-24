package com.wxy.search.tree.basic;

/**
 * <p>红黑树结点</p>
 *
 * @author wxy
 * @version 1.0
 * @create 2019/11/24 18:41
 */
public class RBTNode {
    public static final boolean RED = true;
    public static final boolean BLACK = false;

    private RBTNode left;
    private RBTNode right;
    private int data;
    private boolean color = BLACK;

    public RBTNode(int data, boolean color) {
        this.data = data;
        this.color = color;
    }

    public RBTNode getLeft() {
        return left;
    }

    public void setLeft(RBTNode left) {
        this.left = left;
    }

    public RBTNode getRight() {
        return right;
    }

    public void setRight(RBTNode right) {
        this.right = right;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public boolean isColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

    public int getHeight() {
        return getHeight(this);
    }

    private int getHeight(RBTNode node) {
        int height = 0;
        if (node != null) {
            height = 1 + Math.max(getHeight(node.getLeft()), getHeight(node.getRight()));
        }
        return height;
    }
}
