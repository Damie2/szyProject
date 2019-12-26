package com.smh.szyproject.test.mvvm;

public class User {
    private String UserName;
    private String UserSex;

   public  User(String UserName,String UserSex){
        this.UserName = UserName;
        this.UserSex =UserSex;
    }
    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserSex() {
        return UserSex;
    }

    public void setUserSex(String userSex) {
        UserSex = userSex;
    }
}
