package com.smh.szyproject.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smh.szyproject.R;


/**
 * Created by haoyan on 2016/3/15.
 */
public class ToolBarHelper {
    private Context mContext;
    private FrameLayout mContentView;
    private int topMargin;
    //自己定义
    private View mUserView;
    private View mToolBar;
    private LayoutInflater mInflater;
    private TextView mtv_center;
    private LinearLayout ll_right, ll_left;
    //1.toolbar是否悬浮在窗口之上
    //2.toolbar的高度
    private static int[] ATTRS = {
            R.attr.windowActionBarOverlay,
            R.attr.actionBarSize
    };

    public ToolBarHelper(Context context, int layoutId) {
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
        //初始化内容
        initContentView();
        //初始化用户布局
        initUserView(layoutId);
        initToolBar();
    }

    private void initContentView() {
        //创建布局作为容器
        mContentView = new FrameLayout(mContext);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        mContentView.setId(R.id.root_id);
        mContentView.setLayoutParams(params);
    }

    private void initUserView(int layoutId) {
        mUserView = mInflater.inflate(layoutId, null);
        //LayoutParams相当于命令传递者
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        //设置自定义属性到控件中
        TypedArray typedArray = mContext.getTheme().obtainStyledAttributes(ATTRS);
        //获取主题中定义的悬浮标志
        boolean overly = typedArray.getBoolean(0, false);
        //获取主题中定义的toolbar的高度
        int i = 1;
        int toolBarSize = (int) typedArray.getDimension(i, (int) mContext.getResources().getDimension(R.dimen.abc_action_bar_default_height_material));
        typedArray.recycle();
        //如果是悬浮状态，则不需要设置间距
        params.topMargin = overly ? 0 : toolBarSize;
        topMargin = params.topMargin;
        mContentView.addView(mUserView, params);
    }

    private void initToolBar() {

        View toolbar = mInflater.inflate(R.layout.toolbar, mContentView);
        ll_left = toolbar.findViewById(R.id.ll_left);
        mToolBar = toolbar.findViewById(R.id.fl_toolbar);
        mtv_center = toolbar.findViewById(R.id.tv_center);
        ll_right = toolbar.findViewById(R.id.ll_right);

    }

    public FrameLayout getContentView() {
        return mContentView;
    }

    public View getToolBar() {
        return mToolBar;
    }

    //中间标题
    public TextView getTvCenter() {
        return mtv_center;
    }

    //右侧
    public LinearLayout getRight() {
        return ll_right;
    }

    //左侧
    public LinearLayout getLeft() {
        return ll_left;
    }

    //隐藏
    public void hideToolBar() {
        mToolBar.setVisibility(View.GONE);
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) mUserView.getLayoutParams();
        params.topMargin = 0;
        mUserView.setLayoutParams(params);
    }

    public void showToolBar() {
        mToolBar.setVisibility(View.VISIBLE);
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) mUserView.getLayoutParams();
        params.topMargin = topMargin;
        mUserView.setLayoutParams(params);
    }
}
