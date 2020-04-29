package com.smh.szyproject.ui.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.smh.szyproject.R;
import com.smh.szyproject.base.CommonAdapter;
import com.smh.szyproject.base.ViewHolder;
import com.smh.szyproject.bean.ZmContact;
import com.smh.szyproject.ui.dialog.ZMContactDialog;
import com.smh.szyproject.utils.L;

import java.util.List;

/**
 * author : smh
 * date   : 2020/4/27 11:14
 * desc   :
 */
public class ZmContactAdapter extends CommonAdapter<ZmContact> implements ZMContactDialog.InvitationListener {

    private Context context;
    ZMContactDialog.InvitationListener listener;
    ZMContactDialog dialog;
    private zmListener mListener;

    public ZmContactAdapter(List<ZmContact> datas, Context context, zmListener mListener) {
        super(datas, context);
        this.context = context;
        this.mListener = mListener;
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.zm_contact_item;
    }

    @Override
    protected void showItemContent(ViewHolder holder, int position, ZmContact zmContact) {
        listener = this;
        TextView tvName = holder.findViewById(R.id.tv_name);
        TextView tvPhone = holder.findViewById(R.id.tv_phone);
        TextView tvAddress = holder.findViewById(R.id.tv_address);
        TextView tvEdit = holder.findViewById(R.id.tv_edit);


        tvName.setText(zmContact.getName());
        tvPhone.setText(zmContact.getPhone());
        tvAddress.setText(zmContact.getAddress());
        tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new ZMContactDialog(context, R.style.dialog_style, listener, position, zmContact);
                dialog.show();
            }
        });
    }

    @Override
    public void commit(ZmContact contact) {
        L.e("点击提交");
        mListener.add(contact);
    }

    @Override
    public void delete(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("警告");
        builder.setMessage("确定要删除");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogs, int which) {
                L.e("点击确定");
                mListener.remove(position);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.setCancelable(false);
        builder.create().show();
    }

    @Override
    public void close() {
        dialog.dismiss();
    }

    public interface zmListener {
        void remove(int position);

        void add(ZmContact contact);
    }
}
