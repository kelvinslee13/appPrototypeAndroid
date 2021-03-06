package com.example.seunghyunlee.newapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.seunghyunlee.newapp.adapter.SingleViewAdapter;
import com.example.seunghyunlee.newapp.objects.MenulistObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment {


    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart,container,false);
        RecyclerView menuRecyclerView = (RecyclerView) view.findViewById(R.id.cart_list);
        GridLayoutManager gridLayout = new GridLayoutManager(getActivity(),1);
        menuRecyclerView.setLayoutManager(gridLayout);
        menuRecyclerView.setHasFixedSize(true);

        SingleViewAdapter mAdapter = new SingleViewAdapter(getActivity(), getTestData());
        menuRecyclerView.setAdapter(mAdapter);
        return view;
    }

    public List<MenulistObject> getTestData(){
        List<MenulistObject> menulist = new ArrayList<MenulistObject>();
        menulist.add(new MenulistObject("Salmon","onion1, salmon",""));
        menulist.add(new MenulistObject("Salmon","onion1, salmon",""));
        menulist.add(new MenulistObject("Salmon","onion1, salmon",""));
        menulist.add(new MenulistObject("Salmon","onion1, salmon",""));
        menulist.add(new MenulistObject("Salmon","onion1, salmon",""));


        return menulist;
    }

}
