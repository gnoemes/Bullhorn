package com.gnoemes.bullhorn.ui.Articles;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gnoemes.bullhorn.Adapters.RecyclerAdapter;
import com.gnoemes.bullhorn.Common.BaseFragment;
import com.gnoemes.bullhorn.Models.Networking.Model.Article.Article;
import com.gnoemes.bullhorn.R;
import com.gnoemes.bullhorn.di.Components.AppComponent;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticlesFragment extends BaseFragment {



    @BindView(R.id.recyclerlist)
    RecyclerView recyclerView;

    private List<Article> articles;
    private RecyclerAdapter recyclerAdapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int pos = getArguments().getInt("num");
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);

        recyclerView.setAdapter(getRecyclerAdapter());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_articles,container,false);
    }

    @Override
    public void setupComponent(AppComponent upComponent) {

    }

    public void setAdapter(RecyclerAdapter adapter) {
        recyclerAdapter = adapter;
    }

    public RecyclerAdapter getRecyclerAdapter() {
        return recyclerAdapter;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;

    }

    public static Fragment newInstance(int pos) {
        ArticlesFragment fragment = new ArticlesFragment();
        Bundle bundle = new Bundle();
//        fragment.setArticles();
//        fragment.setAdapter(new RecyclerAdapter(articles, new RecyclerAdapter.OnItemClickListener() {
//            @Override
//            public void onClick(Article item) {
//
//            }
//        }));
//        bundle.putInt("num",pos);
//
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("DEVE", "onResume: ");
    }
}
