package com.smh.szyproject.test.smartRefresh;

import android.os.Bundle;

import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.other.utils.L;


import butterknife.BindView;

/**
 * author : smh
 * date   : 2020/11/19 14:39
 * desc   :
 */
public  class RefreshActivity extends BaseActivity {

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mSmartLayout;

    @Override
    public int getLayoutId() {
        return R.layout.test_activity_refresh;
    }

    @Override
    public void init(Bundle savedInstanceState) {
//        mSmartLayout.setEnableAutoLoadMore(true);//触发自动刷新功能

        mSmartLayout.autoRefresh();
        mSmartLayout.setOnRefreshListener((RefreshLayout refreshLayout)->{
            L.e("开始load数据啦");
            mSmartLayout.finishRefresh(500);
        });

        mSmartLayout.setOnLoadMoreListener((RefreshLayout refreshLayout)->{
            mSmartLayout.finishLoadMore(500);
        });

        //经典
//        mSmartLayout.setRefreshHeader(new ClassicsHeader(this));
//        mSmartLayout.setRefreshFooter(new ClassicsFooter(this));

        //设置 Header 为 贝塞尔雷达 样式
//        mSmartLayout.setRefreshHeader(new BezierRadarHeader(this).setEnableHorizontalDrag(true));
        //设置 Footer 为 球脉冲 样式
//        mSmartLayout.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));

//        //谷歌经典样式
//        mSmartLayout.setRefreshHeader(new MaterialHeader(this));

        //雷达刷新头
//        mSmartLayout.setRefreshHeader(new FalsifyHeader(this));
//        mSmartLayout.setRefreshHeader(new FalsifyFooter(this));


        //二级刷新头  这个跟经典刷新头没啥区别
//        mSmartLayout.setRefreshHeader(new TwoLevelHeader(this));


    }



}
