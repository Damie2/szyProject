package com.smh.szyproject.test.fragment.glodBanner;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.CommonAdapter;
import com.smh.szyproject.common.base.ViewHolder;
import com.smh.szyproject.common.image.ImageLoader;

import java.util.List;

/**
 * Create by smh on 2018/11/26.
 */
public class MemberListAdapter extends CommonAdapter<Member> {
    public MemberListAdapter(List<Member> datas, Context context) {
        super(datas, context);
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.test_member_item;
    }

    @Override
    protected void showItemContent(ViewHolder holder, int position, Member member) {
        ImageView iv = holder.findViewById(R.id.iv_member_icon);
        TextView doJob = holder.findViewById(R.id.tv_do_job);

        switch (position) {
            case 0:
//                doJob.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        context.startActivity(new Intent(context, LoginActivity.class));
//                    }
//                });

                doJob.setText("已完成");
                ImageLoader.with(context).load(String.valueOf(R.drawable.test_iv_member_login)).into(iv);
                break;
            case 1:
                doJob.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        context.startActivity(new Intent(context, RecommendCodeActivity.class));
                    }
                });
//                ImageLoaderPresenter.getInstance().displayImage(context, String.valueOf(R.drawable.iv_member_recommendationcode), iv);
                break;
            case 2:
                doJob.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        context.startActivity(new Intent(context, LoginActivity.class));

//                        RxBus.getInstance().send("", Constants.Tag.WECHAT_LOGIN);


                    }
                });
//                ImageLoaderPresenter.getInstance().displayImage(context, String.valueOf(R.drawable.iv_member_wechat), iv);
                break;
            case 3:
                doJob.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        context.startActivity(new Intent(context, LoginActivity.class));


//                        RxBus.getInstance().send("", Constants.Tag.QQ_LOGIN);


                    }
                });
//                ImageLoaderPresenter.getInstance().displayImage(context, String.valueOf(R.drawable.iv_member_qq), iv);
                break;
            default:
                break;
        }

        TextView tvTitle = holder.findViewById(R.id.tv_member_title);
        TextView tvDes = holder.findViewById(R.id.tv_member_des);

        tvDes.setText(member.getDes());
        tvTitle.setText(member.getTitle());
    }
}
