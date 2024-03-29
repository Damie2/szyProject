package com.smh.szyproject.test.fragment.RYmainAddFragment;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseFragment;
import com.smh.szyproject.other.Rx.databus.RxBus;
import com.smh.szyproject.other.utils.AppUtils;
import com.smh.szyproject.other.utils.L;
import com.smh.szyproject.ui.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainFragment extends BaseFragment {

    @BindView(R.id.mTablayout)
    TabLayout mTablayout;
    @BindView(R.id.vp_content)
    ViewPager vp_content;
    private List<String> list = new ArrayList<>();
    String[] title = new String[5];
    private List<TabLayoutFragment> fragments = new ArrayList<>();


    @Override
    protected void init() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            // 透明状态栏
//            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }
//        getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        getStatusBarConfig().statusBarDarkFont(false).init();
        L.e("MainFragment init");
        mTablayout.setPadding(mTablayout.getPaddingLeft(), AppUtils.getStateBarHeight(getContext()), mTablayout.getPaddingRight(), mTablayout.getPaddingBottom());
        mTablayout.setSelectedTabIndicatorColor(Color.RED);
        mTablayout.setupWithViewPager(vp_content);



        list.add("北京");
        list.add("河北");
        list.add("河南");
        list.add("上海");
        for (int i = 0; i < list.size(); i++) {
            TabLayout.Tab tab = mTablayout.newTab().setText(list.get(i));
            mTablayout.addTab(tab);
            title[i] = list.get(i);
            TabLayoutFragment fragment = new TabLayoutFragment();
            Bundle bundle = new Bundle();
            bundle.putString("name", list.get(i));
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
        TabLayoutFragment[] fragments = new TabLayoutFragment[this.fragments.size()];
        vp_content.setAdapter(new ViewPagerAdapter(getFragmentManager(), this.fragments.toArray(fragments), title));
        vp_content.setOffscreenPageLimit(this.fragments.size() - 1);
        //设置默认的tab样式
        setTextCustomView(mTablayout.getTabAt(0));
        //这个是默认展示第几个
        mTablayout.getTabAt(0).select();
        mTablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setTextCustomView(tab);

                //设置第一页为选中状态时的tab文字颜色为红色   初始的时候要设置这个
                //在上面啊

//                View view=mTabLayout.getTabAt(0).getCustomView();
//                TextView textView=view.findViewById(R.id.tv_header);
//                textView.setTextColor(Color.RED);




                  //可以这么搞
//                View view=tab.getCustomView();
//                TextView textView=view.findViewById(R.id.tv_header);
//                textView.setTextColor(Color.RED);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.setCustomView(null);

//                View view=tab.getCustomView();
//                TextView textView=view.findViewById(R.id.tv_header);
//                textView.setTextColor(Color.BLUE);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public int getLayoutId() {
        return R.layout.test_fragment_main;
    }


    public void onDestroy() {
        super.onDestroy();

    }

    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            L.e("MainFragment 展示");
            getStatusBarConfig().statusBarDarkFont(false).init();
        }else{
            L.e("MainFragment 隐藏");
        }
    }


//    public void onHiddenChanged(boolean hidden) {
//        super.onHiddenChanged(hidden);
//        if (!hidden) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                // 透明状态栏
//                getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            }
//            getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//        }
//    }

    private void setTextCustomView(TabLayout.Tab tab) {
        TextView textView = new TextView(getActivity());
        float selectedSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics());
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, selectedSize);
        textView.setTextColor(getResources().getColor(R.color.red));
        textView.setText(tab.getText());
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        tab.setCustomView(textView);
    }
}
