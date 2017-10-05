package com.gnoemes.bullhorn.adapters;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gnoemes.bullhorn.R;
import com.gnoemes.bullhorn.data.model.article.ArticleEntity;
import com.gnoemes.bullhorn.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.NewsViewHolder> {

    private List<ArticleEntity> articles;
    private final OnItemClickListener listener;

    public RecyclerAdapter(OnItemClickListener listener) {
        this.listener = listener;
        articles = new ArrayList<>();
    }

    public void addAll(List<ArticleEntity> articles) {
        this.articles = articles;
        notifyDataSetChanged();
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new NewsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position){
        holder.click(articles.get(position), listener);
        String title = articles.get(position).getTitle();
        String description = articles.get(position).getDescription();
        String author = articles.get(position).getAuthor();
        String date = Utils.formatDate(articles.get(position));
        if (TextUtils.isEmpty(title)) {
            holder.mTitle.setText(R.string.err_item_no_title);
        } else  {
            holder.mTitle.setText(title);
        }
        if (TextUtils.isEmpty(description)) {
            holder.mDescription.setText(R.string.err_item_no_description);
        } else  {
            holder.mDescription.setText(description);
        }
        if (TextUtils.isEmpty(author)) {
            holder.mAuthor.setText(R.string.err_item_no_author);
        } else  {
            holder.mAuthor.setText(author);
        }
        if (TextUtils.isEmpty(date)) {
            holder.mDescription.setText(R.string.err_item_no_date);
        } else  {
            holder.mDescription.setText(date);
        }
    }

    @Override
    public int getItemCount() {
      return articles == null ? 0 : articles.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {

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

        public void click(final ArticleEntity article, final OnItemClickListener listener) {
            itemView.setOnClickListener(view -> listener.onClick(article));
        }
    }

    public interface OnItemClickListener {
        void onClick(ArticleEntity item);
    }
}