package com.smh.szyproject.easyNet.bean;

/**
 * author : smh
 * date   : 2020/9/22 14:41
 * desc   :
 */
public class User {
    private String name;

    public String getPhone() {
        return name;
    }

    public void setPhone(String name) {
        this.name = name;
    }

    public User(String name) {
        this.name = name;
    }
}
