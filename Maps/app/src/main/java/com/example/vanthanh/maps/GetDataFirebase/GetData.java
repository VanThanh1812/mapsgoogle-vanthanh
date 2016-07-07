package com.example.vanthanh.maps.GetDataFirebase;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.vanthanh.maps.Activity.MapsActivity;
import com.example.vanthanh.maps.Adapter.MarkerWindowAdapter;
import com.example.vanthanh.maps.Adapter.customRecycler;
import com.example.vanthanh.maps.R;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Van Thanh on 7/1/2016.
 */
public class GetData {
    public static long countmarker=0;
    public static final String ROOTFIREBASE="https://quandaohoangsa.firebaseio.com/";

    public static void getMarker(String root, final GoogleMap mMap, final Activity context){

        Firebase childFirebase=new Firebase(root);
        childFirebase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(final DataSnapshot dataSnapshot, String s) {
                Locations newLocations=new Locations();
                //Log.i("abc", dataSnapshot.getValue().toString());
                final Map<String,Object> map=dataSnapshot.getValue(Map.class);
                //Log.i("vt100", dataSnapshot.getKey().toString());
                countmarker=dataSnapshot.getChildrenCount();
                newLocations.setLat(Double.parseDouble(map.get("lat").toString()));
                newLocations.setLog(Double.parseDouble(map.get("log").toString()));
                newLocations.setName(map.get("name").toString());
                newLocations.setSnippet(map.get("snippet").toString());
                mMap.addMarker(new MarkerOptions().position(new LatLng(newLocations.getLog(), newLocations.getLat())).snippet(newLocations.getSnippet()).title(dataSnapshot.getKey().toString() + "." + newLocations.getName()));
                mMap.setInfoWindowAdapter(new MarkerWindowAdapter(context));
                mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {

                        marker.showInfoWindow();

                        //lấy vị trí dấu chấm xuất hiện trong chuỗi Title
                        final ArrayList<Data> data = new ArrayList<>();
                        int position = marker.getTitle().indexOf(".");
                        if (position == -1) return true;
                        String idMarker = marker.getTitle().substring(0, position);
                        String linkanh = ROOTFIREBASE + idMarker + "/listanh";
                        Firebase root = new Firebase(linkanh);
                        root.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                //Log.i("abc", dataSnapshot.toString());

                                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                                    Data post = postSnapshot.getValue(Data.class);
                                    data.add(post);
                                    MapsActivity.recyclerView.setAdapter(new customRecycler(data));
                                }

//                for(int i=1;i<=7;i++) {
//                    data.add(new Data(map.get(i).toString()));
//                    recyclerView.setAdapter(new customRecycler(data));
//                }
                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {

                            }
                        });

                        //Log.i("link", linkanh);
                        return true;
                    }
                });
                mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {

                        GetListPosition.CreatePolyline("quandaohoangsa", MapsActivity.mMap);
                        GetListPosition.CreatePolyline("quandaotruongsa", MapsActivity.mMap);
                    }
                });
                mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick(Marker marker) {
                        String url = "https://www.google.com.vn/?gfe_rd=cr&ei=k-B9V-O0AarY8gf3q6yQDw#q="+marker.getTitle().toString();
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        context.startActivity(i);
                    }
                });
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }
    public static void PopupAddLocation(LatLng latLng, Activity context){
        LayoutInflater inflater=LayoutInflater.from(context);
        View v=inflater.inflate(R.layout.addlocation, null);

        final TextView txtLat= (TextView) v.findViewById(R.id.txtLat);
        final TextView txtLog=(TextView)v.findViewById(R.id.txtLog);
        final EditText edtTitle=(EditText)v.findViewById(R.id.edtTitle);
        final EditText edtArc=(EditText)v.findViewById(R.id.edtArc);
        final EditText edtSnip=(EditText)v.findViewById(R.id.edtSnippet);

        txtLat.setText(String.valueOf(latLng.latitude));
        txtLog.setText(String.valueOf(latLng.longitude));

        AlertDialog.Builder aBuilder=new AlertDialog.Builder(context);
        aBuilder.setTitle("Thêm địa danh");
        aBuilder.setView(v);
        aBuilder.setPositiveButton("Thêm địa danh", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                final Data data=new Data();
                data.setLat(txtLog.getText().toString());
                data.setLog(txtLat.getText().toString());
                data.setTitle(edtTitle.getText().toString());
                data.setArc(edtArc.getText().toString());
                data.setSnippet(edtSnip.getText().toString());

                final Firebase root = new Firebase(ROOTFIREBASE);
                root.child(String.valueOf(countmarker)+1).setValue(data);
            }
        });
        aBuilder.show();

    }

}
