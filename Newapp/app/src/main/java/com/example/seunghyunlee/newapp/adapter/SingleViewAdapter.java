package com.example.seunghyunlee.newapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.seunghyunlee.newapp.objects.MenulistObject;
import com.example.seunghyunlee.newapp.R;
import com.example.seunghyunlee.newapp.SingleViewHolder;

import java.util.List;

/**
 * Created by seunghyunlee on 8/1/17.
 */

public class SingleViewAdapter  extends RecyclerView.Adapter<SingleViewHolder>{

    private static final String TAG = SingleViewAdapter.class.getSimpleName();

    private Context context;
    private List<MenulistObject> menulists;

    public SingleViewAdapter(Context context, List<MenulistObject> menulists){
        this.context = context;
        this.menulists = menulists;
    }


    @Override
    public SingleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.menu_layout,parent,false);

        return new SingleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SingleViewHolder holder, int position) {

        MenulistObject menulistObject = menulists.get(position);
        holder.singleViewName.setText(menulistObject.getMenuName());
        holder.singleViewRecipe.setText(menulistObject.getMenuRecipe());

    }

    @Override
    public int getItemCount() {
        return menulists.size();
    }
}
