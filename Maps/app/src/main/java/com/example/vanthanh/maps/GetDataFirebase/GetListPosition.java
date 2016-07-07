package com.example.vanthanh.maps.GetDataFirebase;

import android.graphics.Color;
import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import java.util.Map;

/**
 * Created by Van Thanh on 7/6/2016.
 */
public class GetListPosition {
    public static final String TAG="iiiiiii";
    public static final String POLYLINE="https://khoanhvungdiadiem.firebaseio.com/";

    public static void CreatePolyline(final String location, final GoogleMap mMap){

        Firebase list=new Firebase(POLYLINE+location);
        list.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final PolygonOptions options= new PolygonOptions();
//                Locations locations=new Locations();
//                Log.i(TAG, dataSnapshot.getValue().toString());
//                final Map<String,Object> map=dataSnapshot.getValue(Map.class);
//
//                locations.setLat(Double.parseDouble(map.get("lat").toString()));
//                locations.setLog(Double.parseDouble(map.get("log").toString()));
//
//                Log.i(TAG,String.valueOf(locations.getLat()));

                Log.i(TAG,String.valueOf(dataSnapshot.getChildrenCount()));
                final int count= (int) dataSnapshot.getChildrenCount();
                for (int i=1;i<=count;i++) {
                    String linkchild = POLYLINE + location + "/" + String.valueOf(i);
                    Firebase child = new Firebase(linkchild);
                    child.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Log.i(TAG, dataSnapshot.getValue().toString());
                            final Map<String, Object> map = dataSnapshot.getValue(Map.class);

                            LatLng newLatLng = new LatLng(Double.parseDouble(map.get("log").toString()), Double.parseDouble(map.get("lat").toString()));

                            options.add(newLatLng);
                            Polygon polygon=null;

                            polygon =mMap.addPolygon(options);
                            polygon.setStrokeColor(Color.RED);
                            polygon.setStrokeWidth(1);
                            if(options.getPoints().size()<count){
                                polygon.remove();
                            }
                        }

                        @Override
                        public void onCancelled(FirebaseError firebaseError) {

                        }
                    });
                }


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });




    }
}
