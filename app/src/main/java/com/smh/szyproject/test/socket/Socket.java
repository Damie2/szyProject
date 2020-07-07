package com.smh.szyproject.test.socket;

import android.os.Bundle;
import android.view.View;
import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import butterknife.OnClick;


/**
 * 参考
 * https://www.jianshu.com/p/9302f8552a7d
 */
public class Socket extends BaseActivity implements View.OnClickListener {

    @Override
    public int getLayoutId() {
        return R.layout.test_activity_socket_test;
    }

    @Override
    public void init(Bundle savedInstanceState) {

    }

    @OnClick({R.id.connect, R.id.disconnect, R.id.Receive, R.id.send})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.connect:
                MyWsManager.getInstance().init(this);
                break;
            case R.id.disconnect:
                MyWsManager.getInstance().disconnect();
                break;
            case R.id.send:
                MyWsManager.getInstance().sendDataD("ping");
                break;
            default:
                break;
        }
    }
}