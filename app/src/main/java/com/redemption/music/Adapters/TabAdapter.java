package com.redemption.music.Adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.redemption.music.Fragments.AlbumsFragment;
import com.redemption.music.Fragments.ArtistsFragment;
import com.redemption.music.Fragments.GenresFragment;
import com.redemption.music.Fragments.PlaylistFragment;
import com.redemption.music.Fragments.SongsFragment;

public class TabAdapter extends FragmentPagerAdapter {

    int totalTabs;

    public TabAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new PlaylistFragment();
            case 1:
                return new ArtistsFragment();
            case 2:
                return new AlbumsFragment();
            case 3:
                return new SongsFragment();
            case 4:
                return new GenresFragment();
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
