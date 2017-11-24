package com.codetoart.MovieDetails;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by 638 on 23-Nov-17.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private int pages;
    private ArrayList<String> urls;
    ViewPagerAdapter(FragmentManager fragmentManager, int pages, ArrayList<String> images) {
        super(fragmentManager);
        this.urls = images;
        this.pages = pages;
    }

    @Override
    public int getCount() {
        return pages;
    }

    @Override
    public Fragment getItem(int position) {
        return PageFragment.newInstance(urls.get(position));
    }
}