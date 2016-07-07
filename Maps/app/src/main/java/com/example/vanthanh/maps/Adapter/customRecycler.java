package com.example.vanthanh.maps.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vanthanh.maps.GetDataFirebase.Data;
import com.example.vanthanh.maps.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Van Thanh on 7/4/2016.
 */
public class customRecycler extends RecyclerView.Adapter<RecyclerViewHolder> {
    private List<Data> listData = new ArrayList<Data>();

    public customRecycler(List<Data> listData) {
        this.listData = listData;
    }

    public void updateList(List<Data> data) {
        listData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View v=inflater.inflate(R.layout.item,null,false);
        return new RecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.bind(listData.get(position));
    }


}
