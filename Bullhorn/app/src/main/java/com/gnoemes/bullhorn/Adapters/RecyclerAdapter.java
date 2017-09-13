package com.gnoemes.bullhorn.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gnoemes.bullhorn.Models.Networking.Model.Article.Article;
import com.gnoemes.bullhorn.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.NewsViewHolder> {

    private List<Article> articles;
    private final OnItemClickListener listener;

    public RecyclerAdapter(List<Article> articles, OnItemClickListener listener) {
        this.articles = articles;
        this.listener = listener;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new NewsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) throws NullPointerException{
        holder.click(articles.get(position), listener);
        holder.mTitle.setText(articles.get(position).getTitle());
        holder.mAuthor.setText(articles.get(position).getAuthor());
        holder.mDescription.setText(articles.get(position).getDescription());
        String[] date = articles.get(position).getPublishedAt().split("T");
        holder.mDate.setText(date[0]);
        Picasso.with(holder.mImage.getContext())
                .load(articles.get(position).getUrlToImage())
                .into(holder.mImage);
    }

    @Override
    public int getItemCount() {
        if (articles == null) {
            return 0;
        }
        return articles.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.news_preview)
        ImageView mImage;
        @BindView(R.id.news_title)
        TextView mTitle;
        @BindView(R.id.news_author)
        TextView mAuthor;
        @BindView(R.id.news_description)
        TextView mDescription;
        @BindView(R.id.news_date)
        TextView mDate;

        public NewsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void click(final Article article, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(article);
                }
            });
        }

    }

    public interface OnItemClickListener {
        void onClick(Article item);
    }
}