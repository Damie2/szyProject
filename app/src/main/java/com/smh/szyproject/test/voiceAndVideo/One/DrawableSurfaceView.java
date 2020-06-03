package com.smh.szyproject.test.voiceAndVideo.One;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.smh.szyproject.R;

/**
 * author : smh
 * date   : 2020/5/29 10:49
 * desc   : 自定义SurfaceView
 */
class DrawableSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private Bitmap bitmap;

    public DrawableSurfaceView(Context context) {
        super(context);
        init(context,null);
    }

    public DrawableSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        Drawable drawable;
        //如果调用的是第一个构造方法，就是没有参数的构造方法
        if(attrs!=null){
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.DrawableSurfaceView);
            drawable = array.getDrawable(R.styleable.DrawableSurfaceView_src);
            //TypedArray回收
            array.recycle();
        }else{
            drawable = context.getResources().getDrawable(R.mipmap.ic_launcher);
        }
        if(drawable==null){
            throw new RuntimeException("DrawableSurfaceView get null drawable");
        }
        getHolder().addCallback(this);
        bitmap =((BitmapDrawable) drawable).getBitmap();
        drawable.setCallback(this);
        drawable.setLevel(0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);
        canvas.drawBitmap(bitmap,0,0,null);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Canvas canvas = null;
        try {
            canvas = holder.lockCanvas();
            onDraw(canvas);
        } finally {
            if (canvas != null) {
                holder.unlockCanvasAndPost(canvas);
            }
        }
    }


    //下面这俩没什么用
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
