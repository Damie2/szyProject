package com.smh.szyproject.test.dialog;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.other.utils.L;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author : smh
 * date   : 2020/7/14 10:15
 * desc   :  https://blog.csdn.net/qq_39652726/article/details/81262061
 */
public class DialogActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.tv_next)
    TextView textView;

    @Override
    public int getLayoutId() {
        return R.layout.test_activity_test;
    }

    @Override
    public void init(Bundle savedInstanceState) {

    }

    @OnClick(R.id.tv_next)
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_next) {
            //基础弹框  标题 内容 取消 确认
//            dialog1();
            //可以取消弹框
//            dialog2();
//            //和上面一样 ，只不过title左边带图标
//            dialog3();
//            //标题  内容  取消和确认，带有更多按钮，在最左边
//            dialog4();
//            //下面是有回调方法的
//            dialog5();
//            //普通列表
//            dialog6();
//            //单选和多选列表
//            dialog7();
//            //输入框
//            dialog8();
//            //进度条
//            dialog9();
            dialog10();
        }
    }

    //自定义dialog 自定义输入框
    private void dialog10() {
        MaterialDialog dialog = new MaterialDialog.Builder(this)
                .title("请输入内容")
                .customView(R.layout.test_material_dialog, true)
                .show();
        EditText editText = (EditText) dialog.findViewById(R.id.et_msg);
        TextView tvCancel = (TextView) dialog.findViewById(R.id.tv_cancel);
        TextView tvSubmit = (TextView) dialog.findViewById(R.id.tv_submit);
        tvCancel.setOnClickListener(view -> {
            dialog.dismiss();
        });
        tvSubmit.setOnClickListener(view -> {
            dialog.dismiss();
            L.e("内容是:" + editText.getText().toString().trim());
        });
    }


    private void dialog9() {
        //这个看demo  上面有链接
    }

    //输入框
    private void dialog8() {
//        new MaterialDialog.Builder(this)
//                .title("Title")
//                .content("内容")
//                .inputType(InputType.TYPE_CLASS_TEXT)
//                .input("说点什么", null, new MaterialDialog.InputCallback() {
//                    @Override
//                    public void onInput(MaterialDialog dialog, CharSequence input) {
//                        L.e("内容是:" + input);
//                    }
//                }).positiveText("确定").show();

        //限制有效字数，最大20，最小2个
//        new MaterialDialog.Builder(this)
//                .title("Title")
//                .inputRangeRes(2, 20, R.color.red)
//                .inputType(InputType.TYPE_CLASS_TEXT)
//                .input("说点什么", null, new MaterialDialog.InputCallback() {
//                    @Override
//                    public void onInput(MaterialDialog dialog, CharSequence input) {
//                        L.e("内容是:" + input);
//                    }
//                }).positiveText("确定").show();


        new MaterialDialog.Builder(this)
                .title("Title")
                .inputRangeRes(2, 20, R.color.red)
                .inputType(InputType.TYPE_CLASS_TEXT)
                .input("说点什么", null, (MaterialDialog dialog, CharSequence input) -> {
                    L.e("内容是:" + input);
                }).positiveText("确定").show();
    }

    //单选和多选列表
    private void dialog7() {
        List item = new ArrayList();
        item.add("1");
        item.add("2");
        item.add("3");
//        单选列表
//        new MaterialDialog.Builder(this)
//                .title("标题")
//                .positiveText("确认")
//                .items(item)
//                .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
//                    @Override
//                    public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
//                        return true;
//                    }
//                }).show();
        //多选列表
        new MaterialDialog.Builder(this)
                .title("标题")
                .positiveText("确认")
                .items(item)
                .itemsCallbackMultiChoice(null, new MaterialDialog.ListCallbackMultiChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, Integer[] which, CharSequence[] text) {
//                        L.e(""+text.length);
                        for (CharSequence info : text) {
                            L.e("" + info.toString());
                        }
                        return true;
                    }
                }).show();
    }

    private void dialog6() {
        List item = new ArrayList();
        item.add("1");
        item.add("2");
        item.add("3");
        new MaterialDialog.Builder(this)
                .title("标题")
                .positiveText("确认")
                .negativeText("取消")
                .items(item)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
//                        Toast.makeText(MainActivity.this, which+"", Toast.LENGTH_SHORT).show();
                        L.e("当前是:" + which + ",内容是:" + text);
                    }
                })
                .show();

    }




    //下面是有回调方法的
    private void dialog5() {
//        new MaterialDialog.Builder(this)
//                .title("标题")
//                .content("内容")
//                .positiveText("确认")
//                .negativeText("取消")
//                .neutralText("更多")
//                .onPositive(new MaterialDialog.SingleButtonCallback() {
//                    @Override
//                    public void onClick(MaterialDialog dialog, DialogAction which) {
//                        // TODO
//                        L.e("点击确认");
//                    }
//                })
//                .onNeutral(new MaterialDialog.SingleButtonCallback() {
//                    @Override
//                    public void onClick(MaterialDialog dialog, DialogAction which) {
//                        // TODO
//                        L.e("点击取消");
//                    }
//                })
//                .onNegative(new MaterialDialog.SingleButtonCallback() {
//                    @Override
//                    public void onClick(MaterialDialog dialog, DialogAction which) {
//                        // TODO
//                        L.e("点击更多");
//                    }
//                })
//                .show();
        //lanmbda表达式
//        new MaterialDialog.Builder(this)
//                .title("标题")
//                .content("内容")
//                .positiveText("确认")
//                .negativeText("取消")
//                .neutralText("更多")
//                .onPositive((MaterialDialog dialog, DialogAction which) -> {
//                    L.e("点击确认");
//                }).onNeutral((MaterialDialog dialog, DialogAction which) -> {
//            L.e("点击取消");
//        }).onNegative((MaterialDialog dialog, DialogAction which) -> {
//            L.e("点击更多");
//        }).show();

        //如果想监听三种动作按钮，只用一个onAny就行了，which会告诉你点击了什么按钮
        new MaterialDialog.Builder(this)
                .title("标题")
                .content("内容")
                .positiveText("确认")
                .negativeText("取消")
                .neutralText("更多")
                .onAny((MaterialDialog dialog, DialogAction which) -> {
//                    L.e("当前选择了" + which.toString());
                    switch (which) {
                        case POSITIVE:
                            L.e("确认");
                            break;
                        case NEGATIVE:
                            L.e("取消");
                            break;
                        case NEUTRAL:
                            L.e("更多");
                            break;
                    }
                })
                .show();
    }

    //标题  内容  取消和确认，带有更多按钮，在最左边
    private void dialog4() {
        new MaterialDialog.Builder(this)
                .title("标题")
                .content("内容")
                .positiveText("确认")
                .negativeText("取消")
                .neutralText("更多")
                .show();
    }

    //和上面一样 ，只不过title左边带图标
    private void dialog3() {
        new MaterialDialog.Builder(this)
                .title("标题")
                .content("内容")
                .positiveText("确认")
                .negativeText("取消")
                .icon(getResources().getDrawable(R.drawable.ic_launcher))
                .show();
    }

    //可以取消弹框  显示 标题 内容  确认
    private void dialog2() {
        MaterialDialog dialog = new MaterialDialog.Builder(this)
                .title("标题")
                .content("我是内容")
                .positiveText("确认")
                .show();
        //这里取消
        textView.postDelayed(() -> {
            dialog.dismiss();
        }, 3000);

    }

    //基础弹框  标题 内容 取消 确认
    private void dialog1() {
        new MaterialDialog.Builder(this)
                .title("标题")
                .content("我是内容")
                .positiveText("确认")
                .negativeText("取消")
                .show();
    }


}
