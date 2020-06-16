package com.smh.szyproject.test.voiceAndVideo.voice;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.other.utils.L;

import java.io.File;

/**
 * author : smh
 * date   : 2020/6/2 13:54
 * desc   :
 */
class voice extends BaseActivity {

    private MediaPlayer mediaPlayer;

    @Override
    public int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    public void init(Bundle savedInstanceState) {

    }

    public void play() {
        String filepath ="";
        File file = new File(filepath);
        if(file.exists()){
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setDataSource(filepath);//设置播放的数据源。
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.prepare();//准备开始播放 播放的逻辑是c代码在新的线程里面执行。
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                L.e("播放失败");
            }
        }else{
            L.e("文件不存在，请检查文件的路径");
        }
    }
    /**
     * 暂停
     */
    public void pause() {
        if(mediaPlayer!=null&&mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
    }
    /**
     * 停止
     */
    public void stop() {
        if(mediaPlayer!=null&&mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }

    }
    /**
     * 重播
     */
    public void replay() {
        if(mediaPlayer!=null&&mediaPlayer.isPlaying()){
            mediaPlayer.seekTo(0);
        }else{
            play();
        }
    }

}
