package com.smh.szyproject.test.dagger;

public class UserManager {
    private ApiService mApiService;
    private UserStore mUserStore;

    public UserManager(ApiService Service) {
        Service = mApiService;
    }

    public void register() {
        mApiService.register();
        mUserStore.register();
    }

}
