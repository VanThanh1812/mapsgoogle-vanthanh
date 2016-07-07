package com.example.vanthanh.maps.Adapter;

import android.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.vanthanh.maps.GetDataFirebase.Data;
import com.example.vanthanh.maps.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Van Thanh on 7/4/2016.
 */
public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    ImageView imgView;
    //ImageView imageView;
    public RecyclerViewHolder(final View itemView) {
        super(itemView);
        imgView=(ImageView)itemView.findViewById(R.id.imageView);
        //txtView=(TextView)itemView.findViewById(R.id.textView);
//        txtView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(itemView.getContext(), txtView.getText().toString(), Toast.LENGTH_SHORT).show();
//            }
//        });

    }
    /**     * Được gọi trong hàm onBindViewHolder của adapter.Dùng để set-up item view dựa theo data item đưa ra     *     * @param item     */
    void bind(final Data data) {
        Picasso.with(imgView.getContext()).load(data.getLink().toString()).placeholder(R.mipmap.ic_launcher).resize(50, 50).centerCrop().into(imgView);
        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: doing somethings
                LayoutInflater inflater = LayoutInflater.from(imgView.getContext());
                View v = inflater.inflate(R.layout.viewimage, null);
                AlertDialog.Builder aBuilder = new AlertDialog.Builder(imgView.getContext());
                ImageView imageView = (ImageView) v.findViewById(R.id.imageView2);
                Picasso.with(imageView.getContext()).load(data.getLink().toString()).placeholder(R.mipmap.ic_launcher).into(imageView);
                aBuilder.setView(v);
//                aBuilder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                    }
//                });
                aBuilder.show();
            }
        });
    }

    @Override
    public void onClick(View view) {

    }
}
