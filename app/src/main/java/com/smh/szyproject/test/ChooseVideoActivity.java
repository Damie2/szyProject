package com.smh.szyproject.test;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author : smh
 * date   : 2020/6/18 9:51
 * desc   :
 */
public class ChooseVideoActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.et_url)
    EditText editText;
    @Override
    public int getLayoutId() {
        return R.layout.test_activity_choose_activity;
    }

    @Override
    public void init(Bundle savedInstanceState) {

    }

    @OnClick({R.id.btn_video1, R.id.btn_video2, R.id.btn_enter_video})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_video1:
                startActivity("video1");
                break;
            case R.id.btn_video2:
                startActivity("video2");
                break;
            case R.id.btn_enter_video:
                String url = editText.getText().toString().trim();
                if(!TextUtils.isEmpty(url)){
                    startActivity(url);
                }else {
                    showToast("请先输入直播地址");
                }
                break;
            default:
                break;
        }
    }

    private void startActivity(String s) {
        Intent intent = new Intent(this, VideoActivity.class);
         switch (s){
             case "video1":
                 intent.putExtra("extra","video1");
                 break;
             case "video2":
                 intent.putExtra("extra","video2");
                 break;
             default:
                 intent.putExtra("extra",s);
                 break;
         }
         startActivity(intent);
    }


}
