package com.smh.szyproject.test.jetpack.liveData;

import android.view.View;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseFragment;
import com.smh.szyproject.other.utils.L;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author : smh
 * date   : 2020/9/21 18:00
 * desc   :https://www.jianshu.com/p/2fa0aa513a32
 */
public class LiveDataFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.tv_next)
    TextView textView;
    private static final String TAG="LiveDataFragment";
    private NameViewModel nameViewModel;
    @Override
    protected void init() {
        nameViewModel = ViewModelProviders.of(this).get(NameViewModel.class);
        nameViewModel.getmCurrentName().observe(this,(String name)->{
            //订阅LiveData中name的数据变化
            textView.setText(name);
        });
        nameViewModel.getNameList().observe(this,(List<String> nameList)->{
        for(String item:nameList){
            L.e(item);//订阅LiveData中Name列表数据变化
        }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.test_activity_test;
    }

    @OnClick(R.id.tv_next)
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case  R.id.tv_next:
//                nameViewModel.getmCurrentName().setValue("???");

                List<String> nameList = new ArrayList<>();
                for (int i = 0; i < 10; i++){
                    nameList.add("Jane<" + i + ">");
                }
                nameViewModel.getNameList().setValue(nameList);
                break;

        }
    }
}
