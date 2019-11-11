package com.wxy.string;

/**
 * <p>文件描述</p>
 *
 * @author wxy
 * @version 1.0
 * @create 2019/10/20 22:12
 */
public class Main {
    public static void main(String[] args) {
        String s = "goodgoogle";
        String t = "google";
        int i = StringAlgorithm.index(s, t, 0);
        i = StringAlgorithm.simpleMatch(s, t, 0);
        i = StringAlgorithm.matchModelOfKMP(s, t, 0);
        System.out.println(i);
    }
}
