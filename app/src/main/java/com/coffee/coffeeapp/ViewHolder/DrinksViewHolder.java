package com.coffee.coffeeapp.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.coffee.coffeeapp.Interface.ItemClickListener;
import com.coffee.coffeeapp.R;

public class DrinksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView drinks_name;
    public ImageView drinks_image;

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public DrinksViewHolder(@NonNull View itemView) {
        super(itemView);

        drinks_name = (TextView)itemView.findViewById(R.id.drinks_name);
        drinks_image = (ImageView) itemView.findViewById(R.id.drinks_image);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }
}
