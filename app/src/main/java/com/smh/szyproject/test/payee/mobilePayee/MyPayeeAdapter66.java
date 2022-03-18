package com.smh.szyproject.test.payee.mobilePayee;

import android.content.Context;
import android.os.Build;
import android.util.ArraySet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.smh.szyproject.R;
import com.smh.szyproject.common.base.CommonAdapter;
import com.smh.szyproject.common.base.ViewHolder;
import com.smh.szyproject.other.utils.L;
import com.smh.szyproject.test.payee.ItemTouchStatus;
import com.smh.szyproject.test.payee.payeeGit.SortModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author smh
 * @Date 2022/3/2 16:20
 */
public class MyPayeeAdapter66 extends CommonAdapter<SortModel> implements ItemTouchStatus {
    private List<SortModel> mData;
    Context context;
    Set<String> letters = new ArraySet<>();
    List<LetterModel> letterModels = new ArrayList<>();
    int tagPosition = 0;
    MultiValueMap<String, String> stringMultiValueMap = new LinkedMultiValueMap<>();

    public MyPayeeAdapter66(List<SortModel> datas, Context context) {
        super(datas, context);
        this.mData = datas;
        this.context = context;
        for (SortModel model : mData) {
            letters.add(model.getLetters());
        }
        getDiff();
    }

    @Override
    protected int getLayoutId(int viewType) {
        switch (viewType) {
            case 0:
                return R.layout.payee_item_tag;
            case 1:
                return R.layout.payee_item;
        }
        return R.layout.payee_item;
    }

    @Override
    protected void showItemContent(ViewHolder holder, int position, SortModel s) {
        switch (getItemViewType(position)) {
            case 0://标题类型
                TextView tvTag = holder.findViewById(R.id.tag);
//                tvTag.setText("1111");
                break;
            case 1://普通类型
                ImageView iv_icon = holder.findViewById(R.id.iv_icon);
                TextView tv_name = holder.findViewById(R.id.tv_name);
                TextView tv_msg = holder.findViewById(R.id.tv_msg);
                TextView tv_null = holder.findViewById(R.id.tv_null);
                TextView tv_edit = holder.findViewById(R.id.tv_edit);
                TextView tv_delete = holder.findViewById(R.id.tv_delete);
                tv_null.setVisibility(View.GONE);
                Glide.with(iv_icon).load(R.mipmap.ic_launcher);
//                tv_msg.setText(position + "");
//                tv_name.setText(s.getName());
                break;
        }
    }

    @Override
    public boolean onItemRemove(int position) {
        return false;
    }

    @Override
    public void onSaveItemStatus(RecyclerView.ViewHolder viewHolder) {

    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    public int getPositionForSection(int section) {
        for (int i = 0; i < getItemCount(); i++) {
            String sortStr = mData.get(i).getLetters();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 拿到所有的count
     *
     * @return
     */
    @Override
    public int getItemCount() {
        int  count = mData.size() + letterModels.size();
        L.e("数量:"+count);
        return count;
    }

    /**
     * 根据当前位置返回不同类型，必须从0开始计数
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
//        int section = getSectionForPosition(position);
//        if (position == getPositionForSection(section)) {
//            return 0;//标题
//        } else {
//            return 1;//普通类型
//        }
//

            //这个时候只需要循环list的位置
//        List<LetterModel> letterModels
        L.e("position:"+position);
        for(LetterModel model :letterModels){
            if(position==model.getPosition()){
                return 0;//标题
            }else{
                return 1;//普通类型
            }
        }


//        Set<String> keySet = stringMultiValueMap.keySet();
//        for (String key : keySet) {
//            List<String> values = stringMultiValueMap.getValues(key);
//            if (position == tagPosition) {
//                return 0;
//            }
//            for (String value : values) {
//                L.e(key + ": " + value);
//                tagPosition++;
//                if (position == tagPosition) {
//                    return 1;
//                }
//            }
//        }
        return -1;

    }

    /**
     * 根据ListView的当前位置获取分类的首字母的char ascii值
     */
    public int getSectionForPosition(int position) {
        return mData.get(position).getLetters().charAt(0);
    }


    public void getDiff() {
        for (SortModel model : mData) {
            String letter = model.getLetters();
            stringMultiValueMap.add(letter, model.getName());
        }

//        letterModels
        LetterModel model;

        Set<String> keySet = stringMultiValueMap.keySet();
        for (String key : keySet) {
            model = new LetterModel();
            List<String> values = stringMultiValueMap.getValues(key);
            model.setFirstChar(key);
            if (tagPosition == 0) {
                model.setPosition(tagPosition);
                tagPosition = tagPosition + values.size() ;
                letterModels.add(model);
               continue;
            }
            tagPosition = tagPosition + values.size();
            model.setPosition(tagPosition);
            tagPosition++;
            letterModels.add(model);
        }
    }
}
