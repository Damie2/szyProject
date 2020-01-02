package com.smh.szyproject.test.dagger2.module;

/**
 * author : smh
 * date   : 2019/12/27 13:53
 * desc   : 假设当前有个User类来自于开源库，构造函数没有@Inject
 *          这个是第一步,啥都不改
 */
public class User {
    private String name;

    public User(){
        this.name = "默认的名字";
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
