package com.smh.szyproject.test.fragment.bannerViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.other.utils.L;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * author : smh
 * date   : 2020/7/10 11:14
 * desc   :
 */
public class BannerViewPager extends BaseActivity implements OnItemClickListener {
    @BindView(R.id.banner)
    ConvenientBanner convenientBanner;

    private List<Integer> localImages = new ArrayList<Integer>();
    private List<String> networkImages;
    private String[] images = {
            "http://img2.3lian.com/2014/f2/37/d/40.jpg",
            "http://img2.3lian.com/2014/f2/37/d/39.jpg",
            "http://f.hiphotos.baidu.com/image/h%3D200/sign=1478eb74d5a20cf45990f9df460b4b0c/d058ccbf6c81800a5422e5fdb43533fa838b4779.jpg",
            "http://f.hiphotos.baidu.com/image/pic/item/09fa513d269759ee50f1971ab6fb43166c22dfba.jpg"
    };


    @Override
    public void onItemClick(int position) {
        Toast.makeText(this, "点击了第" + position + "个", Toast.LENGTH_SHORT).show();
        L.e("点击了第:" + position);
    }

    @Override
    public int getLayoutId() {
        return R.layout.test_banner_view_pager;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        getStatusBarConfig().statusBarDarkFont(true).init();
//        initLocal();
        initNet();
    }


    private void initNet() {
        networkImages = Arrays.asList(images);
        convenientBanner.setPages(
                new CBViewHolderCreator() {
                    @Override
                    public NetWorkImageHolderView createHolder(View itemView) {
                        return new NetWorkImageHolderView(BannerViewPager.this,itemView);
                    }

                    @Override
                    public int getLayoutId() {
                        return R.layout.test_item_localimage;
                    }
                }, networkImages)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.test_round_default, R.drawable.test_round_select})
                .setCanLoop(true)//是否自动翻页
                .setOnItemClickListener(this);
        convenientBanner.startTurning(3000);
    }


    private void initLocal() {
        initLocalDatas();
        convenientBanner.setPages(
                new CBViewHolderCreator() {
                    @Override
                    public LocalImageHolderView createHolder(View itemView) {
                        return new LocalImageHolderView(itemView);
                    }

                    @Override
                    public int getLayoutId() {
                        return R.layout.test_item_localimage;
                    }
                }, localImages)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.test_round_default, R.drawable.test_round_select})
                .setCanLoop(true)//是否自动翻页
                .setOnItemClickListener(this);
        convenientBanner.startTurning(3000);
    }

    private void initLocalDatas() {
        localImages.add(R.mipmap.test_poster1p);
        localImages.add(R.mipmap.test_poster2p);
        localImages.add(R.mipmap.test_poster3p);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //停止翻页
        convenientBanner.stopTurning();
    }
}
