package com.example.vanthanh.maps.Adapter;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.example.vanthanh.maps.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by Van Thanh on 7/6/2016.
 */
public class MarkerWindowAdapter implements GoogleMap.InfoWindowAdapter {
    private Activity context;

    public MarkerWindowAdapter(Activity context) {
        this.context = context;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        View v=this.context.getLayoutInflater().inflate(R.layout.custommarker, null);
        LatLng latLng=marker.getPosition();
        String snippet=marker.getSnippet();

        TextView txtTitle=(TextView)v.findViewById(R.id.txtTitle);
        TextView txtLat=(TextView)v.findViewById(R.id.txtLat);
        TextView txtLog=(TextView)v.findViewById(R.id.txtLog);
        TextView txtSnippet=(TextView)v.findViewById(R.id.txtSnippet);
        TextView txtArc=(TextView)v.findViewById(R.id.txtAcreage);

//        String[] convert=snippet.split(".");
        txtTitle.setText(marker.getTitle());
//        if ((convert[0] !=null)&&(convert[1])!=null){
//            txtSnippet.setText(convert[1]);
//            txtArc.setText(convert[0]);
//        }
        txtLat.setText("Vĩ độ: "+String.valueOf(latLng.latitude));
        txtLog.setText("Kinh độ: "+String.valueOf(latLng.longitude));

        return v;
    }
}
