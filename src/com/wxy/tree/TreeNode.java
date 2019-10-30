package com.wxy.tree;

/**
 * <p>根结点接口</p>
 *
 * @author wxy
 * @version 1.0
 * @create 2019/10/30 22:18
 */
public interface TreeNode<E> {

    /** 判断当前结点是否为叶子结点. */
    boolean isLeaf();

    /** 获取以当前结点为根结点的树的高度. */
    int getHeight();
}
