package com.wxy.tree.impl;

import com.wxy.tree.Tree;
import com.wxy.tree.node.HuffmanTreeNode;

import java.util.*;

/**
 * <p>
 *     赫夫曼树的实现
 *     以及赫夫曼编码解码
 * </p>
 *
 * @author wxy
 * @version 1.0
 * @create 2019/11/4 19:12
 */
public class HuffmanTree<E> implements Tree<E> {

    private String encodeString;
    private String decodeString;
    private HuffmanTreeNode<E> root;
    private static Set<HuffmanTreeNode> set;
    private Map<Character, String> charList = new HashMap<>();

    public HuffmanTree(String message) {
        set = new TreeSet<>();
        createHuffmanTree(message);
    }

    private void createHuffmanTree(String message) {
        convertChar(message);
        createTree();
        this.root = new ArrayList<>(set).get(0);
        prepareEncode(root, "");
        encode(message);
    }

    private void createTree() {
        // 当set中只剩一个元素时，此元素为root结点
        if (set.size() == 1){
            return;
        }

        HuffmanTreeNode l,r;
        l = new ArrayList<>(set).get(0);
        r = new ArrayList<>(set).get(1);
        set.remove(l);
        set.remove(r);

        HuffmanTreeNode node = new HuffmanTreeNode(null, l.getCount() + r.getCount());
        node.setlChild(l);
        node.setrChild(r);
        set.add(node);

        createTree();
    }

    /**
     *  将字符转换为带权的树结点
     * @param message
     */
    private void convertChar(String message) {
        Map<Character, Integer> charsMap = new HashMap<>();
        // 统计字符个数
        for (char c : message.toCharArray()) {
            if (charsMap.containsKey(c)) {
                charsMap.put(c, charsMap.get(c) + 1);
                continue;
            }
            charsMap.put(c, 1);
        }

        initTreeNode(charsMap);
    }

    /**
     *  将统计好的字符初始化为带权值的结点
     * @param charsMap 字符个数
     */
    private void initTreeNode(Map<Character, Integer> charsMap) {
        Iterator<Map.Entry<Character, Integer>> iterator = charsMap.entrySet().iterator();
        while (iterator.hasNext()) {
            HuffmanTreeNode node = new HuffmanTreeNode();
            Map.Entry<Character, Integer> next = iterator.next();
            node.setValue(next.getKey().toString());
            node.setCount(next.getValue());
            set.add(node);
        }
    }

    public String getEncodeString() {
        return encodeString;
    }

    @Override
    public void clearTree() {
        root = null;
    }

    @Override
    public boolean isEmptyTree() {
        return root == null;
    }

    @Override
    public int getTreeDepth() {
        return root.getHeight();
    }

    @Override
    public HuffmanTreeNode<E> getRoot() {
        return root;
    }

    private void encode(String message) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            result.append(charList.get(message.charAt(i)));
        }
        this.encodeString = result.toString();
    }

    /**
     *  前序遍历对所有结点进行赫夫曼编码
     * @param node
     * @param code
     */
    private void prepareEncode(HuffmanTreeNode node, String code) {
        if (node.isLeaf()) {
            charList.put(node.getValue().toCharArray()[0], code);
            return;
        }
        prepareEncode(node.getlChild(), code + "0");
        prepareEncode(node.getrChild(), code + "1");
    }

    public String getDecodeString(HuffmanTreeNode node, String code) {
        this.decodeString = "";
        decode(node, code);
        return this.decodeString;
    }

    private void decode(HuffmanTreeNode node, String code) {
        if (code.length() == 0) {
            this.decodeString += node.getValue();
            return;
        }

        if (node.isLeaf()) {
            this.decodeString += node.getValue();
            node = this.root;
        }

        if (code.charAt(0) == '0') {
            decode(node.getlChild(), code.substring(1));
        }

        if (code.charAt(0) == '1') {
            decode(node.getrChild(), code.substring(1));
        }
    }
}
