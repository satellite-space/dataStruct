package com.wxy.tree;

/**
 * <p>树接口</p>
 *
 * @author wxy
 * @version 1.0
 * @create 2019/10/30 21:39
 */
public interface Tree<E> {
    /** 按照规定的定义去构造树，由实现类的构造方法去实现 */
    // Tree<E> createTree(Object[] elements, String definition);

    /** 若当前树存在，则清空树 */
    void clearTree();

    /** 若树为空树，则返回true，否则返回false */
    boolean isEmptyTree();

    /** 返回树的深度 */
    int getTreeDepth();

    /** 返回树的根结点 */
    TreeNode<E> getRoot();
}
