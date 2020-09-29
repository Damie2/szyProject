package com.smh.szyproject.test.customView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * author : smh
 * date   : 2020/8/25 13:58
 * desc   :圆形
 */
public class CustomView extends View {
    Paint paint;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


//    https://www.jianshu.com/p/76603b122fb4   看这玩意


    @Override
    protected void onDraw(Canvas canvas) {
        paint = new Paint();
        paint.setColor(Color.RED);
//        paint.setStyle(Paint.Style.FILL);//画笔属性是实心圆
        paint.setStyle(Paint.Style.STROKE);//画笔属性空心圆
        paint.setStrokeWidth(80);//设置画笔粗细



//        paint.setAntiAlias(true); //设置是否抗锯齿;
//        paint.setStyle(Paint.Style.FILL_AND_STROKE); //设置填充样式
//        paint.setColor(Color.GREEN);//设置画笔颜色
//        paint.setStrokeWidth(2);//设置画笔宽度
//        paint.setShadowLayer(10,15,15,Color.RED);//设置阴影





        /**
         * 四个参数
         * 1.圆心的x轴坐标
         * 2.圆心的y坐标
         * 3.圆的半径
         * 4、画笔
         */
        canvas.drawCircle(getWidth()/2,getHeight()/2,200,paint);

    }
}
