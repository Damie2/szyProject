package com.smh.szyproject.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.smh.szyproject.R;
import com.smh.szyproject.mvp.bean.ZmContact;
import com.zhy.autolayout.utils.AutoUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Create by smh on 2019/2/25.
 */
public class ZMAddContactDialog extends Dialog implements View.OnClickListener {
    @BindView(R.id.btn_comment)
    Button btnComment;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.iv_close)
    ImageView ivClose;

    int position;
    private addListener mListener;
    ZmContact zmContact;

    public ViewGroup getView() {
        return viewGroup;
    }

    private ViewGroup viewGroup;

    public ZMAddContactDialog(@NonNull Context context, int themeResId, addListener mListener) {
        super(context, themeResId);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.dialog_zm_add_contact, null);
        ButterKnife.bind(this, viewGroup);
        btnComment.setOnClickListener(this);
        ivClose.setOnClickListener(this);
        this.mListener = mListener;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AutoUtils.auto(viewGroup);
        setContentView(viewGroup);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_comment:
                ZmContact contact = new ZmContact();
                String name = etName.getText().toString().trim();
                String phone = etPhone.getText().toString().trim();
                String address = etAddress.getText().toString().trim();
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(phone) && !TextUtils.isEmpty(address)) {
                    contact.setAddress(address);
                    contact.setName(name);
                    contact.setPhone(phone);
                    mListener.addCommit(contact);
                }
                dismiss();
                break;
            case R.id.iv_close:
                dismiss();
                break;
            default:
                break;
        }
    }

    public interface addListener {
        void addCommit(ZmContact contact);
    }
}
