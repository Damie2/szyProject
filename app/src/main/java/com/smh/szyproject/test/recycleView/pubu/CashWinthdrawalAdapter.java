package com.smh.szyproject.test.recycleView.pubu;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.CommonAdapter;
import com.smh.szyproject.common.base.ViewHolder;
import com.smh.szyproject.common.image.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class CashWinthdrawalAdapter extends CommonAdapter<Bean> {
    Context context;

    private List<Integer> mHeights;

    public void getRandomHeight(List<Bean> mList){
        mHeights = new ArrayList<>();
        for(int i=0; i < mList.size();i++){
            //随机的获取一个范围为200-600直接的高度
            mHeights.add((int)(300+Math.random()*400));
        }
    }

    public CashWinthdrawalAdapter(List<Bean> datas, Context context) {
        super(datas, context);
        this.context  = context;
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.test_item_image_text;
    }

    @Override
    protected void showItemContent(ViewHolder holder, int position, Bean bean) {
        ImageView imageView = holder.findViewById(R.id.imageview);
        ViewGroup.LayoutParams group = imageView.getLayoutParams();
        group.height = mHeights.get(position);

        if(position==0){
            group.height=800;
        }else if(position==1){
            group.height=1200;
        }


        imageView.setLayoutParams(group);
        ImageLoader.with(context).load(bean.getUrl()).into(imageView);
    }
}
