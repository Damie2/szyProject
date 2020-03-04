package com.smh.szyproject.ui.view;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ViewHelp {
    private static final ViewHelp ourInstance = new ViewHelp();

    public static ViewHelp getInstance() {
        return ourInstance;
    }

    private ViewHelp() {
    }

    public <T extends View> void setImgForViewTop(T view, Drawable drawable) {
        if (view instanceof TextView) {
            ((TextView) view).setCompoundDrawablesWithIntrinsicBounds(null,
                    drawable, null, null);
        }
    }
    public <T extends View> void setImgForViewRight(T view, Drawable drawable) {
        if (view instanceof TextView) {
           // ((TextView) view).setCompoundDrawablePadding(size);

            ((TextView) view).setCompoundDrawablesWithIntrinsicBounds(null,
                    null, drawable, null);
        }
    }

    public void setTopPadding(View view, int padding) {
        view.setPadding(view.getPaddingLeft(), padding, view.getPaddingRight(), view.getPaddingBottom());
    }

    public void setBottomPadding(View view, int padding) {
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), padding);
    }

    public void setTopMargin(View v, int padding) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(v.getLeft(), padding, v.getRight(), v.getBottom());
            v.requestLayout();
        }
    }

    public void setImgForViewLeft(TextView view, Drawable drawable) {
        if (view instanceof TextView) {
            // ((TextView) view).setCompoundDrawablePadding(size);

            ((TextView) view).setCompoundDrawablesWithIntrinsicBounds(drawable,
                    null, null, null);
        }
    }
}