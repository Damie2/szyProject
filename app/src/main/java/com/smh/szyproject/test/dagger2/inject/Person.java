package com.smh.szyproject.test.dagger2.inject;

import javax.inject.Inject;

//第一步 添加@Inject
public class Person {
    private String name;

    /**
     * 首先为Person构造方法添加@Inject,指定Dagger在为我们初始化Person变量时要调用的构造函数
     */
    //这个是指定Dagger2初始化时调用的Person
    @Inject
    public Person(){
        name="默认name123";
    }

    public Person(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
