package com.smh.szyproject.common.base;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;


/**
 * Created by android on 2018/6/1.
 */

public class CommonPopupWindow extends PopupWindow {
    private Context context;
    public CommonPopupWindow(Context context, Build build) {
        super(context);
        init(context, build);
    }

    private void init(Context context, Build build) {
        this.context=context;
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = mInflater.inflate(build.LayoutId, null);
        setContentView(view);
        /**
         * 设置背景只有设置了这个才可以点击外边和BACK消失
         */
        if (build.getBackground_color() != 0) {
            setBackgroundDrawable(new ColorDrawable(build.getBackground_color()));
        } else {
            setBackgroundDrawable(new ColorDrawable());
        }
        setOutsideTouchable(true);
        setTouchable(true);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        if (build.height != 0)
            setHeight(build.height);
        if (build.width != 0)
            setWidth(build.width);
        if (build.animationId != 0)
            setAnimationStyle(build.animationId);
        if (build.getViewInterface() != null)
            build.getViewInterface().operateChildView(view);

    }
    private long time;
    @Override
    public void dismiss() {
        super.dismiss();
        time= System.currentTimeMillis();
    }

    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff) {
        if (android.os.Build.VERSION.SDK_INT == android.os.Build.VERSION_CODES.N||android.os.Build.VERSION.SDK_INT == android.os.Build.VERSION_CODES.N_MR1) {
            Rect rect = new Rect();
            anchor.getGlobalVisibleRect(rect);
            int h = anchor.getResources().getDisplayMetrics().heightPixels - rect.bottom;
            setHeight(h);
        }
        if (System.currentTimeMillis()-time>500){
            super.showAsDropDown(anchor, xoff, yoff);
        }

    }

    public static class Build {
        private int background_color;
        private int LayoutId;
        private int animationId;
        private int height;
        private int width;
        private Context context;
        private PopupWindowViewInterface viewInterface;

        public PopupWindowViewInterface getViewInterface() {
            return viewInterface;
        }

        public Build setViewInterface(PopupWindowViewInterface viewInterface) {
            this.viewInterface = viewInterface;
            return this;
        }

        public Build(Context context) {
            this.context = context;
        }

        public Build setBackground_color(int background_color) {
            this.background_color = background_color;
            return this;
        }

        public int getBackground_color() {
            return background_color;
        }

        public int getLayoutId() {
            return LayoutId;
        }

        public int getAnimationId() {
            return animationId;
        }

        public int getHeight() {
            return height;
        }

        public int getWidth() {
            return width;
        }

        public Build setLayoutId(int layoutId) {
            LayoutId = layoutId;
            return this;
        }

        public Build setAnimationId(int animationId) {
            this.animationId = animationId;
            return this;
        }

        public Build setHeight(int height) {
            this.height = height;
            return this;
        }

        public Build setWidth(int width) {
            this.width = width;
            return this;
        }

        public CommonPopupWindow build() {
            return new CommonPopupWindow(context, this);
        }
    }

    public interface PopupWindowViewInterface {
        void operateChildView(View view);
    }
}
