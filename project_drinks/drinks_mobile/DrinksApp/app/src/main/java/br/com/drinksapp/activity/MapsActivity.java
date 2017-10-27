package br.com.drinksapp.activity;

import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import br.com.drinksapp.R;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMinZoomPreference(13.0f);
        mMap.setMaxZoomPreference(21.0f);
        //mGoogleMap.clear();
//        LatLng decimosextobpm = new LatLng(-7.9371584,-34.8355436);
//        BitmapDescriptor icondsbpm = BitmapDescriptorFactory.fromResource(R.drawable.icon_app);
//        mMap.addMarker(new MarkerOptions().position(decimosextobpm).icon(icondsbpm).title("Carrefour").snippet("Rua Odorico Mendes, n 700"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(decimosextobpm));
//
//        LatLng decimoterceirobpm = new LatLng(-7.938098,-34.879259);
//        BitmapDescriptor icondecimoterceirobpm = BitmapDescriptorFactory.fromResource(R.drawable.icon_app);
//        mMap.addMarker(new MarkerOptions().position(decimoterceirobpm).icon(icondecimoterceirobpm).title("Hiper Bom Preço").snippet("Av do Forte, n 1250"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(decimoterceirobpm));
//
//
//        LatLng delegaciabv = new LatLng(-7.9576446,-34.8329773);
//        BitmapDescriptor icondelebv = BitmapDescriptorFactory.fromResource(R.drawable.icon_app);
//        mMap.addMarker(new MarkerOptions().position(delegaciabv).icon(icondelebv).title("Carrefour").snippet("Rua Odete Monteiro, n 300"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(delegaciabv));
//
//        LatLng delegaciaaurora = new LatLng(-7.9178675,-34.8382468);
//        BitmapDescriptor icondelegaciaaurora = BitmapDescriptorFactory.fromResource(R.drawable.icon_app);
//        mMap.addMarker(new MarkerOptions().position(delegaciaaurora).icon(icondelegaciaaurora).title("Hiper Bom Preço").snippet("Rua das Neves, n 10"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(delegaciaaurora));

        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(
                this, android.Manifest.permission.
                        ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        googleMap.setMyLocationEnabled(true);
        googleMap.setTrafficEnabled(true);
        googleMap.setIndoorEnabled(true);
        googleMap.setBuildingsEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
    }
}
