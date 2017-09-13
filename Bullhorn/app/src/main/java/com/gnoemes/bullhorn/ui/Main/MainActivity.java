package com.gnoemes.bullhorn.ui.Main;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.gnoemes.bullhorn.Adapters.ViewPagerAdapter;
import com.gnoemes.bullhorn.Common.BaseActivity;
import com.gnoemes.bullhorn.Models.Networking.Model.Article.Article;
import com.gnoemes.bullhorn.R;
import com.gnoemes.bullhorn.di.Components.AppComponent;
import com.gnoemes.bullhorn.di.Components.DaggerMainComponent;
import com.gnoemes.bullhorn.di.Components.MainComponent;
import com.gnoemes.bullhorn.di.Modules.ActivityModule.MainModule;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, IMainView{

    @Inject
    IMainPresenter presenter;

    private MainComponent mainComponent;
    private List<String> sources;
    private ViewPagerAdapter pagerAdapter;

    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.pagerTab)
    TabLayout pagerTab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        sources = new ArrayList<>();
        pagerAdapter = new ViewPagerAdapter(presenter,getSupportFragmentManager(),sources);
        viewPager.setAdapter(pagerAdapter);

    }

    @Override
    public void setupComponent(AppComponent upComponent) {
        mainComponent = DaggerMainComponent.builder()
                .appComponent(upComponent)
                .mainModule(new MainModule(this))
                .build();
        mainComponent.inject(this);
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.nav_general:
                presenter.loadSources("general");
                break;
            case R.id.nav_politics:
                presenter.loadSources("politics");
                break;
            case R.id.nav_sport:
                presenter.loadSources("sport");
                break;
            case R.id.nav_entertainment:
                presenter.loadSources("entertainment");
                break;
            case R.id.nav_gaming:
                presenter.loadSources("gaming");
                break;
            case R.id.nav_science:
                presenter.loadSources("science-and-nature");
                break;
            case R.id.nav_technologies:
                presenter.loadSources("technology");
                break;
            case R.id.nav_business:
                presenter.loadSources("business");
                break;
            case R.id.nav_music:
                presenter.loadSources("music");
                break;


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public IMainPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void updateSources(List<String> sources) {
        setCurrentCategory(sources);
        pagerAdapter.updateAdapter(sources);
        pagerTab.setVisibility(View.VISIBLE);

    }

    private void setCurrentCategory(List<String> sources) {
        this.sources = sources;
    }

    @Override
    public List<String> getCurrentCategory() {
        return sources;
    }

    @Override
    public void updateArticles(List<Article> articleList) {
        pagerAdapter.setArticles(articleList);
        pagerAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showContent() {

    }

    public MainComponent getMainComponent() {
        return mainComponent;
    }
}
