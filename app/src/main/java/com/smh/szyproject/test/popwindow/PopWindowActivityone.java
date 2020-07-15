package com.smh.szyproject.test.popwindow;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.common.base.CommonPopupWindow;
import com.smh.szyproject.other.utils.L;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author : smh
 * date   : 2020/7/14 16:43
 * desc   :
 */
public class PopWindowActivityone extends BaseActivity implements View.OnClickListener {
    private CommonPopupWindow pw_photo;
    @BindView(R.id.rl)
    public RelativeLayout rl;//包裹整个页面的rl


    @Override
    public int getLayoutId() {
        return R.layout.test_activity_test;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        setPopupWindow();
    }

    private void setPopupWindow() {
        pw_photo = new CommonPopupWindow.Build(this)
                .setLayoutId(R.layout.test_pop_photo)
                .setViewInterface(new CommonPopupWindow.PopupWindowViewInterface() {
                    @Override
                    public void operateChildView(View view) {
                        view.findViewById(R.id.tv_camera).setOnClickListener(PopWindowActivityone.this);
                        view.findViewById(R.id.tv_photo).setOnClickListener(PopWindowActivityone.this);
                        view.findViewById(R.id.tv_cancel).setOnClickListener(PopWindowActivityone.this);
                    }
                }).setHeight(WindowManager.LayoutParams.MATCH_PARENT)
                .setWidth(WindowManager.LayoutParams.MATCH_PARENT)
                .setBackground_color(Color.parseColor("#80000000"))
                .build();
    }


    @OnClick(R.id.tv_next)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_next:
                pw_photo.showAtLocation(rl, Gravity.BOTTOM, 0, 0);
                break;
            case R.id.tv_camera:
                L.e("点击相机");
                pw_photo.dismiss();
                break;
            case R.id.tv_photo:
                L.e("点击照片");
                pw_photo.dismiss();
                break;
            case R.id.tv_cancel:
                pw_photo.dismiss();
                break;
        }
    }
}
