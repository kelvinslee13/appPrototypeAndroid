package com.example.seunghyunlee.newapp;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by seunghyunlee on 8/1/17.
 */

public class SingleViewHolder extends RecyclerView.ViewHolder {
    public TextView singleViewName;
    public TextView singleViewRecipe;
    public ImageView singleViewCover;

    public SingleViewHolder(View itemView) {
        super(itemView);
        singleViewName = (TextView) itemView.findViewById(R.id.menu_name);
        singleViewRecipe = (TextView) itemView.findViewById(R.id.menu_recipe);
        singleViewCover = (ImageView) itemView.findViewById(R.id.menu_cover);
    }
}
