package com.smh.szyproject.test.interfaceTest;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.gyf.immersionbar.OnKeyboardListener;
import com.smh.szyproject.R;
import com.smh.szyproject.action.StatusAction;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.other.helper.PopupWindowHelper;
import com.smh.szyproject.other.utils.L;
import com.smh.szyproject.other.utils.SoftHideKeyBoardUtil;
import com.smh.szyproject.other.widget.other.HintLayout;
import com.smh.szyproject.other.utils.SPUtil;

import butterknife.BindView;

/**
 * author : smh
 * date   : 2020/3/9 14:56
 * desc   :
 */
public class interfaceTestA extends BaseActivity implements StatusAction {

    @BindView(R.id.layout)
    HintLayout layout;


    @Override
    public int getLayoutId() {
        return R.layout.test_activity_test;
    }

    @Override
    public void init(Bundle savedInstanceState) {
//        SPUtil.putString("token","123",this);
        //输入法自动往上抬
//        SoftHideKeyBoardUtil.assistActivity(this);
        //输入法，软键盘 输入框 很好很强大  锁键盘
        getStatusBarConfig().keyboardEnable(true).init();
        getStatusBarConfig().keyboardEnable(true).setOnKeyboardListener(new OnKeyboardListener() {
            @Override
            public void onKeyboardChange(boolean isPopup, int keyboardHeight) {
                L.e("软键盘弹出了");
            }
        }).init();
        SPUtil.clear(this);
        InterfaceA inter = new AA();
        inter.test();


        ViewGroup group = getContentView();
        if(group!=null){

        }

//        getStatusBarConfig().statusBarDarkFont(true).init();

//        textView.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

    }

    @Override
    public HintLayout getHintLayout() {
        return layout;
    }

    public void tv_next(View view) {
        PopupWindowHelper helper = new PopupWindowHelper(this,R.layout.test_popwin_demo);
        helper.show(view);
    }
}
