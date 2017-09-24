package com.gnoemes.bullhorn.ui.news;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gnoemes.bullhorn.adapters.ViewPagerAdapter;
import com.gnoemes.bullhorn.common.BaseFragment;
import com.gnoemes.bullhorn.models.networking.model.source.Source;
import com.gnoemes.bullhorn.R;
import com.gnoemes.bullhorn.di.components.AppComponent;
import com.gnoemes.bullhorn.di.components.DaggerSourceComponent;
import com.gnoemes.bullhorn.di.components.SourceComponent;
import com.gnoemes.bullhorn.di.modules.fragments.SourceModule;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SourceFragment extends BaseFragment implements ISourceView{

    private ViewPagerAdapter pagerAdapter;

    @Inject
    ISourcePresenter presenter;

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
    private String category;
    private SourceComponent sourceComponent;
    private int page;
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
        ButterKnife.bind(this,view);
        viewPager.setAdapter(pagerAdapter);

        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void setupComponent(AppComponent appComponent){
            sourceComponent = DaggerSourceComponent.builder()
                    .appComponent(appComponent)
                    .sourceModule(new SourceModule(this))
                    .build();
            sourceComponent.inject(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        final SharedPreferences.Editor ed = getActivity().getSharedPreferences("name", android.content.Context.MODE_PRIVATE).edit();
        ed.putInt(ITEM_KEY, viewPager.getCurrentItem());
        ed.apply();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.loadSourcesList(category);
        final SharedPreferences sp = getActivity().getSharedPreferences("name",
                android.content.Context.MODE_PRIVATE);
        viewPager.setCurrentItem(sp.getInt(ITEM_KEY, 0));


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
        pagerAdapter = null;
    }


}
