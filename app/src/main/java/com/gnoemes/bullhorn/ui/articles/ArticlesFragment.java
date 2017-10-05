package com.gnoemes.bullhorn.ui.articles;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.gnoemes.bullhorn.adapters.RecyclerAdapter;
import com.gnoemes.bullhorn.data.model.article.ArticleEntity;
import com.gnoemes.bullhorn.ui.details.DetailsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ArticlesFragment extends MvpAppCompatFragment implements ArticlesView {

    @BindView(R.id.articles_error_text)
    TextView errorText;
    @BindView(R.id.articles_error_image)
    ImageView errorImage;
    @BindView(R.id.articles_progressBar)
    ProgressBar progressBar;
    @BindView(R.id.recyclerList)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;

    @InjectPresenter
    ArticlesPresenter presenter;
    @ProvidePresenter
    ArticlesPresenter providePresenter() {
        return new ArticlesPresenter(App.getAppComponent().getNewsRepository());
    }

    private static final String SOURCE_KEY = "source";
    private Unbinder unbinder;
    private String sourceId;
    private RecyclerAdapter recyclerAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sourceId = getArguments().getString(SOURCE_KEY);
        setRetainInstance(true);

        recyclerAdapter = new RecyclerAdapter((ArticleEntity item) -> {
            Log.i("DEVE", "onClick: Clicked " + item.getTitle());
            presenter.itemClicked(item);
        });
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder =  ButterKnife.bind(this,view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(recyclerAdapter);

        boolean force = savedInstanceState == null;
        presenter.loadArticlesList(sourceId, force);

        swipeRefresh.setOnRefreshListener(() -> {
            presenter.loadArticlesList(sourceId,true);
            swipeRefresh.setRefreshing(false);
        });

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_articles,container,false);
    }

    public static Fragment newInstance(String sourceName) {
        ArticlesFragment fragment = new ArticlesFragment();
        Bundle bundle = new Bundle();
        bundle.putString(SOURCE_KEY,sourceName);
        Log.i("DEVE", "onCreate: " + sourceName);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("first",false);
    }

    @Override
    public void showLoad() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);
        errorImage.setVisibility(View.GONE);
        errorText.setVisibility(View.GONE);
    }

    @Override
    public void showContent() {
        progressBar.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.INVISIBLE);
        errorImage.setVisibility(View.VISIBLE);
        errorText.setVisibility(View.VISIBLE);
    }

    @Override
    public void updateArticleList(List<ArticleEntity> articles) {
        recyclerAdapter.addAll(articles);
    }

    @Override
    public void showDetails(Parcelable article) {
        Intent intent = new Intent(getActivity().getApplicationContext(),DetailsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("article",article);
        Log.i("DEVE", "showDetails: ");
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        presenter.onDestroy();
    }
}
