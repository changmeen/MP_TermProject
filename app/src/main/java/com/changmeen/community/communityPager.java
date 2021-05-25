package com.changmeen.community;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class communityPager extends FragmentPagerAdapter {

    private int tabCount;

    public communityPager(@NonNull FragmentManager fm, int tabCount) {
        super(fm, tabCount);
        this.tabCount = tabCount;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: {
                return new communityRice();
            }
            case 1: {
                return new communityNoodle();
            }
            default: {
                return null;
            }
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
