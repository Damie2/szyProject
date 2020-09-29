package com.smh.szyproject.test.jetpack.bilibiliJetPack.room5;

import android.os.Bundle;
import android.view.View;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.other.utils.L;

import java.util.List;

import butterknife.OnClick;

/**
 * author : smh
 * date   : 2020/9/28 14:45
 * desc   : 数据库的增删查改
 */
public class roomTestActivity extends BaseActivity implements View.OnClickListener {
    @Override
    public int getLayoutId() {
        return R.layout.test_activity_test;
    }

    @Override
    public void init(Bundle savedInstanceState) {

    }


    @OnClick(R.id.tv_next)
    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.tv_next){
//            insert();
//            delete();
            query();
            
        }
    }

    //ok
    private void query() {
        List<WorksEntity> list = WorksRepository.getInstance(this).getWorksDao().getWorks();
        for(WorksEntity info:list){
            L.e("name:"+info.getName());
            L.e("work:"+info.getWorks());
        }
    }

    private void delete() {
        WorksRepository.getInstance(this).getWorksDao().deleteAll();
    }

    //ok
    private void insert() {
        WorksRepository.getInstance(this).getWorksDao().insert(new WorksEntity("邵民航","goodWork"));
    }
}
