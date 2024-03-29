package com.smh.szyproject.test.fragment.bannerViewPager;

import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.smh.szyproject.R;

/**
 * Created by Sai on 15/8/4.
 * 本地图片Holder例子
 */
public class LocalImageHolderView extends Holder<Integer> {
    private ImageView imageView;

    public LocalImageHolderView(View itemView) {
        super(itemView);
    }

    @Override
    protected void initView(View itemView) {
        imageView =itemView.findViewById(R.id.ivPost);
        //xml在上面那一层，这里就直接findviewById就行了
    }

    @Override
    public void updateUI(Integer data) {
        imageView.setImageResource(data);
    }
}
