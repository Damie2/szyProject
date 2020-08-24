package com.smh.szyproject.test.checkList;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.CommonAdapter;
import com.smh.szyproject.common.base.ViewHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * author : smh
 * date   : 2020/8/18 9:31
 * desc   :
 */
public class CheckItemAdapter extends CommonAdapter<String> {
    private Context mContext;
    private List<String>list;
    private HashMap<Integer, Boolean> maps = new HashMap<Integer, Boolean>();//多选

    public CheckItemAdapter(List<String> list, Context context) {
        super(list, context);
        this.list = list;
        for (int i = 0; i <list.size() ; i++) {
            maps.put(i,false);   //每一次进入列表页面  都是未选中状态
        }
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.test_check_item;
    }

    @Override
    protected void showItemContent(ViewHolder holder, int position, String s) {
        TextView textView = holder.findViewById(R.id.tv_msg);
        textView.setText(s);
        CheckBox cbox = holder.findViewById(R.id.cb_enshine);

        cbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                maps.put(position, isChecked);
            }
        });

        if (maps.get(position) == null) {
            maps.put(position, false);
        }
       cbox.setChecked(maps.get(position));
    }
    //修改 方法名 shift  F6
    //全选方法
    public void All() {
        Set<Map.Entry<Integer, Boolean>> entries = maps.entrySet();
        boolean shouldall = false;
        for (Map.Entry<Integer, Boolean> entry : entries) {
            Boolean value = entry.getValue();
            if (!value) {
                shouldall = true;
                break;
            }
        }
        for (Map.Entry<Integer, Boolean> entry : entries) {
            entry.setValue(shouldall);
        }
        notifyDataSetChanged();
    }

    //反选
    public void neverAll() {
        Set<Map.Entry<Integer, Boolean>> entries = maps.entrySet();
        for (Map.Entry<Integer, Boolean> entry : entries) {
            entry.setValue(!entry.getValue());
        }
        notifyDataSetChanged();
    }

    //多选
    public void MultiSelection(int position) {
        //对当前状态取反
        if (maps.get(position)) {
            maps.put(position, false);
        } else {
            maps.put(position, true);
        }
        notifyItemChanged(position);
    }

    //获取最终的map存储数据
    public Map<Integer, Boolean> getMap() {
        return maps;
    }

    public void  changeMapStatus(){
        Set<Integer> s = maps.keySet();//获取key的集合
        for (Integer str : s) {
            maps.put(str, false);
        }
    }


}
