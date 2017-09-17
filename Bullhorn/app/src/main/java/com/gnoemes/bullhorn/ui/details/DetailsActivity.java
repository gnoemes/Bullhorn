package com.gnoemes.bullhorn.ui.details;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gnoemes.bullhorn.Common.BaseActivity;
import com.gnoemes.bullhorn.Models.Preference.ArticleParcelable;
import com.gnoemes.bullhorn.R;
import com.gnoemes.bullhorn.di.Components.AppComponent;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DetailsActivity  extends BaseActivity{

    @BindView(R.id.details_toolbar)
    Toolbar toolbar;
    @BindView(R.id.details_fab)
    FloatingActionButton fab;
    @BindView(R.id.details_image)
    ImageView imageView;
    @BindView(R.id.details_title)
    TextView textTitle;
    @BindView(R.id.details_date)
    TextView textDate;
    @BindView(R.id.details_description)
    TextView textDescription;
    @BindView(R.id.details_progress)
    ProgressBar progressBar;

    private Unbinder unbinder;
    private Intent networkIntent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        unbinder = ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        ArticleParcelable parcelable =  getIntent().getParcelableExtra("article");
        loadArticle(parcelable);

        final String articleIUrl = parcelable.getUrl();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                networkIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(articleIUrl));
                startActivity(networkIntent);
            }
        });
    }

    private void loadArticle(final ArticleParcelable parcelable) {
        progressBar.setVisibility(View.VISIBLE);
        String title = parcelable.getTitle() == null ? getResources().getString(R.string.err_item_no_title): parcelable.getTitle();
        String description = parcelable.getDescription() == null ? getResources().getString(R.string.err_item_no_description): parcelable.getDescription();
        String date = parcelable.getPublishedAt() == null ? getResources().getString(R.string.err_item_no_date): parcelable.getPublishedAt();

        textTitle.setText(title);
        textDate.setText(date);
        textDescription.setText(description);
        Picasso.with(DetailsActivity.this)
                .load(parcelable.getmUrlToImage())
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                            progressBar.setVisibility(View.GONE);
                        }

                    @Override
                    public void onError() {
                        Picasso.with(DetailsActivity.this)
                                .load(parcelable.getmUrlToImage())
                                .into(imageView, new Callback() {
                                    @Override
                                    public void onSuccess() {
                                        if (progressBar != null) {
                                            progressBar.setVisibility(View.GONE);
                                        }
                                    }

                                    @Override
                                    public void onError() {

                                    }
                                });
                        }
                    });

    }

    @Override
    public void setupComponent(AppComponent upComponent) {
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
