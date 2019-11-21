package com.project.sopmmobileapp.view.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.project.sopmmobileapp.view.fragments.pager.AllSurveysFragment;
import com.project.sopmmobileapp.view.fragments.pager.MySurveysFragment;
import com.project.sopmmobileapp.view.fragments.pager.SurveysInAreaFragment;

import java.util.Arrays;
import java.util.List;

public class AdapterTabsPager extends FragmentStatePagerAdapter {

    private  List<String> titles;

    public AdapterTabsPager(FragmentManager fragmentManager, List<String> titles) {
        super(fragmentManager);
        this.titles = titles;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new MySurveysFragment();
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return new SurveysInAreaFragment();
            case 2:
                return new AllSurveysFragment();
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

}
