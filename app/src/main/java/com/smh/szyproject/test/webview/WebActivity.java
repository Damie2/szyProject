package com.smh.szyproject.test.webview;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Looper;
import android.provider.Settings;

import android.text.TextUtils;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.RelativeLayout;

import com.azhon.appupdate.manager.DownloadManager;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.DefaultHandler;
import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.other.utils.AppUtils;

import butterknife.BindView;

public class WebActivity extends BaseActivity {
    @BindView(R.id.m_web_view)
    public BridgeWebView webView;

    @BindView(R.id.rl_title)
    public RelativeLayout rl_title;
    ValueCallback<Uri> mUploadMessage;
    ValueCallback<Uri[]> mUploadMessageArray;

    @Override
    public int getLayoutId() {
        return R.layout.test_activity_web;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        rl_title.setPadding(rl_title.getPaddingLeft(), AppUtils.getStateBarHeight(this), rl_title.getPaddingRight(), rl_title.getPaddingBottom());
        initWebView();
    }

    /**
     * 初始化webView
     */
    private void initWebView() {
        webView.setDefaultHandler(new DefaultHandler());
        WebSettings settings = webView.getSettings();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        settings.setDomStorageEnabled(true);//支持对H5的加载
        settings.setBuiltInZoomControls(true);
        settings.setUseWideViewPort(true);
        settings.setJavaScriptEnabled(true);

        webView.setWebChromeClient(new WebChromeClient() {

            @SuppressWarnings("unused")
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String AcceptType, String capture) {
                this.openFileChooser(uploadMsg);
            }

            @SuppressWarnings("unused")
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String AcceptType) {
                this.openFileChooser(uploadMsg);
            }

            public void openFileChooser(ValueCallback<Uri> uploadMsg) {
                mUploadMessage = uploadMsg;
            }

            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
                mUploadMessageArray = filePathCallback;
                return true;
            }
        });
        webView.loadUrl("https://cn.Pornhub.com");
//        webView.loadUrl("https://www.baidu.com/");
    }



    @Override
    public void onBackPressed() {
//        super.onBackPressed();

            webView.goBack();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


}
