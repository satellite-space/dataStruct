package com.wxy.search.tree.impl;

import com.wxy.search.tree.basic.BiTNode;

/**
 * <p>平衡二叉树(只实现int的排序)</p>
 *
 * @author wxy
 * @version 1.0
 * @create 2019/11/20 20:37
 */
public class AVLTree {

    private static final int LH = 1;
    private static final int EH = 0;
    private static final int RH = -1;
    private static final int MAX = 2147483647;

    private BiTNode root;
    private boolean taller;

    public AVLTree() {}

    public void clearTree() {
        root = null;
    }

    public boolean isEmptyTree() {
        return root == null;
    }

    public int getTreeDepth() {
        return root.getHeight();
    }

    public BiTNode getRoot() {
        return root;
    }

    /**
     * 将以node为根结点的树右旋
     * @param node 根结点
     * @return 右旋处理之后树的根结点
     */
    private BiTNode r_Rotate(BiTNode node) {
        BiTNode lTree = node.getlChild();
        node.setlChild(lTree.getrChild());
        lTree.setrChild(node);
        return lTree;
    }

    /**
     * 将以node为根结点的树左旋
     * @param node 根结点
     * @return 左旋处理之后树的根结点
     */
    private BiTNode l_Rotate(BiTNode node) {
        BiTNode rTree = node.getrChild();
        node.setrChild(rTree.getlChild());
        rTree.setlChild(node);
        return rTree;
    }

    /**
     * 对以node为根结点的最小不平衡树进行左平衡处理
     * @param node 根结点
     * @return 左平衡处理之后树的根结点
     */
    private BiTNode leftBalance(BiTNode node) {
        BiTNode lTree = node.getlChild();
        switch (lTree.getBf()) {
            case LH:
                lTree.setBf(EH);
                node.setBf(EH);
                node = r_Rotate(node);
                break;
            case RH:
                BiTNode lrTree = lTree.getrChild();
                switch (lrTree.getBf()) {
                    case LH:
                        node.setBf(RH);
                        lTree.setBf(EH);
                        break;
                    case EH:
                        node.setBf(EH);
                        lTree.setBf(EH);
                        break;
                    case RH:
                        node.setBf(EH);
                        lTree.setBf(LH);
                        break;
                }
                lrTree.setBf(EH);
                lTree = l_Rotate(lTree);
                node.setrChild(lTree);
                node = r_Rotate(node);
                break;
        }
        return node;
    }

    /**
     * 对以node为根结点的最小不平衡树进行右平衡处理
     * @param node 根结点
     * @return 右平衡处理之后树的根结点
     */
    private BiTNode rightBalance(BiTNode node) {
        BiTNode rTree = node.getrChild();
        switch (rTree.getBf()) {
            case RH:
                rTree.setBf(EH);
                node.setBf(EH);
                node = l_Rotate(node);
                break;
            case LH:
                BiTNode rlTree = rTree.getlChild();
                switch (rlTree.getBf()) {
                    case RH:
                        rTree.setBf(EH);
                        node.setBf(LH);
                        break;
                    case EH:
                        rTree.setBf(EH);
                        node.setBf(EH);
                        break;
                    case LH:
                        rTree.setBf(RH);
                        node.setBf(EH);
                        break;
                }
                rlTree.setBf(EH);
                rTree = r_Rotate(rTree);
                node.setrChild(rTree);
                node = l_Rotate(node);
                break;
        }
        return node;
    }

    /**
     * 在平衡二叉树中插入数据
     * @param keys 待插入数据
     * @return 插入结果
     */
    public boolean insertAVL(int[] keys) {
        for (int i = 0; i < keys.length; i++) {
            root = insertAVL(root, keys[i]);
        }
        return true;
    }

    /**
     * 在平衡二叉树中插入数据
     * @param tree 根结点
     * @param key 待插入数据
     * @return 插入数据之后树的根结点
     */
    private BiTNode insertAVL(BiTNode tree, int key) {
        if (null == tree) {
            tree = new BiTNode(key);
            taller = true;
        } else {
            if (key == tree.getData()) {
                taller = false;
                return null;
            } else if (key < tree.getData()) {
                BiTNode lNode = insertAVL(tree.getlChild(), key);
                if (null == lNode) {
                    return null;
                }
                tree.setlChild(lNode);
                if (taller) {
                    switch (tree.getBf()) {
                        case LH:
                            tree = leftBalance(tree);
                            taller = false;
                            break;
                        case EH:
                            tree.setBf(LH);
                            taller = true;
                            break;
                        case RH:
                            tree.setBf(EH);
                            taller = false;
                            break;
                    }
                }
            } else {
                BiTNode rNode = insertAVL(tree.getrChild(), key);
                if (null == rNode) {
                    return null;
                }
                tree.setrChild(rNode);
                if (taller) {
                    switch (tree.getBf()) {
                        case LH:
                            tree.setBf(EH);
                            taller = false;
                            break;
                        case EH:
                            tree.setBf(RH);
                            taller = true;
                            break;
                        case RH:
                            tree = rightBalance(tree);
                            taller = false;
                            break;
                    }
                }
            }
        }
        return tree;
    }

    public BiTNode deleteAVL(int key) {
        return deleteAVL(root, key);
    }

    private BiTNode deleteAVL(BiTNode root, int key) {
        if (null == root) {
            return null;
        }

        if (key == root.getData()) {
            if (root.getlChild() != null && root.getrChild() != null) {
                BiTNode node, tmp;
                node = root;
                tmp = root.getlChild();
                while (null != tmp.getrChild()) {
                    node = tmp;
                    tmp = tmp.getrChild();
                }
                root.setData(tmp.getData());
                if (node != root) {
                    node.setrChild(tmp.getlChild());
                } else {
                    node.setlChild(tmp.getlChild());
                }
            } else {
                root = (root.getlChild() != null) ? root.getlChild() : root.getrChild();
            }
        } else if (key < root.getData()) {
            root.setlChild(deleteAVL(root.getlChild(), key));
            int lh = (root.getlChild() != null) ? root.getlChild().getHeight() : 0;
            int rh = (root.getrChild() != null) ? root.getrChild().getHeight() : 0;
            if (rh - lh == 2) {
                root = rightBalance(root);
            }
        } else {
            root.setrChild(deleteAVL(root.getrChild(), key));
            int lh = (root.getlChild() != null) ? root.getlChild().getHeight() : 0;
            int rh = (root.getrChild() != null) ? root.getrChild().getHeight() : 0;
            if (lh - rh == 2) {
                root = leftBalance(root);
            }
        }
        return root;
    }

    public void inOrder() {
        inOrder(this.root);
    }

    private void inOrder(BiTNode node) {
        if (null == node) {
            return;
        }
        inOrder(node.getlChild());
        System.out.print(node.getData() + " ");
        inOrder(node.getrChild());
    }

    public void preOrder() {
        preOrder(this.root);
    }

    private void preOrder(BiTNode node) {
        if (null == node) {
            return;
        }
        System.out.print(node.getData() + " ");
        preOrder(node.getlChild());
        preOrder(node.getrChild());
    }
}
