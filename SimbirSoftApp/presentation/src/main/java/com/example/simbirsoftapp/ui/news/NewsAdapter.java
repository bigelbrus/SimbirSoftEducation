package com.example.simbirsoftapp.ui.news;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simbirsoftapp.R;
import com.example.simbirsoftapp.data.DataForProfile;
import com.example.simbirsoftapp.data.model.EventModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<EventModel> events;
    private Context context;
    private NewsClickHolder clickHolder;

    @Inject
    NewsAdapter(Context context) {
        this.events = new ArrayList<>();
        this.context = context;
    }

    interface NewsClickHolder {
        void onNewsClick(int position);
    }

    public void addEvent(EventModel event) {
        this.events.add(event);
        notifyDataSetChanged();
    }

    public EventModel getEvent(int position) {
        return events.get(position);
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView view = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_item, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        EventModel currentEvent = events.get(position);
        String eventPhoto = currentEvent.getEventPhoto().isEmpty() ?
                DataForProfile.STANDARD_EVENT_IMAGE : currentEvent.getEventPhoto().get(0);
        Drawable image = context.getResources().getDrawable(context.getResources()
                .getIdentifier(eventPhoto,
                        "drawable",
                        context.getPackageName()));
        holder.itemView.setOnClickListener((v) -> clickHolder.onNewsClick(position));
        holder.cardImage.setImageDrawable(image);
        holder.cardDate.setText(currentEvent.getEventDate());
        holder.cardInfo.setText(currentEvent.getEventDescription());
        holder.cardLabel.setText(currentEvent.getEventName());
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView cardLabel;
        TextView cardInfo;
        ImageView cardImage;
        TextView cardDate;
        View itemView;

        NewsViewHolder(CardView cardView) {
            super(cardView);
            cardLabel = cardView.findViewById(R.id.cardLabel);
            cardInfo = cardView.findViewById(R.id.cardInfo);
            cardDate = cardView.findViewById(R.id.cardDate);
            cardImage = cardView.findViewById(R.id.cardImage);
            itemView = cardView;
        }

    }

    public void setUpClickHolder(NewsClickHolder newsClickHolder) {
        this.clickHolder = newsClickHolder;
    }
}
