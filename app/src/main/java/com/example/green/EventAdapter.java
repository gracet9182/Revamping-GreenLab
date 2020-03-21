package com.example.green;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
    private ArrayList<Event> dataSet;

    public static class EventViewHolder extends RecyclerView.ViewHolder {

        TextView eventName;
        TextView eventLocation;
        TextView eventDate;
        TextView eventTime;
        ImageView imageViewIcon;

        public EventViewHolder(View itemView) {
            super(itemView);
            this.eventName = (TextView) itemView.findViewById(R.id.eventName);
            this.eventLocation = (TextView) itemView.findViewById(R.id.eventLocation);
            this.eventDate = (TextView) itemView.findViewById(R.id.eventDate);
            this.eventTime = (TextView)itemView.findViewById(R.id.eventTime);
            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
    public EventAdapter(ArrayList<Event> data) {
        this.dataSet = data;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent,
                                              int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_card, parent, false);
        EventViewHolder myViewHolder = new EventViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final EventViewHolder holder, final int listPosition) {

        TextView textViewName = holder.eventName;
        TextView textViewLocation = holder.eventLocation;
        TextView textViewDate = holder.eventDate;
        TextView textViewTime = holder.eventTime;
        ImageView imageView = holder.imageViewIcon;

        textViewName.setText(dataSet.get(listPosition).getName());
        textViewLocation.setText(dataSet.get(listPosition).getLocation());
        textViewDate.setText(dataSet.get(listPosition).getDate());
        textViewTime.setText(dataSet.get(listPosition).getTime());
        imageView.setImageResource(R.drawable.event);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
