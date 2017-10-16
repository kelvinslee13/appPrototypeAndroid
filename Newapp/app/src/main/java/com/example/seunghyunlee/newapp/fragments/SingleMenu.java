package com.example.seunghyunlee.newapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.seunghyunlee.newapp.objects.MenulistObject;
import com.example.seunghyunlee.newapp.R;
import com.example.seunghyunlee.newapp.adapter.SingleViewAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SingleMenu extends Fragment {


    public SingleMenu() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_single_menu,container,false);
        RecyclerView menuRecyclerView = (RecyclerView) view.findViewById(R.id.your_menu_list);
        GridLayoutManager gridLayout = new GridLayoutManager(getActivity(),2);
        menuRecyclerView.setLayoutManager(gridLayout);
        menuRecyclerView.setHasFixedSize(true);

        SingleViewAdapter mAdapter = new SingleViewAdapter(getActivity(), getTestData());
        menuRecyclerView.setAdapter(mAdapter);


        return view;
    }

    public List<MenulistObject> getTestData(){
        List<MenulistObject> menulist = new ArrayList<MenulistObject>();
        menulist.add(new MenulistObject("Salmon","onion, salmon",""));
        menulist.add(new MenulistObject("Salmon","onion, salmon",""));
        menulist.add(new MenulistObject("Salmon","onion, salmon",""));
        menulist.add(new MenulistObject("Salmon","onion, salmon",""));
        menulist.add(new MenulistObject("Salmon","onion, salmon",""));
        menulist.add(new MenulistObject("Salmon","onion, salmon",""));
        menulist.add(new MenulistObject("Salmon","onion, salmon",""));
        menulist.add(new MenulistObject("Salmon","onion, salmon",""));

        return menulist;
    }

}
