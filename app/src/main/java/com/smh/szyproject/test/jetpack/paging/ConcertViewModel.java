package com.smh.szyproject.test.jetpack.paging;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

/**
 * author : smh
 * date   : 2020/9/21 17:06
 * desc   :  这玩意是pagedList预加载和viewModel的用法
 * //要搞明白啊
 */
public class ConcertViewModel extends ViewModel {
    private static  final int PAGE_SIZE = 10;//出示加载数量
    private static final int RELOAD_SIZE =20;//往下滑动加载数量

//    private final LiveData<PagedList<Concert>> mConcertListLiveData;
//
//    public ConcertViewModel(){
//        PagedList.Config config = new PagedList.Config.Builder()
//                .setPageSize(PAGE_SIZE)//分页加载的数量
//                .setEnablePlaceholders(false)//当item为null的时候是否使用PlaceHolder展示
//                .setInitialLoadSizeHint(RELOAD_SIZE)//预加载数量，与分页加载的数量成倍数关系
//                .setPrefetchDistance(5)//设置举例最后还有多少个item时，开始加载下一页的数据
//                .build();
//        mConcertListLiveData = new LivePagedListBuilder<>(new ConcertFactory(),config)
//                .setBoundaryCallback(new ConcertListBoundaryCallback())
//                .build();
//    }
//    public LiveData<PagedList<Concert>> getmConcertListLiveData(){
//        return mConcertListLiveData;
//    }
}
