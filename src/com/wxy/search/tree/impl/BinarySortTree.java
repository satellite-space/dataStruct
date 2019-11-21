package com.wxy.search.tree.impl;

import com.wxy.search.tree.basic.BiTNode;

/**
 * <p>二叉排序树(目前只实现对int类型的排序)</p>
 *
 * @author wxy
 * @version 1.0
 * @create 2019/11/18 21:43
 */
public class BinarySortTree {

    private BiTNode root;

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

    public BiTNode searchBTS(int key) {
        return searchBTS(this.root, null, key);
    }

    private BiTNode searchBTS(BiTNode node, BiTNode parent, int key) {
        if (null == node) {
            return parent;
        }

        if (key == node.getData()) {
            return node;
        } else if (key < node.getData()) {
            return searchBTS(node.getlChild(), node, key);
        } else {
            return searchBTS(node.getrChild(), node, key);
        }
    }

    public boolean insertBTS(int key) {
        BiTNode node = searchBTS(key);
        if (node != null && key == node.getData()) {
            return false;
        }

        BiTNode biTNode = new BiTNode(null, key, null);
        if (null == node) { // 根结点
            root = biTNode;
        } else if (key < node.getData()) {
            node.setlChild(biTNode);
        } else {
            node.setrChild(biTNode);
        }
        return true;
    }

    public boolean deleteBTS(int key) {
        return deleteBTS(this.root, key);
    }

    private boolean deleteBTS(BiTNode node, int key) {
        if (node == null) {
            return false;  // 不存在该数据
        }

        if (key == node.getData()) {
            return delete(node);
        } else if (key < node.getData()) {
            return deleteBTS(node.getlChild(), key);
        } else {
            return deleteBTS(node.getrChild(), key);
        }
    }

    private boolean delete(BiTNode node) {
        BiTNode s, p;
        if (null == node.getlChild()) {
            node = node.getrChild();
        } else if (null == node.getrChild()) {
            node = node.getlChild();
        } else {
            s = node;
            p = node.getlChild();
            while (null != p.getrChild()) {
                s = p;
                p = p.getrChild();
            }
            node.setData(p.getData());
            if (node != s) {
                s.setrChild(p.getlChild());
            } else {
                s.setlChild(p.getlChild());
            }
        }
        return true;
    }

    public void foreach() {
        inOrder(this.root);
    }

    private void inOrder(BiTNode node) {
        if (null == node) {
            return;
        }
        inOrder(node.getlChild());
        System.out.print(node.getData() + ", ");
        inOrder(node.getrChild());
    }
}
