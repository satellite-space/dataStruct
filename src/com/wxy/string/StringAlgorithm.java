package com.wxy.string;

/**
 * <p>字符串匹配算法</p>
 *
 * @author wxy
 * @version 1.0
 * @create 2019/10/20 22:05
 */
public class StringAlgorithm {

    /** T串为非空串，pos为主串的起始查找位置，若S串存在T串，则返回其第一次出现的位置，否则返回-1 */
    public static int index(String s, String t, int pos) {
        int m = s.length();
        int n = t.length();
        int len = m - n;
        String sub = "";
        while (pos <= len) {
            sub = s.substring(pos, n);
            if (t.equals(sub)) {
                return pos;
            }
            pos++;
            n++;
        }
        return -1;
    }

    /** 朴素的匹配模式算法 */
    public static int simpleMatch(String s, String t, int pos) {
        int i = 0;
        int j = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
        }

        if (j == t.length()) {
            return i - j;
        }
        return -1;
    }

    /** KMP模式匹配算法 */
    public static int matchModelOfKMP(String s, String t, int pos) {
        int[] next = getNext(t);
        int i = 0;
        int j = -1;
        while (i < s.length() && j < t.length()) {
            if (j == -1 || s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }

        if (j == t.length()) {
            return i - j;
        }
        return -1;
    }

    /** 获取T串每一位对应的位置 */
    private static int[] getNext(String t) {
        int[] next = new int[t.length()];
        next[0] = -1;
        int i = 0; // i表示后一个字符的下标
        int j = -1; // j表示前一个字符的下标
        while (i < t.length() - 1) {
            if (j == -1 || t.charAt(i) == t.charAt(j)) {
                i++;
                j++;
                next[i] = j;
            } else {
                j = next[j];   // 若字符不匹配，则将当前的下标回溯到前一个j的下标
            }
        }
        return next;
    }
	
	/** 获取T串每一位对应的位置——优化 */
    private static int[] getNextVal(String t) {
        int[] next = new int[t.length()];
        next[0] = -1;
        int i = 0; // i表示后一个字符的下标
        int j = -1; // j表示前一个字符的下标
        while (i < t.length() - 1) {
            if (j == -1 || t.charAt(i) == t.charAt(j)) {
                i++;
                j++;
				if (t.charAt(i) != t.charAt(j)) {  // 若当前字符与前一个字符不相同
					next[i] = j;           // 则将当前的j值赋给数组的i的值
				} else {
					next[i] = next[j];     // 相同，则将当前j值回溯到上一个j的下标。
				}
                next[i] = j;
            } else {
                j = next[j];   // 若字符不匹配，则将当前的下标回溯到前一个j的下标
            }
        }
        return next;
    }
}
