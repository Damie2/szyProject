package com.smh.szyproject.test.payee.myPayee;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.TextView;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.test.mvvmGit.architecture.utils.DisplayUtils;
import com.smh.szyproject.test.payee.GamItemTouchCallback;
import com.smh.szyproject.test.payee.payeeGit.ClearEditText;
import com.smh.szyproject.test.payee.payeeGit.PinyinComparator;
import com.smh.szyproject.test.payee.payeeGit.PinyinUtils;
import com.smh.szyproject.test.payee.payeeGit.SideBar;
import com.smh.szyproject.test.payee.payeeGit.SortModel;
import com.smh.szyproject.ui.view.MyLayoutManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;

/**
 * @Author smh
 * @Date 2022/3/3 10:08
 */
public class MyPayeeActivity extends BaseActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.sideBar)
    SideBar sideBar;
    @BindView(R.id.filter_edit)
    ClearEditText mClearEditText;
    @BindView(R.id.dialog)
    TextView dialog;
    MyLayoutManager manager;
    private List<SortModel> SourceDateList;
    private MyPayeeAdapter adapter;

    /**
     * 根据拼音来排列RecyclerView里面的数据类
     */
    private PinyinComparator pinyinComparator;


    public int getLayoutId() {
        return R.layout.activity_payee_new;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        pinyinComparator = new PinyinComparator();
        SourceDateList = filledData(getResources().getStringArray(R.array.date));
        manager = new MyLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        sideBar.setOnTouchingLetterChangedListener(s -> {
            //该字母首次出现的位置
            int position = adapter.getPositionForSection(s.charAt(0));
            if (position != -1) {
                manager.scrollToPositionWithOffset(position, 0);
            }
        });
        // 根据a-z进行排序源数据
        Collections.sort(SourceDateList, pinyinComparator);
        adapter = new MyPayeeAdapter(SourceDateList, this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new GamItemTouchCallback(adapter, DisplayUtils.dp2px(100)));
        itemTouchHelper.attachToRecyclerView(recyclerView);

        //根据输入框输入值的改变来过滤搜索
        mClearEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
                filterData(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


    }

    /**
     * 为RecyclerView填充数据
     *
     * @param date
     * @return
     */
    private List<SortModel> filledData(String[] date) {
        List<SortModel> mSortList = new ArrayList<>();

        for (int i = 0; i < date.length; i++) {
            SortModel sortModel = new SortModel();
            sortModel.setName(date[i]);
            //汉字转换成拼音
            String pinyin = PinyinUtils.getPingYin(date[i]);
            String sortString = pinyin.substring(0, 1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]")) {
                sortModel.setLetters(sortString.toUpperCase());
            } else {
                sortModel.setLetters("#");
            }

            mSortList.add(sortModel);
        }
        return mSortList;

    }

    /**
     * 根据输入框中的值来过滤数据并更新RecyclerView
     *
     * @param filterStr
     */
    private void filterData(String filterStr) {
        List<SortModel> filterDateList = new ArrayList<>();

        if (TextUtils.isEmpty(filterStr)) {
            filterDateList = SourceDateList;
        } else {
            filterDateList.clear();
            for (SortModel sortModel : SourceDateList) {
                String name = sortModel.getName();
                if (name.indexOf(filterStr.toString()) != -1 ||
                        PinyinUtils.getFirstSpell(name).startsWith(filterStr.toString())
                        //不区分大小写
                        || PinyinUtils.getFirstSpell(name).toLowerCase().startsWith(filterStr.toString())
                        || PinyinUtils.getFirstSpell(name).toUpperCase().startsWith(filterStr.toString())
                ) {
                    filterDateList.add(sortModel);
                }
            }
        }

        // 根据a-z进行排序
        Collections.sort(filterDateList, pinyinComparator);
        adapter.refresh(filterDateList);
    }


}
