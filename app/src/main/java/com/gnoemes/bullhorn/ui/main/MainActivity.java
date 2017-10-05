package com.gnoemes.bullhorn.ui.main;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.gnoemes.bullhorn.R;
import com.gnoemes.bullhorn.ui.sources.SourceFragment;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

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

        if (savedInstanceState == null) {
            loadFragment("general");
            getSupportActionBar().setTitle(getResources().getString(R.string.msg_nav_general));
        } else {
          getSupportActionBar().setTitle(savedInstanceState.getString("category"));
        }
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_general:
                loadFragment("general");
                break;
            case R.id.nav_politics:
                loadFragment("politics");
                break;
            case R.id.nav_sport:
                loadFragment("sport");
                break;
            case R.id.nav_entertainment:
                loadFragment("entertainment");
                break;
            case R.id.nav_gaming:
                loadFragment("gaming");
                break;
            case R.id.nav_science:
                loadFragment("science-and-nature");
                break;
            case R.id.nav_technologies:
                loadFragment("technology");
                break;
            case R.id.nav_business:
                loadFragment("business");
                break;
            case R.id.nav_music:
                loadFragment("music");
                break;
        }
        getSupportActionBar().setTitle(item.getTitle());
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void loadFragment(String category) {
        SourceFragment fragment = new SourceFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.source_container, fragment,category).commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("notFirst",false);
        outState.putString("category", (String) getSupportActionBar().getTitle());
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.i("INSTANCE", "onPause: ");
    }
}
