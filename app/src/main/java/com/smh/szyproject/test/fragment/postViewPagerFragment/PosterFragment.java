package com.smh.szyproject.test.fragment.postViewPagerFragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;


import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseFragment;

import butterknife.BindView;

/**
 * Create by smh on 2019/4/2.
 */
public class PosterFragment extends BaseFragment {
    @BindView(R.id.tv_msg)
    TextView msg;
    @BindView(R.id.rl_vp)
    RelativeLayout rl_vp;
    @BindView(R.id.iv_code)
    ImageView iv_code;
    @BindView(R.id.ln_posterText)
    LinearLayout ln_posterText;
    @BindView(R.id.tv_msgBottom)
    TextView tv_msgBottom;
    @BindView(R.id.tv_msgToast)
    TextView tv_msgToast;
    private getBitmapListener mListener;
    private int invitationCode;

    @Override
    protected void init() {
        changeBackgoundImage(getArguments().getInt("id"));
    }

    @Override
    public void onResume() {
        super.onResume();
        changeBackgoundImage(getArguments().getInt("id"));
    }



    private void changeBackgoundImage(int ids) {
        switch (ids) {
            case 0:
                rl_vp.setBackground(getResources().getDrawable(R.mipmap.test_poster1p));
                break;
            case 1:
                rl_vp.setBackground(getResources().getDrawable(R.mipmap.test_poster2p));
                break;
            case 2:
                rl_vp.setBackground(getResources().getDrawable(R.mipmap.test_poster3p));
                break;
        }

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.test_poster_fragment;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof getBitmapListener) {
            mListener = (getBitmapListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }

    }



    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface getBitmapListener {
        void getBitMap(Bitmap data, String msg);
    }
}
