package com.example.nitro.tasteme;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Random;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        // Adding all markers from database here

        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                View view = getLayoutInflater().inflate(R.layout.info_window, null);
                ImageView image = (ImageView) view.findViewById(R.id.badge);
                TextView title = (TextView) view.findViewById(R.id.title);
                TextView snippet = (TextView) view.findViewById(R.id.snippet);

                Random rd = new Random();
                int n = rd.nextInt(9);

                switch (n) {
                    case 0:
                        image.setImageResource(R.drawable.sample_0);
                        break;
                    case 1:
                        image.setImageResource(R.drawable.sample_1);
                        break;
                    case 2:
                        image.setImageResource(R.drawable.sample_2);
                        break;
                    case 3:
                        image.setImageResource(R.drawable.sample_3);
                        break;
                    case 4:
                        image.setImageResource(R.drawable.sample_4);
                        break;
                    case 5:
                        image.setImageResource(R.drawable.sample_5);
                        break;
                    case 6:
                        image.setImageResource(R.drawable.sample_6);
                        break;
                    case 7:
                        image.setImageResource(R.drawable.sample_7);
                        break;
                    case 8:
                        image.setImageResource(R.drawable.sample_8);
                        break;
                }

                LatLng posotion = marker.getPosition();
                title.setText(marker.getTitle());
                snippet.setText(marker.getSnippet());

                return view;
            }
        });

        // Add a marker in Sydney and move the camera
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(4.210484, 101.975766))
                .title("Curry Mee")
                .snippet("Malaysia")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.074208, 21.824312))
                .title("Pastitsio")
                .snippet("Greece")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-16.290154, -63.588653))
                .title("Chairo Paceño")
                .snippet("Bolivia")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));


        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    private Bitmap resizeMapIcons(String iconName,int width, int height){
        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(), getResources().getIdentifier(iconName, "drawable", getPackageName()));
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, width, height, false);
        return resizedBitmap;
    }
}
