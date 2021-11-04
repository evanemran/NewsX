package com.example.newsx;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CustomViewHolder extends RecyclerView.ViewHolder {

    public TextView text_title, text_author;
    public CardView cardView;
    public ImageView img_headline;

    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);
        text_author = itemView.findViewById(R.id.text_author);
        img_headline = itemView.findViewById(R.id.img_headline);
        text_title = itemView.findViewById(R.id.text_title);
        cardView = itemView.findViewById(R.id.main_container);
    }
}
