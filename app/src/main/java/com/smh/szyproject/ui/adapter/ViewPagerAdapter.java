package com.smh.szyproject.ui.adapter;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * Created by Administrator on 2018/8/3.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private Fragment[]fragments;
    private String[] title;
    public ViewPagerAdapter(FragmentManager fm, Fragment[] fragments, String[] title) {
        super(fm);
        this.fragments = fragments;
        this.title=title;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (title==null)
            return super.getPageTitle(position);
        return title[position];
    }
}
