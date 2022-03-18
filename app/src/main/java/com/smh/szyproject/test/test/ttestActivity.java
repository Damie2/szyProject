package com.smh.szyproject.test.test;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.TextView;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.other.utils.L;
import com.smh.szyproject.other.utils.MyClickSpan;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Author smh
 * @Date 2022/2/23 9:37
 */
public class ttestActivity extends BaseActivity implements View.OnClickListener {

    String msg = "我已阅读并同意《廊坊银行电子银行个人客户服务协议》和《廊坊银行个人电子银行隐私政策》";

    @BindView(R.id.tv_test)
    TextView tv_test;

    @Override
    public int getLayoutId() {
        return R.layout.test_activity_test;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        tv_test.setText(msg);
        tv_test.setTextSize(25);
//        tv_test.setTextColor(Color.BLUE);

        SpannableStringBuilder spannableString = new SpannableStringBuilder();
        spannableString.append(msg);
        /**
         * 设置小明
         * 颜色和点击事件
         */
        //设置字体颜色
//        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.RED);
//        spannableString.setSpan(colorSpan, 7, 25, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        //点击事件
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.BLACK);//设置颜色
//                ds.setUnderlineText(false);//去掉下划线
            }

            @Override
            public void onClick(View view) {
                showToast("廊坊银行电子银行个人客户服务协议");
                L.e("1111111111111");
            }
        };

        spannableString.setSpan(clickableSpan, 7, 25, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);

        /**
         * 设置小红
         * 颜色和点击事件
         */
        //设置字体颜色
//        ForegroundColorSpan colorSpan1 = new ForegroundColorSpan(Color.RED);
//        spannableString.setSpan(colorSpan1, 26, 41, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        //点击事件
        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
//                ds.setColor(Color.YELLOW);//设置颜色
//                ds.setUnderlineText(false);//去掉下划线
            }

            @Override
            public void onClick(View view) {
                L.e("2222222222");
                showToast("廊坊银行个人电子银行隐私政策");
            }
        };
        spannableString.setSpan(clickableSpan1, 26, 41, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);


//        /**
//         * 内容点击事件
//         */
//        ClickableSpan clickableSpan2 = new ClickableSpan() {
//            @Override
//            public void updateDrawState(TextPaint ds) {
//                super.updateDrawState(ds);
//                ds.setColor(Color.parseColor("#FF151515"));//设置颜色
//                ds.setUnderlineText(false);//去掉下划线
//            }
//
//            @Override
//            public void onClick(View view) {
//                showToast("点击内容");
//            }
//        };
//        spannableString.setSpan(clickableSpan2, 7, spannableString.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);

//        tv_test.setHighlightColor(Color.TRANSPARENT); //设置点击后的颜色为透明，否则会一直出现高亮
        tv_test.setText(spannableString);
        tv_test.setMovementMethod(LinkMovementMethod.getInstance());

























    }

    @OnClick(R.id.tv_next)
    @Override
    public void onClick(View view) {


    }
}
