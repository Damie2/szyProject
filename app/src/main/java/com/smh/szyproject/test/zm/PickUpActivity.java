package com.smh.szyproject.test.zm;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.hjq.permissions.Permission;

import com.smh.szyproject.R;
import com.smh.szyproject.aop.Permissions;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.other.utils.FileUtil;
import com.smh.szyproject.other.utils.L;
import com.smh.szyproject.ui.view.SpaceItemDecoration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author : smh
 * date   : 2020/7/15 9:23
 * desc   :
 */
public class PickUpActivity extends BaseActivity implements View.OnClickListener {

    String name;
    String price;
    String number;

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_number)
    TextView tvNumber;

    @BindView(R.id.rl_picture)
    RelativeLayout rl_picture;


    PictureAdapter adapter;
    @BindView(R.id.receiveview)
    RecyclerView rv_data;

    @BindView(R.id.rl_center)
    RelativeLayout rl_center;

    List list = new ArrayList();

    @Override
    public int getLayoutId() {
        return R.layout.test_pick_up_picture;
    }

    @Permissions(Permission.MANAGE_EXTERNAL_STORAGE)
    @Override
    public void init(Bundle savedInstanceState) {
        getStatusBarConfig().statusBarDarkFont(true);
        rv_data.setLayoutManager(new GridLayoutManager(this, 2));
        rv_data.addItemDecoration(new SpaceItemDecoration(5));

        adapter = new PictureAdapter(list, this);
        rv_data.setAdapter(adapter);
    }

    @Permissions(Permission.MANAGE_EXTERNAL_STORAGE)
    @OnClick({R.id.tv_set_message, R.id.tv_get_photo, R.id.tv_down_load_picture})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_set_message:
                setMessage();
                break;
            case R.id.tv_get_photo:
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(price) || TextUtils.isEmpty(number)) {
                    showToast("先填信息");
                    return;
                }
                break;
            case R.id.tv_down_load_picture:
                downLoad();
                break;
            default:
                break;
        }
    }

    private void downLoad() {
        if (list.size() == 0) {
            showToast("图片和信息呢？");
            return;
        }
        Bitmap bitmap = null;
        final boolean drawingCacheEnabled = true;
        rl_picture.setDrawingCacheEnabled(drawingCacheEnabled);
        rl_picture.buildDrawingCache(drawingCacheEnabled);
        final Bitmap drawingCache = rl_picture.getDrawingCache();
        if (drawingCache != null) {
            bitmap = Bitmap.createBitmap(drawingCache);
            rl_picture.setDrawingCacheEnabled(false);
        } else {
            bitmap = null;
        }
        File file = FileUtil.saveBitmap(bitmap, Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "cache", 100);
        if (file.exists()) {
            L.d("路径是" + file.getAbsolutePath());
            showToast("保存成功");
            //通知系统刷新相册
            Intent mediaScanIntent = new Intent(
                    Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri contentUri = Uri.fromFile(file);
            mediaScanIntent.setData(contentUri);
            this.sendBroadcast(mediaScanIntent);
        }
    }


    private void setWater() {
        rl_center.setVisibility(View.VISIBLE);
        tvName.setText(name);
        tvPrice.setText(price);
        tvNumber.setText(number);
    }


    //自定义dialog 自定义输入框
    private void setMessage() {
        MaterialDialog materialDialog = new MaterialDialog.Builder(this)
                .title("请输入内容")
                .customView(R.layout.test_set_message_dialog, true)
                .show();

        EditText etName = (EditText) materialDialog.findViewById(R.id.et_msg);
        EditText etPrice = (EditText) materialDialog.findViewById(R.id.et_price);
        EditText etNumber = (EditText) materialDialog.findViewById(R.id.et_number);

        TextView tvCancel = (TextView) materialDialog.findViewById(R.id.tv_cancel);
        TextView tvSubmit = (TextView) materialDialog.findViewById(R.id.tv_submit);

        tvCancel.setOnClickListener(view -> {
            materialDialog.dismiss();
        });
        tvSubmit.setOnClickListener(view -> {
            L.e("" + etName.getText().toString().trim());
            L.e("" + etPrice.getText().toString().trim());
            L.e("" + etNumber.getText().toString().trim());
            name = etName.getText().toString().trim();
            price = etPrice.getText().toString().trim();
            number = etNumber.getText().toString().trim();
            materialDialog.dismiss();
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

        }
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
