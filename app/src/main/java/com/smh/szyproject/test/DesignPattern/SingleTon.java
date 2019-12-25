package com.smh.szyproject.test.DesignPattern;

/**
 * 单例模式
 */
public class SingleTon {
    /**
     * 单例模式定义：一个类中只允许有一个实例，思路：让类的构造方法私有化，同时提供一个静态方法去实例化这个类
     *
     * 懒汉式，时间换空间，不推荐，线程不安全，不推荐，时间很重要
     *
     * 饿汉式，空间换时间，推荐(常用的那种方式)
     */

    /**
     * 定义私有构造方法，变成单例的核心
     */
    private SingleTon() {
    }

    //饿汉式，类加载的时候就去初始化
    private static final SingleTon single = new SingleTon();

    public static SingleTon getTeacher() {
        return single;
    }

    //分割线
    //双重校验
    private volatile static SingleTon myTest = null;

    public static SingleTon getMyTest() {
        if (myTest == null) {
            synchronized (SingleTon.class) {
                if (myTest == null) {
                    myTest = new SingleTon();
                }
            }
        }
        return myTest;
    }
}
