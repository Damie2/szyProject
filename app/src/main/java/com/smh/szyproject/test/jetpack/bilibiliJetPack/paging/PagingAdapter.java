package com.smh.szyproject.test.jetpack.bilibiliJetPack.paging;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.smh.szyproject.R;
import com.smh.szyproject.test.jetpack.bilibiliJetPack.room5.WorksEntity;

/**
 * author : smh
 * date   : 2020/9/28 17:35
 * desc   :
 */
public class PagingAdapter extends PagedListAdapter<WorksEntity,PagingAdapter.CustomViewHolder> {


//    protected PagingAdapter(@NonNull DiffUtil.ItemCallback<WorksEntity> diffCallback) {
//        super(diffCallback);
//    }

    //自己写DiffUtil，她是来判断两个条目是不是一致的
    protected PagingAdapter() {
        super(new DiffUtil.ItemCallback<WorksEntity>() {
            @Override
            public boolean areItemsTheSame(@NonNull WorksEntity oldItem, @NonNull WorksEntity newItem) {
                return oldItem.getId()==newItem.getId();
                //以ID为区分，来判断是否为同一个条目的组件
            }

            @Override
            public boolean areContentsTheSame(@NonNull WorksEntity oldItem, @NonNull WorksEntity newItem) {
                return !oldItem.getName().equals(newItem.getName())&&!oldItem.getWorks().equals(newItem.getWorks());
                //内容上以我们的作品为依据进行一个判断
            }
        });
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //每一个条目的布局文件给加载进来
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_paging_item,parent,false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        WorksEntity entity =getItem(position);
        if(entity!=null){
            holder.workTv.setText(entity.getWorks());
            holder.nameTv.setText(entity.getName());
        }else{
            holder.workTv.setText("loading...");
        }
    }

    //ViewHolder和每一个ListItem是一一对应的关系,解决了findViewById的性能问题
    static class CustomViewHolder extends RecyclerView.ViewHolder{
        TextView nameTv,workTv;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.tv_name);
            workTv = itemView.findViewById(R.id.tv_works);
        }
    }
}
