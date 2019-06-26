package com.example.week4daily2.model.datasource;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.week4daily2.MainActivity;
import com.example.week4daily2.R;
import com.example.week4daily2.model.datasource.flickr.FlickrResponse;
import com.example.week4daily2.model.datasource.flickr.Item;


import java.util.List;

public class FlickerRVAdapter extends RecyclerView.Adapter<FlickerRVAdapter.ViewHolder> {
    List<Item> listOfQuery;

    public FlickerRVAdapter(List<Item> items) {
        this.listOfQuery = items;
    }


    @NonNull
    @Override
    public FlickerRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.flicker_item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Item itemResult = listOfQuery.get(position);
        final String title = itemResult.getTitle();
        final String description = itemResult.getAuthor();
        final String imageLink = itemResult.getMedia().getM();

        holder.tvTitle.setText(title);
        holder.tvDescription.setText(description);
        Glide.with(holder.imgFlickrPic).load(imageLink).into(holder.imgFlickrPic);

    }

    @Override
    public int getItemCount() {
        return listOfQuery.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgFlickrPic;
        TextView tvDescription;
        TextView tvTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFlickrPic = itemView.findViewById(R.id.imgThumb);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);

        }


    }
}
