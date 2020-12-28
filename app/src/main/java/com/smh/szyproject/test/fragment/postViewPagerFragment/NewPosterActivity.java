package com.smh.szyproject.test.fragment.postViewPagerFragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;


import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.common.base.CommonAdapter;
import com.smh.szyproject.other.utils.FileUtil;
import com.smh.szyproject.other.utils.L;
import com.smh.szyproject.ui.adapter.ViewPagerAdapter;
import com.smh.szyproject.ui.view.MyLayoutManager;
import com.tencent.mm.opensdk.openapi.IWXAPI;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class NewPosterActivity extends BaseActivity implements ViewPager.OnPageChangeListener, PosterFragment.getBitmapListener {

    PosterAdapter adapter;
    private int position;

    @BindView(R.id.rv_middle)
    RecyclerView rv_middle;

    @BindView(R.id.vp_poster)
    ViewPager vp_content;
    private List<PosterFragment> fragments = new ArrayList<>();

    @Override
    public void init(Bundle savedInstanceState) {
        for (int i = 0; i < 3; i++) {
            PosterFragment fragment = new PosterFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("id", i);
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
        L.e("???");
        vp_content.addOnPageChangeListener(this);
        PosterFragment[] fragments = new PosterFragment[this.fragments.size()];
        vp_content.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), this.fragments.toArray(fragments), null));
        vp_content.setOffscreenPageLimit(this.fragments.size() - 1);
        initRv();
    }

    //RecyclerView 初始化
    public void initRv() {
        List<Integer> list = new ArrayList<>();
        list.add(R.mipmap.test_poster1p);//第一张图
        list.add(R.mipmap.test_poster2p);//第二张图
        list.add(R.mipmap.test_poster3p);//第三张图
        adapter = new PosterAdapter(list, this);
        rv_middle.setLayoutManager(new MyLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rv_middle.setAdapter(adapter);
        adapter.setOnClickLinster((int position, View view) -> {
            int old = adapter.getSelectPosiion();//先拿到默认的-1位置
            adapter.setSelectPosiion(position);//adapter中将当前选中的设置为选中的位置
            adapter.notifyItemChanged(position);//更新选中的位置
            adapter.notifyItemChanged(old);//更新旧位置
            vp_content.setCurrentItem(position);
        });
//        adapter.setOnClickLinster(new CommonAdapter.OnItemClickLinster() {
//            @Override
//            public void onItemClick(int position, View view) {
//                int old = adapter.getSelectPosiion();
//                adapter.setSelectPosiion(position);
//                adapter.notifyItemChanged(position);
//                adapter.notifyItemChanged(old);
//                vp_content.setCurrentItem(position);
//            }
//        });
        adapter.setSelectPosiion(0);
        adapter.notifyItemChanged(0);
    }

    @Override
    public int getLayoutId() {
        return R.layout.test_activity_new_poster;
    }

    @Override
    public void getBitMap(Bitmap bitmap, String msg) {
        if (bitmap != null) {
            switch (msg) {
                case "saveAlbum"://保存到相册
                    File file = FileUtil.saveBitmap(bitmap, Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "cache", 100);
                    if (file.exists()) {
                        Intent mediaScanIntent = new Intent(
                                Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                        Uri contentUri = Uri.fromFile(file);
                        mediaScanIntent.setData(contentUri);
                        this.sendBroadcast(mediaScanIntent);
                    }
                    break;
                case "shareMoments"://分享到朋友圈
                    break;
                case "shareFriend"://分享给好友
                    break;
            }
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        this.position = position;
        adapter.setSelectPosiion(position);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
