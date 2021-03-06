package com.example.nitro.tasteme.maps;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nitro.tasteme.MainActivity;
import com.example.nitro.tasteme.R;
import com.example.nitro.tasteme.fragments.HomeRecipeFragment;
import com.example.nitro.tasteme.fragments.RecipeFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Random;

public class MapsActivity extends FragmentActivity implements
        GoogleMap.OnInfoWindowClickListener,
        OnMapReadyCallback {

    //------------
    //ZA MAPS TO FRAGMENT:
    OnInfoWindowCustomClickListener mapsCallback;

    // The container Activity must implement this interface so the frag can deliver messages
    public interface OnInfoWindowCustomClickListener {
        /** Called by HeadlinesFragment when a list item is selected */
        public void onInfoWindowCustomClick();
    }

    //---------------

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
        mMap.getUiSettings().setZoomControlsEnabled(true);

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
        mMap.setOnInfoWindowClickListener(this);

        // Override Info Window - need to moved to UserInfoWindowsAdapter class
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

                switch (marker.getTitle()) {
                    case "Curry Mee":
                        image.setImageResource(R.drawable.sample_0);
                        break;
                    case "Pastitsio":
                        image.setImageResource(R.drawable.sample_1);
                        break;
                    case "Chairo Paceño":
                        image.setImageResource(R.drawable.sample_2);
                        break;
                    case "Chocolate Chip Muffins":
                        image.setImageResource(R.drawable.sample_4);
                        break;
                    case "Rise with Anshoa":
                        image.setImageResource(R.drawable.sample_3);
                        break;
                    case "Green salad":
                        image.setImageResource(R.drawable.sample_5);
                        break;
                    case "Can chua":
                        image.setImageResource(R.drawable.sample_6);
                        break;
                    case "Big Mac Homemade":
                        image.setImageResource(R.drawable.sample_7);
                        break;
                    case "Sushi Moa":
                        image.setImageResource(R.drawable.sample_8);
                        break;
                }

                title.setText(marker.getTitle());
                snippet.setText(marker.getSnippet());

                return view;
            }
        });

        // Adding all markers from database here
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(4.210484, 101.975766))
                .title("Curry Mee")
                .snippet("Malaysia")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.074208, 21.824312))
                .title("Pastitsio")
                .snippet("Greece")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-16.290154, -63.588653))
                .title("Chairo Paceño")
                .snippet("Bolivia")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(41.156944, -82.106323))
                .title("Chocolate Chip Muffins")
                .snippet("Chicago")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(35.689487, 139.691706))
                .title("Rise with Anshoa")
                .snippet("Japon")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(15.870032, 100.992541))
                .title("Green salad")
                .snippet("Tailand")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(48.856614, 2.352222))
                .title("Can chua")
                .snippet("Paris")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(40.712784, -74.005941))
                .title("Big Mac Homemade")
                .snippet("New York")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.904211, 116.407395))
                .title("Sushi Moa")
                .snippet("Benjing")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));


        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    private Bitmap resizeMapIcons(String iconName,int width, int height){
        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(), getResources().getIdentifier(iconName, "drawable", getPackageName()));
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, width, height, false);
        return resizedBitmap;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        HomeRecipeFragment fr = new HomeRecipeFragment ();
        getSupportFragmentManager().beginTransaction().replace(R.id.map, fr).commit();
    }
}
