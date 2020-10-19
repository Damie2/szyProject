package com.smh.szyproject.test.jetpack.bilibiliJetPack.paging;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.test.jetpack.bilibiliJetPack.room5.WorksEntity;
import com.smh.szyproject.test.jetpack.bilibiliJetPack.room5.WorksRepository;

import butterknife.BindView;

/**
 * author : smh
 * date   : 2020/9/28 17:34
 * desc   :
 */
public class PagingActivity extends BaseActivity {
    @BindView(R.id.rv_recyclerview)
    RecyclerView recyclerView;
    PagingAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.test_paging_activity;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        adapter = new PagingAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        LiveData<PagedList<WorksEntity>> datas = new LivePagedListBuilder<>(WorksRepository.getInstance(this).getWorksDao().getWorksForPaging(), 15).build();//第二个参数是每页我们需要加载的条目数
//        data是个liveData,那我们就可以观察它
        datas.observe(this, new Observer<PagedList<WorksEntity>>() {
            @Override
            public void onChanged(PagedList<WorksEntity> worksEntities) {
                //如果有新的数据，我们在这里就把它submit
                adapter.submitList(worksEntities);
            }
        });

        getContentView().postDelayed(() -> {
            for (int i = 0; i < 100; i++) {
                WorksEntity entity = new WorksEntity("name" + i, "work" + i);
                WorksRepository.getInstance(this).getWorksDao().insert(entity);
            }

        }, 5000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        WorksRepository.getInstance(this).getWorksDao().deleteAll();
    }
}
