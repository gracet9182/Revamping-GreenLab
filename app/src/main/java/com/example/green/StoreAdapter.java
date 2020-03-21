package com.example.green;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.StoreViewHolder> {
    private ArrayList<Store> dataSet;

    public static class StoreViewHolder extends RecyclerView.ViewHolder {

        TextView storeName;
        TextView storeLocation;
        TextView storeContact;
        TextView storeCategory;
        ImageView imageViewIcon;

        public StoreViewHolder(View itemView) {
            super(itemView);
            this.storeName = (TextView) itemView.findViewById(R.id.storeName);
            this.storeLocation = (TextView) itemView.findViewById(R.id.storeLocation);
            this.storeContact = (TextView) itemView.findViewById(R.id.storeContact);
            this.storeCategory = (TextView) itemView.findViewById(R.id.category);
            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
    public StoreAdapter(ArrayList<Store> data) {
        this.dataSet = data;
    }

    @Override
    public StoreViewHolder onCreateViewHolder(ViewGroup parent,
                                              int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.store_card, parent, false);
        StoreViewHolder myViewHolder = new StoreViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final StoreViewHolder holder, final int listPosition) {

        TextView textViewName = holder.storeName;
        TextView textViewLocation = holder.storeLocation;
        TextView textViewContact = holder.storeContact;
        TextView textViewCategory = holder.storeCategory;
        ImageView imageView = holder.imageViewIcon;

        textViewName.setText(dataSet.get(listPosition).getName());
        textViewLocation.setText(dataSet.get(listPosition).getLocation());
        textViewContact.setText(dataSet.get(listPosition).getContacts());
        textViewCategory.setText(dataSet.get(listPosition).getCategory());
        imageView.setImageResource(dataSet.get(listPosition).getImage());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
