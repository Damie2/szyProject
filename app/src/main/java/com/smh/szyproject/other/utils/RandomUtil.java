package com.smh.szyproject.other.utils;

import java.util.Random;

/**
 * Created by Administrator on 2017/6/22.
 */
public class RandomUtil {

    public static int getNumber(int start, int end) {
        return (int) (Math.random() * (end - start + 1) + start);
    }

    public static String getRandomNumberString(int length) { // length表示生成字符串的长度
        String base = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static boolean yesOrNo(double rate) {
        return Math.random() < rate ? true : false;
    }
}
