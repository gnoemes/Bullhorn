package com.gnoemes.bullhorn.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gnoemes.bullhorn.Models.Networking.Model.Article.Article;
import com.gnoemes.bullhorn.R;
import com.gnoemes.bullhorn.Utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.NewsViewHolder> {

    private List<Article> articles;
    private final OnItemClickListener listener;

    public RecyclerAdapter(OnItemClickListener listener) {
        this.listener = listener;
        articles = new ArrayList<>();
    }

    public void addAll(List<Article> articles) {
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
        try {
            holder.mTitle.setText(articles.get(position).getTitle());
            holder.mDescription.setText(articles.get(position).getDescription());
            holder.mAuthor.setText(articles.get(position).getAuthor());
            holder.mDate.setText(Utils.formatDate(articles.get(position)));
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        catch (NullPointerException nE) {
           if (articles.get(position).getAuthor() == null) {
               holder.mAuthor.setText(R.string.err_item_no_author);
            }
            if (articles.get(position).getDescription() == null) {
                holder.mDescription.setText(R.string.err_item_no_description);
            }
            if (articles.get(position).getTitle() == null) {
                holder.mTitle.setText(R.string.err_item_no_title);
            }
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

        public void click(final Article article, final OnItemClickListener listener) {
            itemView.setOnClickListener(view -> listener.onClick(article));
        }

    }

    public interface OnItemClickListener {
        void onClick(Article item);
    }
}