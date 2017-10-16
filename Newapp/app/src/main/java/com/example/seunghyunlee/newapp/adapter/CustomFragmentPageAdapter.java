package com.example.seunghyunlee.newapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.seunghyunlee.newapp.fragments.SingleMenu;

/**
 * Created by seunghyunlee on 7/21/17.
 */

public class CustomFragmentPageAdapter extends FragmentPagerAdapter{
    private static final String TAG = CustomFragmentPageAdapter.class.getSimpleName();
    private static final int FRAGMENT_COUNT =4;

    public CustomFragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new SingleMenu();
            case 1:
                return new SingleMenu();
            case 2:
                return new SingleMenu();
            case 3:
                return new SingleMenu();
        }
        return null;
    }

    @Override
    public int getCount() {
        return FRAGMENT_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Single Menu";
            case 1:
                return "Set Menu";
            case 2:
                return "Something";
            case 3:
                return "Soemthingelse";
        }
        return null;
    }
}
