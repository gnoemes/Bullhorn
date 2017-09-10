package com.gnoemes.bullhorn.ui;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.gnoemes.bullhorn.R;
import com.gnoemes.bullhorn.models.DataManager;
import com.gnoemes.bullhorn.models.DataManagerHelper;
import com.gnoemes.bullhorn.models.database.DatabaseApp;
import com.gnoemes.bullhorn.models.database.DatabaseHelper;
import com.gnoemes.bullhorn.models.networking.NewsNetworkApp;
import com.gnoemes.bullhorn.models.networking.NewsNetworkHelper;
import com.gnoemes.bullhorn.models.networking.data.NetworkEndpoints;
import com.gnoemes.bullhorn.models.networking.model.article.Article;
import com.gnoemes.bullhorn.models.preference.PreferenceApp;
import com.gnoemes.bullhorn.models.preference.PreferenceHelper;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

//    private final MainViewPresenter presenter;
//    private final DataManagerHelper dataManagerHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        initPresenter();


    }

    private void initPresenter() {

        NewsNetworkHelper networkHelper = new NewsNetworkApp();
        PreferenceHelper preferenceHelper = new PreferenceApp(getApplicationContext());
        DatabaseHelper databaseHelper = new DatabaseApp(getApplicationContext(),"wqe",1);

        DataManagerHelper managerHelper = new DataManager(getApplicationContext(),networkHelper,preferenceHelper,databaseHelper);


        networkHelper.getArticles().getArticlesData("bbc-news", NetworkEndpoints.API_KEY)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Article>() {
                    @Override
                    public void accept(Article article) throws Exception {
                        Log.i("DEVE", "accept: " +article.getArticles().get(0).getTitle());
                        Log.i("DEVE", "accept: " +article.getArticles().get(1).getTitle());
                    }
                });
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


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
