package com.example.vanthanh.maps.Activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.vanthanh.maps.GetDataFirebase.GetData;
import com.example.vanthanh.maps.R;
import com.firebase.client.Firebase;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    public static RecyclerView recyclerView;
    public static GoogleMap mMap;
    private final String[] MAPS_DISPLAY={"Bản đồ","Bản đồ vệ tinh",  "Bản đồ địa hình"};
    private final int[] MAPS_TYPES={GoogleMap.MAP_TYPE_NORMAL,GoogleMap.MAP_TYPE_SATELLITE,GoogleMap.MAP_TYPE_TERRAIN};

    Spinner spn;
    ProgressDialog myProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Firebase.setAndroidContext(this);


        recyclerView=(RecyclerView)findViewById(R.id.recycler);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getBaseContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        //Tạo Progress Bar
        myProgress = new ProgressDialog(this);
        myProgress.setTitle("Đang tải Map ...");
        myProgress.setMessage("Vui lòng chờ...");
        myProgress.setCancelable(true);
        //Hiển thị Progress Bar
        myProgress.show();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //

        //
        ArrayAdapter<String> adapterMap=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,MAPS_DISPLAY);
        spn= (Spinner) findViewById(R.id.spn);
        spn.setAdapter(adapterMap);
        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                mMap.setMapType(MAPS_TYPES[pos]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                myProgress.dismiss();
            }
        });
        // Add a marker in Hoàng Sa and move the camera
        LatLng hoangsaVN = new LatLng(16.494328847656796,112.03764416277409);
        LatLng truongsaVN=new LatLng(8.963698, 111.934682);
        mMap.addMarker(new MarkerOptions().position(hoangsaVN).title("Quần đảo Hoàng Sa Việt Nam").icon(BitmapDescriptorFactory.fromResource(R.drawable.vn)));
        mMap.addMarker(new MarkerOptions().position(truongsaVN).title("Quần đảo Trường Sa Việt Nam").icon(BitmapDescriptorFactory.fromResource(R.drawable.vn)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(hoangsaVN));
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                Log.i("vanthanh", "click");
                mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.location)).position(new LatLng(latLng.longitude, latLng.latitude)).title("Điểm chưa có sẵn"));
                GetData.PopupAddLocation(latLng, MapsActivity.this);
            }
        });
        GetData.getMarker(GetData.ROOTFIREBASE, mMap, this);
/*
        log=17.162282, lat=111.463799}
    07-06 14:14:29.614 28026-28026/com.example.vanthanh.maps I/iiiiiii: {log=16.983745, lat=112.265801}
    07-06 14:14:29.624 28026-28026/com.example.vanthanh.maps I/iiiiiii: {log=16.531396, lat=112.595390}*/



        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hoangsaVN, 9f));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        mMap.setMyLocationEnabled(true);
    }
}
