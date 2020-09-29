package com.smh.szyproject.test.jetpack.bilibiliJetPack.navigation4;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smh.szyproject.R;

public class CKFragment extends Fragment {

    //在文件夹里点右键

    /**
     * new -fragment- fragment with viewModel
     */

    private CKViewModel mViewModel;

    public static CKFragment newInstance() {
        return new CKFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.test_c_k_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(CKViewModel.class);
        // TODO: Use the ViewModel
    }

}