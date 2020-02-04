package com.example.simbirsoftapp.ui.news;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simbirsoftapp.R;
import com.example.simbirsoftapp.data.DataSource;
import com.example.simbirsoftapp.data.model.Event;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<Event> events;
    private Context context;
    private NewsClickHolder clickHolder;

    NewsAdapter(Context context,NewsClickHolder clickHolder) {
        events = new ArrayList<>();
        this.context = context;
        this.clickHolder = clickHolder;
    }

    NewsAdapter(List<Event> events, Context context,NewsClickHolder clickHolder) {
        this.events = events;
        this.context = context;
        this.clickHolder = clickHolder;
    }

    interface NewsClickHolder {
        void onNewsClick(int position);
    }

    public void addEvent(Event event) {
        this.events.add(event);
        notifyDataSetChanged();
    }

    public Event getEvent(int position){
        return events.get(position);
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView view = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_item,parent,false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        Event currentEvent = events.get(position);
        String eventPhoto = currentEvent.getEventPhoto().isEmpty() ?
                 DataSource.STANDARD_EVENT_IMAGE : currentEvent.getEventPhoto().get(0);
        Drawable image = context.getResources().getDrawable(context.getResources()
                .getIdentifier(eventPhoto,
                        "drawable",
                        context.getPackageName()));
        holder.cardImage.setImageDrawable(image);
        holder.cardDate.setText(currentEvent.getEventDate());
        holder.cardInfo.setText(currentEvent.getEventDescription());
        holder.cardLabel.setText(currentEvent.getEventName());
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView cardLabel;
        TextView cardInfo;
        ImageView cardImage;
        TextView cardDate;

        NewsViewHolder (CardView cardView) {
            super(cardView);
            cardLabel = cardView.findViewById(R.id.cardLabel);
            cardInfo = cardView.findViewById(R.id.cardInfo);
            cardDate = cardView.findViewById(R.id.cardDate);
            cardImage = cardView.findViewById(R.id.cardImage);
            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickHolder.onNewsClick(getAdapterPosition());
        }
    }
}
