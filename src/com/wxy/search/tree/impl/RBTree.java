package com.wxy.search.tree.impl;

import com.wxy.search.tree.basic.RBTNode;

/**
 * <p>红黑二叉树，只实现int</p>
 *
 * @author wxy
 * @version 1.0
 * @create 2019/11/24 18:54
 */
public class RBTree {
    private RBTNode root;

    public void clearTree() {
        root = null;
    }

    public boolean isEmptyTree() {
        return root == null;
    }

    public int getTreeDepth() {
        return root.getHeight();
    }

    public RBTNode getRoot() {
        return root;
    }

    /**
     *  判断结点是否是红色的
     * @param node 当前结点
     * @return 判断结果
     */
    private boolean isRed(RBTNode node) {
        return node == null ? false : node.isColor();
    }

    /**
     *  插入新结点
     * @param key 待插入的数据
     * @return 插入是否大成功
     */
    public boolean insert(int key) {
        root = insert(root, key);
        root.setColor(RBTNode.BLACK);
        return true;
    }

    private RBTNode insert(RBTNode root, int key) {
        if (null == root) {
            return new RBTNode(key, RBTNode.BLACK);
        }

        if (key < root.getData()) {
            root.setLeft(insert(root.getLeft(), key));
        } else {
            root.setRight(insert(root.getRight(), key));
        }

        if (isRed(root.getRight()) && !isRed(root.getLeft())) {
            root = rotateLeft(root);
        }
        if (isRed(root.getLeft()) && isRed(root.getLeft().getLeft())) {
            root = rotateRight(root);
        }
        if (isRed(root.getLeft()) && isRed(root.getRight())) {
            root = flipColors(root);
        }

        return root;
    }

    /**
     * 对以root为根结点的树进行左旋
     * @param root 待旋转树的结点
     * @return 处理结果
     */
    private RBTNode rotateLeft(RBTNode root) {
        RBTNode node = root.getRight();
        root.setRight(node.getLeft());
        node.setLeft(root);
        node.setColor(node.getLeft().isColor());
        node.getLeft().setColor(RBTNode.RED);
        return node;
    }

    /**
     * 对以root为根结点的树进行左旋
     * @param root 待旋转树的结点
     * @return 处理结果
     */
    private RBTNode rotateRight(RBTNode root) {
        RBTNode node = root.getLeft();
        root.setLeft(node.getRight());
        node.setRight(root);
        node.setColor(node.getRight().isColor());
        node.getRight().setColor(RBTNode.RED);
        return node;
    }

    /**
     * 对以root为根结点的树进行重新赋值
     * @param root 根结点
     * @return 处理结果
     */
    private RBTNode flipColors(RBTNode root) {
        if (root == null || null == root.getLeft() || null == root.getRight()) {
            throw new RuntimeException("待旋转结点必须有左右结点");
        }
        root.setColor(!root.isColor());
        root.getLeft().setColor(!root.getLeft().isColor());
        root.getRight().setColor(!root.getRight().isColor());
        return root;
    }

    public boolean delete(int key) {
        if (!isRed(root.getLeft()) && !isRed(root.getRight())) {
            root.setColor(RBTNode.RED);
        }
        root = delete(root, key);
        if (!isEmptyTree()) {
            root.setColor(RBTNode.BLACK);
        }
        return true;
    }

    private RBTNode delete(RBTNode root, int key) {
        if (key < root.getData()) {
            if (!isRed(root.getLeft()) && !isRed(root.getLeft().getLeft())) {
                root = moveRedLeft(root);
            }
            root.setLeft(delete(root.getLeft(), key));
        } else {
            if (isRed(root.getLeft())) {
                root = rotateRight(root);
            }
            if (key == 0 && root.getRight() == null) {
                return null;
            }
            if (!isRed(root.getRight()) && !isRed(root.getRight().getLeft())) {
                root = moveRedRight(root);
            }
            if (key == root.getData()) {
                root.setData(min(root.getRight()).getData());
                root.setRight(deleteMin(root.getRight()));
                // 用当前结点前序遍历的前一个结点替换
                // root.setData(max(root.getLeft()).getData());
                // root.setLeft(deleteMax(root.getLeft()));
            } else {
                root.setRight(delete(root.getRight(), key));
            }
        }
        return balance(root);
    }

    private RBTNode balance(RBTNode root) {
        if (!isRed(root.getLeft()) && isRed(root.getRight())) {
            root = rotateLeft(root);
        }
        if (isRed(root.getLeft()) && isRed(root.getLeft().getLeft())) {
            root = rotateRight(root);
        }
        if (isRed(root.getLeft()) && isRed(root.getRight())) {
            flipColors(root);
        }
        return root;
    }

    private RBTNode deleteMin(RBTNode node) {
        if (null == node.getLeft()) {
            return null;
        }
        if (!isRed(node.getLeft()) && !isRed(node.getLeft().getLeft())) {
            node = moveRedLeft(node);
        }
        node.setLeft(deleteMin(node.getLeft()));
        return balance(root);
    }

    private RBTNode deleteMax(RBTNode node) {
        if (isRed(node.getLeft()))
            node = rotateRight(node);
        if (node.getRight() == null) return null;

        if (!isRed(node.getRight()) && !isRed(node.getRight().getLeft()))
            node = moveRedRight(node);
        node.setRight(deleteMax(node.getRight()));
        return balance(node);
    }

    private RBTNode min(RBTNode node) {
        return node.getLeft() == null ? null : min(node.getLeft());
    }

    private RBTNode max(RBTNode node) {
        return node.getRight() == null ? null : max(node.getRight());
    }

    private RBTNode moveRedRight(RBTNode root) {
        flipColors(root);
        if (!isRed(root.getLeft().getLeft())) {
            root = rotateRight(root);
        }
        return root;
    }

    private RBTNode moveRedLeft(RBTNode root) {
        //假设h为红色，h.right 和h.right.left都是黑色（h.right和h.right.left都是2-结点）
        //将h.right 或h.right 的子结点之一变红（想办法变红，变为3-结点）
        flipColors(root);
        if (isRed(root.getRight().getLeft())) {
            root.setRight(rotateRight(root.getRight()));
            root = rotateLeft(root);
        }
        return root;
    }
}
