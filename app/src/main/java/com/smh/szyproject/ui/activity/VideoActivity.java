package com.smh.szyproject.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.gyf.immersionbar.ImmersionBar;
import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.other.widget.MyJzvdStd;

import butterknife.BindView;
import cn.jzvd.Jzvd;

/**
 * author : smh
 * date   : 2020/6/18 10:28
 * desc   :
 */
public class VideoActivity extends BaseActivity {
    @BindView(R.id.jz_video)
    MyJzvdStd myJzvdStd;
    private String url;
    @Override
    public int getLayoutId() {
        return R.layout.activity_jiaozi_video;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        ImmersionBar.setTitleBar(this, myJzvdStd);
        String  extra = getIntent().getStringExtra("extra");
        switch (extra){
            case "video1":
                url = "http://stream.vipniu.com/live/xunshi.m3u8";
                break;
            case "video2":
                url="http://pull.6133.net/live/lovelife230515_360p/playlist.m3u8";
                break;
            default:
                url="http://jzvd.nathen.cn/342a5f7ef6124a4a8faf00e738b8bee4/cf6d9db0bd4d41f59d09ea0a81e918fd-5287d2089db37e62345123a1be272f8b.mp4";
                break;
        }

        myJzvdStd.setUp(url
                , "");
//        Glide.with(this).load("http://jzvd-pic.nathen.cn/jzvd-pic/1bb2ebbe-140d-4e2e-abd2-9e7e564f71ac.png").into(myJzvdStd.thumbImageView);
        myJzvdStd.startVideo();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
        if (Jzvd.backPress()) {
            return;
        }
        super.onBackPressed();
    }

}
