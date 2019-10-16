package com.wxy.string;

/**
 * <p>字符串接口</p>
 *
 * @author wxy
 * @version 1.0
 * @create 2019/10/16 22:37
 */
public interface String {

    /** 生成其值等于chars的串T */
    String strAssign(char[] chars);

    String strCopy(String string);

    void clearString();

    boolean isEmpty();

    int length();

    int compare(String string);

    String concat(String string);

    String subString(int pos, int len);

    int index(String string, int pos);

    String replace(String str1, String str2);

    String insert(int pos, String string);

    String delete(int pos, int len);
}
