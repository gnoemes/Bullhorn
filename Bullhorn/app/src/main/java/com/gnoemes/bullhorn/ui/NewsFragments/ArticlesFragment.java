package com.gnoemes.bullhorn.ui.NewsFragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gnoemes.bullhorn.Adapters.RecyclerAdapter;
import com.gnoemes.bullhorn.Common.BaseFragment;
import com.gnoemes.bullhorn.Models.Networking.Model.Article.Article;
import com.gnoemes.bullhorn.R;
import com.gnoemes.bullhorn.di.Components.AppComponent;
import com.gnoemes.bullhorn.di.Components.ArticleComponent;
import com.gnoemes.bullhorn.di.Components.DaggerArticleComponent;
import com.gnoemes.bullhorn.di.Modules.FragmentModule.ArticlesModule;
import com.gnoemes.bullhorn.ui.details.DetailsActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticlesFragment extends BaseFragment implements IArticlesView{

    @BindView(R.id.articles_error_text)
    TextView errorText;
    @BindView(R.id.articles_error_image)
    ImageView errorImage;
    @BindView(R.id.articles_progressBar)
    ProgressBar progressBar;
    @BindView(R.id.recyclerList)
    RecyclerView recyclerView;

    @Inject
    IArticlesPresenter presenter;

    private ArticleComponent articleComponent;
    private static final String SOURCE_KEY = "source";
    private String sourceId;
    private RecyclerAdapter recyclerAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sourceId = getArguments().getString(SOURCE_KEY);

        recyclerAdapter = new RecyclerAdapter(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onClick(Article item) {
                Log.i("DEVE", "onClick: Clicked " + item.getTitle());
                presenter.itemClicked(item);
            }
        });
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(recyclerAdapter);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_articles,container,false);
    }

    @Override
    public void setupComponent(AppComponent appComponent) {
        articleComponent = DaggerArticleComponent.builder()
                .appComponent(appComponent)
                .articlesModule(new ArticlesModule(this))
                .build();
        articleComponent.inject(this);
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
        presenter.loadArticlesList(sourceId);
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
    public void updateArticleList(List<Article> articles) {
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
}
