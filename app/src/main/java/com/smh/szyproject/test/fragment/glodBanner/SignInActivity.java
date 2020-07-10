package com.smh.szyproject.test.fragment.glodBanner;

import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.common.image.ImageLoader;
import com.smh.szyproject.ui.view.MyLayoutManager;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Create by smh on 2018/11/23.
 */
public class SignInActivity extends BaseActivity {



    @BindView(R.id.rl_name_iv)
    public RelativeLayout rl_name_iv;
    @BindView(R.id.rl_club_sign)
    public RelativeLayout rlSign;
    @BindView(R.id.iv_user_club_icon)
    public ImageView icon;
    @BindView(R.id.rv_member_card)
    public RecyclerView rvMemgerCard;
    private MemgerCardAdapter adapter;

    @Override
    public void init(Bundle savedInstanceState) {
        ImageLoader.with(this).load(R.drawable.test_iv_un_login).into(icon);
        icon.setVisibility(View.GONE);
        getStatusBarConfig().setTitleBar(this, rl_name_iv);
        List<Integer> list = new ArrayList<>();
        list.add(R.drawable.test_iv_crown_card);//皇冠
        list.add(R.drawable.test_iv_diamonds_card);//钻石
        list.add(R.drawable.test_iv_platinum_card);//铂金

        adapter = new MemgerCardAdapter(list, this);
        rvMemgerCard.setLayoutManager(new MyLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvMemgerCard.setAdapter(adapter);
        rvMemgerCard.scrollToPosition(1);

    }

    @Override
    public int getLayoutId() {
        return R.layout.test_sing_in_activity;
    }

}
