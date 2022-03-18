package com.smh.szyproject.other.utils;

/**
 * @Author smh
 * @Date 2022/3/1 11:43
 */
public class NameUtil {

    /**
     * 定义所有常量
     */
    public static final String EMPTY = "";
    public static final int ZERO = 0;
    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final int THREE = 3;
    public static final int FOUR = 4;

    /**
     * @param str
     * @param len
     * @return java.lang.String
     * @Description 字符串向左截取
     */
    public static String left(String str, int len) {
        if (str == null) {
            return null;
        }
        if (len < ZERO) {
            return EMPTY;
        }
        if (str.length() <= len) {
            return str;
        }
        return str.substring(ZERO, len);

    }


    /**
     * @param str
     * @param len
     * @return java.lang.String
     * @Description 字符串向右截取
     */
    public static String right(String str, int len) {
        if (str == null) {
            return null;
        }
        if (len < ZERO) {
            return EMPTY;
        }
        if (str.length() <= len) {
            return str;
        }
        return str.substring(str.length() - len);

    }

    /**
     * @param str
     * @return java.lang.String
     * @Description 根据不同名字的长度返回不同的显示数据
     */
    public static String checkNameLength(String str) {
        if (str == null) {
            return null;
        }
        if (str.length() == ONE) {
            return str;
        } else if (str.length() == TWO) {
            return "*" + NameUtil.right(str, ONE);
        } else if (str.length() == THREE) {
            return NameUtil.left(str, ONE) + "*" + NameUtil.right(str, ONE);
        } else if (str.length() == FOUR) {
            return NameUtil.left(str, ONE) + "**" + NameUtil.right(str, ONE);
        }
        return str;
    }
}
