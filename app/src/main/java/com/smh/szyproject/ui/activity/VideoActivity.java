package com.smh.szyproject.ui.activity;

import android.os.Bundle;

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
    private String url = "http://pull.6133.net/live/lovelife230515_360p/playlist.m3u8";
    @Override
    public int getLayoutId() {
        return R.layout.test_activity_jiaozi_video;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        ImmersionBar.setTitleBar(this, myJzvdStd);
//        String  extra = getIntent().getStringExtra("extra");
//        switch (extra){
//            case "video1":
//                url = "http://stream.vipniu.com/live/xunshi.m3u8";
//                break;
//            case "video2":
//                url="http://pull.6133.net/live/lovelife230515_360p/playlist.m3u8";
//                break;
//            default:
//                break;
//        }

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
