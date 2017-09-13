package com.gnoemes.bullhorn.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.gnoemes.bullhorn.Models.Networking.Model.Article.Article;
import com.gnoemes.bullhorn.ui.Articles.ArticlesFragment;

import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<String> sources;
    private List<Article> articles;


    public ViewPagerAdapter(FragmentManager fm, List<String> sources) {
        super(fm);
        this.sources = sources;

    }



    @Override
    public Fragment getItem(int position) {
        return ArticlesFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        if (sources.size() == 0) {
            return 0;
        }
        return sources.size();
    }

    public void updateAdapter(List<String> sources) {
        this.sources = sources;
        notifyDataSetChanged();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return sources.get(position);
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
        for (Article a:articles) {
            Log.i("DEVE", "setArticles: "  + a.getTitle());
        }
        notifyDataSetChanged();
    }
}