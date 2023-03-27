package com.smh.szyproject.test.netTest;

import android.os.Bundle;
import android.view.View;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.mvp.bean.Test;
import com.smh.szyproject.mvp.bean.getStatus;
import com.smh.szyproject.mvp.bean.sendId;
import com.smh.szyproject.mvp.module.TestContract;
import com.smh.szyproject.mvp.presenter.TestPresenter;
import com.smh.szyproject.other.utils.L;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

/**
 * author : smh
 * date   : 2020/8/25 16:39
 * desc   :
 */
public class netTestActivity extends BaseActivity implements View.OnClickListener, TestContract.View {

    int page = 0;
    TestPresenter presenter;
    List<Integer> list;
    @Override
    public int getLayoutId() {
        return R.layout.test_activity_test;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        list = new ArrayList<>();
        presenter = new TestPresenter(this, this);
        for (int i = 0; i < 12; i++) {
            list.add(i);
        }
        initPage(list);
        L.e("=================");
//        for (int i = 0; i < 16; i++) {
//            list.add(i);
//        }
//        initPage(list);
//        L.e("=================");
//        for (int i = 0; i < 17; i++) {
//            list.add(i);
//        }
        initPage(list);

    }

    private void initPage(List<Integer> list) {
        int size = list.size();
        int pages = size / 8;//取整
        int lastPageMenu = size % 8;//取余
        if (lastPageMenu != 0) {
            pages++;
        }
        List<Integer> pageList;
        page = 0;
        for (int i = 0; i < pages; i++) {

            if (pages == 1) {//如果只有一个页面
            L.e("?");
            } else {//如果有多个页面

                if (i == pages - 1) {//如果是最后一个界面
                    L.e("如果是最后一个界面");
                    if (lastPageMenu != 0) {
                        pageList = list.subList(size - lastPageMenu, size);
                    } else {
                        pageList = list.subList(size - i * 8, size);
                    }

                } else {//如果不是最后一个页面
                    L.e("如果不是最后一个页面");
                    pageList = list.subList(page * 8, (page + 1) * 8);
                    page++;
                }
            }

        }





    }

    @OnClick(R.id.tv_next)
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_next) {
            request1();
        }
    }

    private void request1() {
        sendId test = new sendId();
        test.setId(1);
        presenter.getName(test);
    }

    @Override
    public void setContent(String content) {

    }

    @Override
    public void sendRequest(getStatus data) {

    }

    @Override
    public void sendId(sendId testStatus) {

    }
}
