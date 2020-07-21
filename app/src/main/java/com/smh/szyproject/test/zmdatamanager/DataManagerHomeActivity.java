package com.smh.szyproject.test.zmdatamanager;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.common.base.BaseFragment;
import com.smh.szyproject.common.base.BaseFragmentAdapter;
import com.smh.szyproject.other.db.DBHelper;
import com.smh.szyproject.other.helper.ActivityStackManager;
import com.smh.szyproject.other.helper.DoubleClickHelper;
import com.smh.szyproject.other.utils.L;
import com.smh.szyproject.other.utils.SPUtil;
import com.smh.szyproject.test.zmdatamanager.bean.Product;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author : smh
 * date   : 2020/7/20 9:53
 * desc   :
 * <p>
 * 需求 管理所有订单、汇总利润
 * 一、经销商订单
 * 二、自己订单
 * 1、自己出售
 * 2、自吃
 * <p>
 * 详细设计
 * 1、提交整合所有的商品 商品列表页面和商品详情页面
 * 2、商品销售页面  三个状态 1、2、3
 * 根据状态的不同，调整销售利润
 */

public class DataManagerHomeActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.vp_home_pager)
    ViewPager mViewPager;
    @BindView(R.id.bv_home_navigation)
    BottomNavigationView mBottomNavigationView;

    private BaseFragmentAdapter<BaseFragment> mPagerAdapter;

    List<String> list = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        // 不使用图标默认变色
        mBottomNavigationView.setItemIconTintList(null);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);
        initDB();
//        initData();
    }

    protected void initData() {
        mPagerAdapter = new BaseFragmentAdapter<>(this);
        mPagerAdapter.addFragment(new TestFragment1());
        mPagerAdapter.addFragment(new TestFragment2());
        mPagerAdapter.addFragment(new TestFragment3());
        mPagerAdapter.addFragment(new TestFragment4());

        mViewPager.setAdapter(mPagerAdapter);

        // 限制页面数量
        mViewPager.setOffscreenPageLimit(mPagerAdapter.getCount());
    }

    @Override
    public void onBackPressed() {
        if (DoubleClickHelper.isOnDoubleClick()) {
            // 移动到上一个任务栈，避免侧滑引起的不良反应
            moveTaskToBack(false);
            mViewPager.postDelayed(() -> {

                // 进行内存优化，销毁掉所有的界面
                ActivityStackManager.getInstance().finishAllActivities();
                // 销毁进程（注意：调用此 API 可能导致当前 Activity onDestroy 方法无法正常回调）
                // System.exit(0);

            }, 300);
        } else {
            showToast("再按一次退出");
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_home:
                mPagerAdapter.setCurrentItem(TestFragment1.class);
                return true;
            case R.id.home_found:
                mPagerAdapter.setCurrentItem(TestFragment2.class);
                return true;
            case R.id.home_message:
                mPagerAdapter.setCurrentItem(TestFragment3.class);
                return true;
            case R.id.home_me:
                mPagerAdapter.setCurrentItem(TestFragment4.class);
                return true;
            default:
                break;
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        mViewPager.setAdapter(null);
        mBottomNavigationView.setOnNavigationItemSelectedListener(null);
        super.onDestroy();
    }

    //初始化数据库数据
    private void initDB() {
        boolean b = SPUtil.getBoolean("product", false, this);
        if (!b) {
            L.e("第一次，进来了");
            SPUtil.putBoolean("product", true, this);
            addData();
            Product product;
            for (String info : list) {
                String[] strings = info.split(",");
                String name = strings[0]; //名称
                float generalAgencyPrice = Float.parseFloat(strings[1]);//总代价
                float agentPrice = Float.parseFloat(strings[2]);//特代价
                float retail = Float.parseFloat(strings[3]);//零售价

                product = new Product(name, generalAgencyPrice, agentPrice, retail);
                DBHelper.getInstance().save(product);
            }
            initData();
        } else {
            initData();
        }
    }

    private void addData() {
        list.add("海南青柠檬3斤装23个左右,12.5,14.5,19");
        list.add("彩虹面40g9X6包原4紫1胡1,30,33,39");
        list.add("艾草面条40g×4包,30,33,39");
        list.add("海带苗3斤装,26,29,35");
        list.add("香蕉5斤装,23,25,29.9");
        list.add("云南水果玉米5斤装,21.5,23.5,29");
        list.add("小香薯5斤,26,28.5,35");
        list.add("新西兰佳沛奇异果12粒,66,69,79");
        list.add("河南紫皮大蒜,15.5,17,19.9");
        list.add("黑布林酸李子,14.5,16,19.9");
        list.add("火龙果金都一号,32,34,39.9");
        list.add("民勤蜜瓜6斤,18,20,25");
        list.add("黄桃罐头,23,25,29.9");
        list.add("山东栖霞红富士,23.5,25.5,29.9");
        list.add("翠冠梨,20.5,23,29");
        list.add("泰国椰青,46,49,59");
        list.add("黄金蟠桃4.5斤,29,32,39");
        list.add("咸蛋黄表芽饼干,38,41,49");
        list.add("优选米脂小米5斤装,43,47,57");
        list.add("东北干黑木耳200g2袋,23,25,29.9");
        list.add("鲜珥6朵陆运,23.5,25.5,29.9");
        list.add("鲜珥6朵空运,30.5,33,39.9");
        list.add("坚果礼盒,51,53,59.9");
        list.add("榴莲酥,33,37,43");
        list.add("榴莲酥礼袋,39,42,54");
        list.add("嗨呀蛋黄酥,29,32,39.9");
        list.add("海鸭蛋20枚,36,40,49.9");
        list.add("土鸡蛋24枚装,32,34.5,39.9");
        list.add("黄金蛋酥,33,35,39.9");
        list.add("广东雷州木瓜,22,24,29.9");
        list.add("山楂条,19,22,29");
        list.add("甘蔗红糖,22,24,29.9");
        list.add("金枪鱼松,31,33.5,39.9");
        list.add("咸甜巴旦木,24,26,29.9");
        list.add("椰蓉吐司,29,32,39");
        list.add("嘉兴私房粽10只装,46,49,59");
        list.add("古田鲜活耳2件6朵装(实发12朵),46,50,59.9");
        list.add("若羌灰枣,29,32,39");
        list.add("特级红枣礼盒,40,43,55");
        list.add("五色葡萄干,32,35,43");
        list.add("芒果干1斤,35,37,43");
        list.add("天日山小香薯枣,30,33,39");
        list.add("土耳其无花果干,29,32,39");
        list.add("嗨呀蛋黄酥,21,23,29");
        list.add("纸皮核桃500克*3袋,50,53,63");
        list.add("原味/黄味糯米锅巴,23,25,29.9");
        list.add("腰果+瓜子仁混合6袋,25,28,37");
        list.add("炒薄皮大核桃,25,28,38");
        list.add("香蕉片,19.5,22,29");
        list.add("鱿鱼丝200g2袋,42,45,55");
        list.add("嗨呀蛋黄酥12颗,36,39,49.9");
        list.add("鸭舌205g,57,61,75");
        list.add("榴莲酥12枚,39,42,54");
        list.add("七天杂粮粥,31,33.5,39.9");
        list.add("咸蛋黄腰果+成蛋黄瓜子仁,18,21,28");
        list.add("东北大米9斤(两袋装】,45,48,58");
        list.add("芝麻夹心头水海苔,36,39,49.9");
    }
}
