package com.smh.szyproject.test.fragment.bannerViewPager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.smh.szyproject.common.image.ImageLoader;

public class 
 ImageViewHolder extends Holder<Integer> {
    private ImageView imageView;
    Context context;

    public ImageViewHolder(View itemView,Context context) {
        super(itemView);
        this.context = context;
    }


    @Override
    protected void initView(View itemView) {
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
    }

    @Override
    public void updateUI(Integer data) {
//        imageView.setImageResource(data);
        ImageLoader.with(context).load(data).into(imageView);
    }
}