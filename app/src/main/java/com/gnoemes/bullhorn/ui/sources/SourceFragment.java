package com.gnoemes.bullhorn.ui.sources;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.gnoemes.bullhorn.App;
import com.gnoemes.bullhorn.R;
import com.gnoemes.bullhorn.adapters.ViewPagerAdapter;
import com.gnoemes.bullhorn.data.model.source.Source;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SourceFragment extends MvpAppCompatFragment implements SourceView {

    private ViewPagerAdapter pagerAdapter;

    @InjectPresenter
    SourcePresenter presenter;
    @ProvidePresenter
    SourcePresenter providePresenter() {
        return new SourcePresenter(App.getAppComponent().getNewsRepository());
    }

    @BindView(R.id.sources_error_text)
    TextView errorText;
    @BindView(R.id.sources_error_image)
    ImageView errorImage;
    @BindView(R.id.sources_progress)
    ProgressBar progressBar;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.pagerTab)
    TabLayout pagerTab;

    private static final String ITEM_KEY = "current_item";
    private Unbinder unbinder;
    private String category;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        category = getTag();
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        pagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        return inflater.inflate(R.layout.fragment_sources,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this,view);
        pagerTab.setupWithViewPager(viewPager);
        viewPager.setAdapter(pagerAdapter);
        presenter.loadSourcesList(category);
        viewPager.setBackgroundResource(R.drawable.background_main);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        loadSelectedPage();
    }

    @Override
    public void onPause() {
        super.onPause();
        saveSelectedPage();
    }

    private void saveSelectedPage() {
        final SharedPreferences.Editor ed = getActivity().getSharedPreferences("name", android.content.Context.MODE_PRIVATE).edit();
        ed.putInt(ITEM_KEY, viewPager.getCurrentItem());
        ed.apply();
    }

    private void loadSelectedPage() {
        int page = getActivity().getSharedPreferences("name", Context.MODE_PRIVATE).getInt(ITEM_KEY, 0);
        TabLayout.Tab  tab = pagerTab.getTabAt(page);
        if (tab != null) {
            tab.select();
            pagerTab.setScrollPosition(pagerTab.getSelectedTabPosition(),0f,true);
        }
        Log.i("DEVE", "onClick: " + pagerTab.getSelectedTabPosition());
        viewPager.setCurrentItem(page);
    }

    @Override
    public void showLoad() {
        progressBar.setVisibility(View.VISIBLE);
        pagerTab.setVisibility(View.INVISIBLE);
        viewPager.setVisibility(View.INVISIBLE);
        errorImage.setVisibility(View.GONE);
        errorText.setVisibility(View.GONE);
    }

    @Override
    public void showContent() {
        progressBar.setVisibility(View.GONE);
        pagerTab.setVisibility(View.VISIBLE);
        viewPager.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError() {
        progressBar.setVisibility(View.GONE);
        pagerTab.setVisibility(View.INVISIBLE);
        viewPager.setVisibility(View.INVISIBLE);
        errorImage.setVisibility(View.VISIBLE);
        errorText.setVisibility(View.VISIBLE);
    }

    @Override
    public void updateSourceList(List<Source> sources) {
        pagerAdapter.updateAdapter(sources);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
        unbinder.unbind();
        pagerAdapter = null;
    }


}
