package com.smh.szyproject.test.MagicIndicator;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.viewpager.widget.ViewPager;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.test.MagicIndicator.navigator.ScaleCircleNavigator;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.circlenavigator.CircleNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * author : smh
 * date   : 2020/3/30 15:04
 * desc   :
 */
public class MagicIndicatorActivity extends BaseActivity {

    private static final String[] CHANNELS = new String[]{"KITKAT", "NOUGAT", "DONUT"};
    private List<String> mDataList = Arrays.asList(CHANNELS);

    private ExamplePagerAdapter mExamplePagerAdapter = new ExamplePagerAdapter(mDataList);

    @BindView(R.id.view_pager)
    public  ViewPager mViewPager;

   @BindView(R.id.magic_indicator)
   public  MagicIndicator magicIndicator;

    @Override
    public int getLayoutId() {
        return R.layout.test_activity_magicindicator;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        mViewPager.setAdapter(mExamplePagerAdapter);
//        initMagicIndicator1();
//        initMagicIndicator2();
        initMagicIndicator3();
    }
    private void initMagicIndicator1() {
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
                simplePagerTitleView.setText(mDataList.get(index));
                simplePagerTitleView.setNormalColor(Color.parseColor("#88ffffff"));
                simplePagerTitleView.setSelectedColor(Color.WHITE);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewPager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setColors(Color.parseColor("#40c4ff"));
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        LinearLayout titleContainer = commonNavigator.getTitleContainer(); // must after setNavigator
        titleContainer.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        titleContainer.setDividerPadding(UIUtil.dip2px(this, 15));
        titleContainer.setDividerDrawable(getResources().getDrawable(R.drawable.simple_splitter));
        ViewPagerHelper.bind(magicIndicator, mViewPager);
    }


    private void initMagicIndicator2() {
        CircleNavigator circleNavigator = new CircleNavigator(this);
        circleNavigator.setFollowTouch(false);
        circleNavigator.setCircleCount(CHANNELS.length);
        circleNavigator.setCircleColor(Color.RED);
        circleNavigator.setCircleClickListener(new CircleNavigator.OnCircleClickListener() {
            @Override
            public void onClick(int index) {
                mViewPager.setCurrentItem(index);
            }
        });
        magicIndicator.setNavigator(circleNavigator);
        ViewPagerHelper.bind(magicIndicator, mViewPager);
    }

    private void initMagicIndicator3() {
        ScaleCircleNavigator scaleCircleNavigator = new ScaleCircleNavigator(this);
        scaleCircleNavigator.setCircleCount(CHANNELS.length);
        scaleCircleNavigator.setNormalCircleColor(Color.LTGRAY);
        scaleCircleNavigator.setSelectedCircleColor(Color.DKGRAY);
        scaleCircleNavigator.setCircleClickListener(new ScaleCircleNavigator.OnCircleClickListener() {
            @Override
            public void onClick(int index) {
                mViewPager.setCurrentItem(index);
            }
        });
        magicIndicator.setNavigator(scaleCircleNavigator);
        ViewPagerHelper.bind(magicIndicator, mViewPager);
    }

}
