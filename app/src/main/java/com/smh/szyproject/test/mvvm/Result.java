package com.smh.szyproject.test.mvvm;

import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;

import com.smh.szyproject.common.image.ImageLoader;

import java.util.List;

/**
 * author : smh
 * date   : 2020/4/3 13:32
 * desc   :
 */
public class Result extends BaseObservable {

    /**
     * data : []
     * code : 0
     * timeAt : 1585884228251
     * info : 设备信息已记录
     */

    private int code;
    private long timeAt;
    private String info;
    private List<?> data;
    private String imgUrl;

    @Bindable
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Bindable
    public long getTimeAt() {
        return timeAt;
    }

    public void setTimeAt(long timeAt) {
        this.timeAt = timeAt;
    }

    @Bindable
    public String getInfo() {
        return info;
    }

    @Bindable
    public void setInfo(String info) {
        this.info = info;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @BindingAdapter("bind:img")
    public static void setIamgeUrl(ImageView iv, String url) {
        ImageLoader.with(iv.getContext()).load(url).into(iv);
    }
}
