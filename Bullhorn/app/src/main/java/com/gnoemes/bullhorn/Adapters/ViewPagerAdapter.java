package com.gnoemes.bullhorn.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.gnoemes.bullhorn.Models.Networking.Model.Source.Source;
import com.gnoemes.bullhorn.ui.NewsFragments.ArticlesFragment;

import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<Source> sources;


    public ViewPagerAdapter(FragmentManager fm ){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return ArticlesFragment.newInstance(sources.get(position).getId());
    }

    @Override
    public int getCount() {
        return sources == null ? 0 : sources.size();

    }
    public void updateAdapter(List<Source> sources) {
        this.sources = sources;
        notifyDataSetChanged();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return sources.get(position).getName();
    }

}