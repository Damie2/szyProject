package com.smh.szyproject.test.livebroadcast;

import android.media.AudioManager;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.gyf.immersionbar.ImmersionBar;
import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.other.utils.L;

import java.io.IOException;

import butterknife.BindView;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * author : smh
 * date   : 2020/6/9 14:27
 * desc   : 视频直播  只是简单的播放
 *
 * 拉流地址
 * http://stream.vipniu.com/live/xunshi.m3u8
 *
 *
 * https://github.com/bilibili/ijkplayer
 *
 * //参考这个
 * https://blog.csdn.net/y331271939/article/details/83217507
 */
public class LiveBroadcastActivity extends BaseActivity {
    private String url = "http://pull.6133.net/live/lovelife230515_360p/playlist.m3u8";
//    "http://stream.jiaoliu168.com/live/test.m3u8"

    @BindView(R.id.surface_view)
    SurfaceView surfaceView;
    private IjkMediaPlayer mPlayer;

    @Override
    public int getLayoutId() {
        return R.layout.test_activity_live_broadcast;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        ImmersionBar.setTitleBar(this, surfaceView);
        surfaceView.getHolder().addCallback(callback);
    }
    private void createPlayer() {
        if (mPlayer == null) {
            mPlayer = new IjkMediaPlayer();
            mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            try {
                mPlayer.setDataSource(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            mPlayer.prepareAsync();
        }
    }
    private void release() {
        if (mPlayer != null) {
            mPlayer.stop();
            mPlayer.release();
            mPlayer = null;
        }
        IjkMediaPlayer.native_profileEnd();
    }
    private SurfaceHolder.Callback callback = new SurfaceHolder.Callback() {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            createPlayer();
            mPlayer.setDisplay(surfaceView.getHolder());
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            //修复返回桌面就销毁的问题
//            if (surfaceView != null) {
//                L.e("surface销毁");
//                surfaceView.getHolder().removeCallback(callback);
//                surfaceView = null;
//            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        L.e("onDestroy,销毁");
        release();
    }

}
