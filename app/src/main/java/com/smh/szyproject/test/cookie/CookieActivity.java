package com.smh.szyproject.test.cookie;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;

public class CookieActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.test_activity_test;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        syncCookie();
        newCookis();
        newKanWO();
    }

    private void newKanWO() {
        WebView   webview = new WebView(this);
        webview.getSettings().setJavaScriptEnabled(true); // enable javascript


        CookieManager.setAcceptFileSchemeCookies(true);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.acceptCookie();
        String cookie = CookieManager.getInstance().getCookie("mylink");

        final Activity activity = this;

        webview.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
            }
        });
        webview.loadUrl("mylink");


//        ???在Lollipop之前,还需要使用CookieSyncManager中的附加静态方法

        //这个要结合上面  如果是5.0以前，要加上它
        CookieManager cookieManager1 = CookieManager.getInstance();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            CookieSyncManager.createInstance(this);
        }
        cookieManager1.setAcceptCookie(true);


    }

    //不知道成不成功
    private void newCookis() {
//        WebSettings webSettings= webView.getSettings();
//        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
//        settings.setAppCacheEnabled(false);
        //上面这个是设置缓存  可以试试

        synchronousWebCookies("","");

//        https://www.jianshu.com/p/5798dd3515ca
    }
    /**
     * 同步cookies
     */
    public void synchronousWebCookies(String url, String cookies){
        if (!TextUtils.isEmpty(url))
            if (!TextUtils.isEmpty(cookies)) {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
                    CookieSyncManager.createInstance(CookieActivity.this);//MyApplication.mContext传可用context即可
                }
                CookieManager cookieManager = CookieManager.getInstance();
                cookieManager.setAcceptCookie(true);
                cookieManager.removeSessionCookie();// 移除
                cookieManager.removeAllCookie();
                StringBuilder sbCookie = new StringBuilder();
                sbCookie.append("SESSION=" + cookies);//拼接sessionId，跟后台约好格式，例如我的是SESSION=1111111111这样传
                String cookieValue = sbCookie.toString();
                cookieManager.setCookie(url, cookieValue);//为url设置cookie
                CookieSyncManager.getInstance().sync();//同步cookie
                String newCookie = cookieManager.getCookie(url);
            }
    }


    private void syncCookie() {
        String cookieLogin = "123";
        CookieSyncManager.createInstance(this);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.removeAllCookie();
        cookieManager.setCookie("www.baidu.com就是地址", cookieLogin);// cookies是在HttpClient中获得的cookie
        CookieSyncManager.getInstance().sync();
    }
}
