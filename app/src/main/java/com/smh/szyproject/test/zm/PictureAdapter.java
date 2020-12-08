package com.smh.szyproject.test.zm;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.smh.szyproject.R;
import com.smh.szyproject.common.base.CommonAdapter;
import com.smh.szyproject.common.base.ViewHolder;
import com.smh.szyproject.common.image.ImageLoader;

import java.util.List;

/**
 * author : smh
 * date   : 2020/7/15 11:06
 * desc   :
 */
public class PictureAdapter extends CommonAdapter<String> {
    Context context;

    public PictureAdapter(List<String> datas, Context context) {
        super(datas, context);
        this.context = context;
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.test_picture_item;
    }

    @Override
    protected void showItemContent(ViewHolder holder, int position, String s) {
        ImageView iv = holder.findViewById(R.id.iv_picture);
        ImageLoader.with(context).load(s).into(iv);


//        ImageLoader.with(context).load(s)
//                .placeholder(R.drawable.ic_launcher)
//                .error(R.drawable.article_2)
//                .into(iv);


        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.article_1)//图片加载出来前，显示的图片
                .fallback(R.drawable.article_2)//url为空的时候,显示的图片
                .error(R.drawable.aurora_anim_send_voice);//图片加载失败后，显示的图片

        Glide.with(context)
                .load(s) //图片地址
                .apply(options)
                .into(iv);
    }
}
