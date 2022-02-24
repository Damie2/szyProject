package com.smh.szyproject.test.test;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.other.utils.L;

import butterknife.OnClick;

/**
 * @Author smh
 * @Date 2022/2/23 9:37
 */
public class ttestActivity extends BaseActivity implements View.OnClickListener {

    String msg = "123";
    String msg1 = "1";

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

            L.e(change(msg));
        }
    }

    private String change(String address) {
        if (TextUtils.isEmpty(address)) {//空就返个空字符串
            return "";
        }
        if (address.length() < 2) {//长度小于2，就返俩星
            return "**";
        }
        if (address.length() == 3) {//长度为三个
            String start = TextUtils.substring(address, 0, 2);
            String end = TextUtils.substring(address, address.length() - 1, address.length());
            address = start + "**********" + end;

            return address;
        }
        String start = TextUtils.substring(address, 0, 2);
        String end = TextUtils.substring(address, address.length() - 2, address.length());
        address = start + "**********" + end;
        return address;
    }
}
