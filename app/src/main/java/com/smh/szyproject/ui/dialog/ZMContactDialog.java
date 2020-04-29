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
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.smh.szyproject.R;
import com.smh.szyproject.bean.ZmContact;
import com.smh.szyproject.db.DBHelper;
import com.zhy.autolayout.utils.AutoUtils;

import org.xutils.db.table.DbBase;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Create by smh on 2019/2/25.
 */
public class ZMContactDialog extends Dialog implements View.OnClickListener {
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
    @BindView(R.id.tv_delete)
    TextView tvDelete;
    int position;
    private InvitationListener mListener;
    ZmContact zmContact;

    public ViewGroup getView() {
        return viewGroup;
    }

    private ViewGroup viewGroup;

    String oldName;

    public ZMContactDialog(@NonNull Context context, int themeResId, InvitationListener mListener, int myPosition, ZmContact zmContact) {
        super(context, themeResId);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.dialog_zm_contact, null);
        ButterKnife.bind(this, viewGroup);
        btnComment.setOnClickListener(this);
        ivClose.setOnClickListener(this);
        tvDelete.setOnClickListener(this);
        this.mListener = mListener;
        this.position = myPosition;
        this.zmContact = zmContact;
        if (zmContact != null) {
            etName.setText(zmContact.getName());
            etPhone.setText(zmContact.getPhone());
            etAddress.setText(zmContact.getAddress());
            oldName = zmContact.getName();
        }
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
                    if (!TextUtils.equals(oldName, name)) {
                        DBHelper.getInstance().deleteById(ZmContact.class, oldName);
                    }
                    contact.setAddress(address);
                    contact.setName(name);
                    contact.setPhone(phone);
                    mListener.commit(contact);

                }
                dismiss();
                break;
            case R.id.iv_close:
                mListener.close();
                break;
            case R.id.tv_delete:
                DBHelper.getInstance().deleteById(ZmContact.class, etName.getText().toString().trim());
                mListener.delete(position);
            default:
                break;
        }
    }

    public interface InvitationListener {

        void commit(ZmContact contact);

        void delete(int position);

        void close();
    }
}
