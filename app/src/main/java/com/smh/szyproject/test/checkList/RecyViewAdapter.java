package com.smh.szyproject.test.checkList;

/**
 * author : smh
 * date   : 2020/8/18 10:58
 * desc   :
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.smh.szyproject.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RecyViewAdapter extends RecyclerView.Adapter<RecyViewAdapter.ViewHolder> {
    private Context mContext;
    private List<String>list;
    private HashMap<Integer, Boolean> maps = new HashMap<Integer, Boolean>();//多选
    public RecyViewAdapter(Context mContext, List<String> list) {
        this.mContext = mContext;
        this.list = list;
        initMap();
    }

    private  void initMap() {
        for (int i = 0; i <list.size() ; i++) {
            maps.put(i,false);   //每一次进入列表页面  都是未选中状态
        }
    }


    public void  changeMapStatus(){
        Set<Integer> s = maps.keySet();//获取key的集合
        for (Integer str : s) {
            maps.put(str, false);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.test_check_item, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder,final int i) {
        viewHolder.province_name.setText(list.get(i).toString());
        viewHolder.cbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                maps.put(i, isChecked);
            }
        });

        if (maps.get(i) == null) {
            maps.put(i, false);
        }
        viewHolder.cbox.setChecked(maps.get(i));
    }

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
    public void neverall() {
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

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private RecyclerViewOnItemClickListener mListener;//接口
        private CheckBox cbox;
        private TextView province_name;

        public ViewHolder(View itemView) {
            super(itemView);
            cbox = itemView.findViewById(R.id.cb_enshine);
            province_name = itemView.findViewById(R.id.tv_msg);
        }
    }

    public RecyclerViewOnItemClickListener onItemClickListener;

    //回调的接口
    public void setItemClickListener(RecyclerViewOnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    //接口回调设置点击事件
    public interface RecyclerViewOnItemClickListener {
        //点击事件
        void onItemClickListener(View view, int position);
    }
}