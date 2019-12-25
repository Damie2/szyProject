package com.smh.szyproject.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by Administrator on 2016/7/29.
 * 作者：任洋
 * 功能：
 */
public class ToastUtils {
    private static Toast toast;
    private final static int imageViewId = 99 * 99;

    /**
     * @param context              上下文
     * @param text                 显示的文本
     * @param imageResId           图片资源Id
     * @param gravitys             位置数组 0 居中的位置 1 偏右多少 2，是偏左多少gr
     * @param backgroundResourceId 背景资源id
     **/
    public static void showToast(Context context, String text, int imageResId, int[] gravitys, int backgroundResourceId) {
        if (toast == null)
            toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        else {
            toast.setText(text);
        }

        LinearLayout toastView = (LinearLayout) toast.getView();
        if (backgroundResourceId > 0)
            toastView.setBackgroundResource(backgroundResourceId);
        if (imageResId > 0) {
            ImageView imageCodeProject = toastView.findViewById(imageViewId);
            if (imageCodeProject == null) {
                imageCodeProject = new ImageView(context);
                imageCodeProject.setId(imageViewId);
                toastView.addView(imageCodeProject, 0);
            }
            imageCodeProject.setImageResource(imageResId);
        }
        TextView message = toastView.findViewById(android.R.id.message);
        message.setText(text);
        if (gravitys != null && gravitys.length == 3)
            toast.setGravity(gravitys[0], gravitys[1], gravitys[2]);
        toast.show();
    }

    public static void showToastForResId(Context mContext, int resId) {
        showToast(mContext, mContext.getResources().getString(resId), 0, new int[]{Gravity.BOTTOM}, 0);
    }

    public static void showToastForText(Context mContext, String text) {
        showToast(mContext, text, 0, null, 0);
    }

    public static void showToastImg(Context mContext, String text, int imageResId) {
        showToast(mContext, text, imageResId, null, 0);
    }

    public static void showToastBg(Context mContext, String text, int bgId) {
        showToast(mContext, text, 0, null, bgId);
    }

    public static void showToastNoImg(Context context, String text, int[] gravitys) {
        if (toast == null)
            toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        else {
            toast.setText(text);
        }
        LinearLayout toastView = (LinearLayout) toast.getView();

        if (gravitys != null && gravitys.length == 3)
            toast.setGravity(gravitys[0], gravitys[1], gravitys[2]);
        toast.show();
    }
}
