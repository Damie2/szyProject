package com.smh.szyproject.test.dialog;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.other.utils.L;
import com.smh.szyproject.test.build.CustomDialog;
import com.smh.szyproject.test.build.IDialogView;
import com.smh.szyproject.test.build.OnLeftLinster;
import com.smh.szyproject.test.build.OnRightLinster;
import com.smh.szyproject.test.build.SimpleDialog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author : smh
 * date   : 2020/7/14 10:15
 * desc   :  https://blog.csdn.net/qq_39652726/article/details/81262061
 */
public class CustomDialogActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.tv_next)
    TextView textView;
    CustomDialog dialog;

    @Override
    public int getLayoutId() {
        return R.layout.test_activity_test;
    }

    @Override
    public void init(Bundle savedInstanceState) {
//        dialog1();
//        dialog2();
//        dialog3();
//        dialog4();
        getContentView().postDelayed(() -> {
            dialog5();
        }, 3000);
    }

    @Override
    protected void onPause() {
        L.e("onPause");
        super.onPause();
    }

    @Override
    public void onResume() {
        L.e("onResume");
        super.onResume();
    }

    private void dialog5() {
        new SimpleDialog(this, R.style.dialog_style)
                .setContent("确定吗骚年？")
                .setRightBtnText("确定")
                .setLeftBtnText("取消")
                .setLeftBtnGone()
                .setRightBtnColor(Color.RED)
                .setLeftListener(view -> {
                    L.e("点击左边");
                    SimpleDialog dialog = (SimpleDialog) view;
                    dialog.dismiss();
                }).setRightListener(view -> {
            L.e("点击右边");
            SimpleDialog dialog = (SimpleDialog) view;
            dialog.dismiss();
        }).show();
    }



    private void dialog4() {
        new CustomDialog(this, R.style.dialog_style)
                .setContent("我是内容")
                .setRightBtnText("确定")
                .setLeftBtnText("取消")
                .setRightBtnColor(Color.RED)
                .setLeftListener(view -> {
                    L.e("点击左边");
                    CustomDialog dialog = (CustomDialog) view;
                    dialog.dismiss();
                }).setRightListener(view -> {
            L.e("点击右边");
            CustomDialog dialog = (CustomDialog) view;
            dialog.dismiss();
        }).show();
    }

    private void dialog3() {
        new CustomDialog(this, R.style.dialog_style)
                .setTitle("提示")
                .setContent("我是内容")
                .setRightBtnText("确定")
                .setLeftBtnText("取消")
                .setRightBtnColor(Color.BLUE)
                .setLeftListener(view -> {
                    L.e("点击左边");
                    CustomDialog dialog = (CustomDialog) view;
                    dialog.dismiss();
                }).setRightListener(view -> {
            L.e("点击右边");
            CustomDialog dialog = (CustomDialog) view;
            dialog.dismiss();
        }).show();
    }

    private void dialog2() {
        View view = View.inflate(this, R.layout.test_up_date_message, null);
        final TextView tv = view.findViewById(R.id.tv_update_message);
        tv.setText("?????");
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                L.e("我被点击了");
            }
        });
        dialog = new CustomDialog(this, R.style.dialog_style)
                .setTitle("提示")
                .setTitleColor(Color.RED)
//                .setViewContent(view)
                .setContent("是否保存图片至手机")//这俩互斥
                .setRightBtnText("确定")
                .setLeftBtnText("取消")
                .setRightBtnColor(Color.BLUE)
                .setLeftBtnColor(Color.BLUE)
                .setLeftListener(new OnLeftLinster() {
                    @Override
                    public void onClick(IDialogView view) {
                        L.e("点击左边");
                        dialog.dismiss();
                    }
                }).setRightListener(new OnRightLinster() {
                    @Override
                    public void onClick(IDialogView view) {
                        L.e("点击右边");
                        dialog.dismiss();
                    }
                });
        //点击弹窗外部或物理返回键都不消失
        dialog.setCancelable(true);
        //点击弹窗外部不消失，物理返回键消失
//        dialog.setCanceledOnTouchOutside(false);
        //点击弹窗外部不消失，物理返回键消失
//        dialog.setOnCancelListener((DialogInterface dialogInterface)->{
//        });
    }

    @OnClick(R.id.tv_next)
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_next) {
//            dialog.show();
        }
    }

    private void dialog1() {
        dialog = new CustomDialog(this, R.style.dialog_style)
                .setTitle("我是标题")
                .setContent("我是内容")
                .setRightBtnText("确定")
                .setLeftBtnText("取消")
                .setRightBtnColor(Color.BLUE)
                .setLeftListener(new OnLeftLinster() {
                    @Override
                    public void onClick(IDialogView view) {
                        L.e("点击左边");
                        dialog.dismiss();
                    }
                }).setRightListener(new OnRightLinster() {
                    @Override
                    public void onClick(IDialogView view) {
                        L.e("点击右边");
                        dialog.dismiss();
                    }
                });
    }
}
