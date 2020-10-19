package com.smh.szyproject.easyNet.request;

import com.hjq.http.config.IRequestApi;
import com.smh.szyproject.easyNet.bean.User;

/**
 * author : smh
 * date   : 2020/10/16 11:41
 * desc   :
 */
public class LoginApi implements IRequestApi {
    @Override
    public String getApi() {
        return "callLogin";
    }

    private User user;

    public LoginApi setAuthor(User user) {
        this.user = user;
        return this;
    }
}
