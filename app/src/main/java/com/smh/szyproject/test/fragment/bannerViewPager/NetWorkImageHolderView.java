package com.smh.szyproject.test.fragment.bannerViewPager;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;

import com.smh.szyproject.R;
import com.smh.szyproject.common.image.ImageLoader;

/**
 * Created by Sai on 15/8/4.
 * 网络图片加载例子
 */
public class NetWorkImageHolderView extends Holder<String> {
    private ImageView imageView;
    Context context;
    public NetWorkImageHolderView(Context context, View itemView) {
        super(itemView);
        this.context = context;
    }

    @Override
    protected void initView(View itemView) {
        imageView =itemView.findViewById(R.id.ivPost);
    }

    @Override
    public void updateUI(String data) {
        ImageLoader.with(context).load(data).into(imageView);
    }
}
