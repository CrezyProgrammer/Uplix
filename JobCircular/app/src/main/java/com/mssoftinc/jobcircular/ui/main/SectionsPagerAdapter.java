package com.mssoftinc.jobcircular.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.mssoftinc.jobcircular.R;
import com.mssoftinc.jobcircular.Tab;

import java.util.ArrayList;
import java.util.List;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {



    private static final String[] TAB_TITLES = new String[]{"govt","non govt"};
    List<String> list=new ArrayList<>();




    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
        list.add("govt");
        list.add("govt");
        list.add("govt");
        list.add("govt");
        list.add("govt");
    }


    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return PlaceholderFragment.newInstance(position + 1);
        //Log.i("123321", ""+TAB_TITLES.g);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return Tab.list.get(position);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
       return Tab.list.size();
    }
}