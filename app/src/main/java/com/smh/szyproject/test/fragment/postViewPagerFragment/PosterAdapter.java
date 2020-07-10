package com.smh.szyproject.test.fragment.postViewPagerFragment;

import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.smh.szyproject.R;
import com.smh.szyproject.common.base.CommonAdapter;
import com.smh.szyproject.common.base.ViewHolder;
import com.smh.szyproject.common.image.ImageLoader;
import com.smh.szyproject.other.utils.L;
import com.sxu.shadowdrawable.ShadowDrawable;


import java.util.List;

/**
 * Create by smh on 2019/4/2.
 */
public class PosterAdapter extends CommonAdapter<Integer> {
    private int selectPosiion = -1;
    List<Integer> datas;
    public int getSelectPosiion() {
        return selectPosiion;
    }

    public void setSelectPosiion(int selectPosiion) {
        this.selectPosiion = selectPosiion;
    }

    public PosterAdapter(List<Integer> datas, Context context) {
        super(datas, context);
       this.datas = datas;
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.test_poster_card_item;
    }

    @Override
    protected void showItemContent(ViewHolder holder, int position, Integer integer) {
        ImageView iv = holder.findViewById(R.id.iv_poster);
        ImageLoader.with(iv.getContext()).load(String.valueOf(integer)).into(iv);
        switch (position) {
            case 0:
                ImageLoader.with(iv.getContext()).load(datas.get(0)).into(iv);
                break;
            case 1:
                ImageLoader.with(iv.getContext()).load(datas.get(1)).into(iv);
                break;
            case 2:
                ImageLoader.with(iv.getContext()).load(datas.get(2)).into(iv);
                break;
            default:
                break;
        }

        if (selectPosiion == position) {
            ShadowDrawable.setShadowDrawable(iv, Color.parseColor("#00000000"), 0,
                    Color.parseColor("#b2ff2922"), 10, 0, 0);
        } else {
            ShadowDrawable.setShadowDrawable(iv, Color.parseColor("#00000000"), 0,
                    Color.parseColor("#00000000"), 0, 0, 0);
        }
    }


}
