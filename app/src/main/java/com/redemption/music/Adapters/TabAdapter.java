package com.redemption.music.Adapters;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.redemption.music.Fragments.HomeFragment;
import com.redemption.music.Fragments.MovieFragment;
import com.redemption.music.Fragments.SportFragment;

public class TabAdapter extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;

    public TabAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                HomeFragment homeFragment = new HomeFragment();
                return homeFragment;
            case 1:
                SportFragment sportFragment = new SportFragment();
                return sportFragment;
            case 2:
                MovieFragment movieFragment = new MovieFragment();
                return movieFragment;
            default:
                return null;
        }
    }
    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }
}
