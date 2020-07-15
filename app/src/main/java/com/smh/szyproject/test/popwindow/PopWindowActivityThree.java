package com.smh.szyproject.test.popwindow;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.other.utils.L;
import com.smh.szyproject.other.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author : smh
 * date   : 2020/7/14 16:43
 * desc   :
 */
public class PopWindowActivityThree extends BaseActivity implements View.OnClickListener  {

    private PopupWindow popupWindow;
    @BindView(R.id.rl)
    RelativeLayout rl_root;

    @Override
    public int getLayoutId() {
        return R.layout.test_activity_test;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        setPopupWindow();
    }

    private void setPopupWindow() {
        LayoutInflater inflater = LayoutInflater.from(PopWindowActivityThree.this);
        View imgEntryView = inflater.inflate(R.layout.test_dialog_photo_entry, null); // 加载自定义的布局文件
        popupWindow = new PopupWindow(imgEntryView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#dd000000")));
        ImageView large_image = (ImageView) imgEntryView.findViewById(R.id.large_image);
        large_image.setImageResource(R.mipmap.ic_launcher);
    }


    @OnClick(R.id.tv_next)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_next:
                popupWindow.showAtLocation(rl_root, Gravity.CENTER, 0, 0);
                break;
            default:
                break;
        }
    }


    @Override
    public void onBackPressed() {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
        }
    }
}
