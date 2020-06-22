package com.smh.szyproject.test.im;

import android.content.Context;

import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Administrator on 2018/7/20.
 */

public class ChatListView extends RecyclerView {

    public ChatListView(Context context) {
        super(context);
    }

    public ChatListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private int position1, position2;
    private boolean isDown;

    private int oldB;
    private int s;


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            int a = oldB - b;
            if ((Math.abs(a) > Math.abs(dy)&&!flag)||(Math.abs(a) <= Math.abs(dy))) {
                flag = true;
                this.scrollBy(0, oldB - b);
            } else {

                this.scrollBy(0, dy);
                flag = false;
            }

            oldB = b;
        } else {
            oldB = b;
        }
//        if (!changed) {
//            getLayoutManager().scrollToPosition(position);
//            position1=mLineManager.findLastVisibleItemPosition();
//            position2=mLineManager.findFirstVisibleItemPosition();
//        } else {
//           int newPosition = mLineManager.findLastVisibleItemPosition() - (position1 - position2);
//            getLayoutManager().scrollToPosition(newPosition);
//
//        }
    }

    private int dy;
    private boolean flag;  //是否要累加y

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void onScrolled(int dx, int dy) {
        if (!flag)
            this.dy += dy;
        super.onScrolled(dx, dy);

    }

    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);
        if (state==1) {
            flag = false;
        }

    }

    public void setDown(boolean down) {
        isDown = down;
    }


}
