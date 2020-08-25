package com.smh.szyproject.test.netTest;

import android.os.Bundle;
import android.view.View;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.other.utils.L;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import butterknife.OnClick;

/**
 * author : smh
 * date   : 2020/8/25 16:39
 * desc   :
 */
public class netTestActivity extends BaseActivity implements View.OnClickListener {
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
        if(view.getId()==R.id.tv_next){
            request1();
        }
    }

    private void request1() {
        RequestParams params = new RequestParams("http://service.xunjimap.com/xunjiservice/common1_0_4/index?53D2CFEB65F6BBEEEB42836FE18E7E0D");
        params.addParameter("userinfoid", "26099");
        params.addParameter("appuserid", "43480");
        params.addParameter("t", "20151110102609");
        params.addParameter("token", "074FD831C183689BFACBD215118EEA525773DC8F");
        params.addParameter("sid", "kJMOzgjVcH");
        params.addParameter("imei", "9692B6993494E9324B7F9560912242B7");
        params.addParameter("os", "Android+4.4.4");
        params.addParameter("key", "WANDOUJIA");

        try {
            x.http().post(params, new Callback.CommonCallback<String>() {

                @Override
                public void onSuccess(String result) {
                    L.e("结果是:" + result);
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    L.e("onError"+ex.getMessage());
                }

                @Override
                public void onCancelled(CancelledException cex) {
                    L.e("onCancelled");
                }

                @Override
                public void onFinished() {
                    L.e("onFinished");
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
