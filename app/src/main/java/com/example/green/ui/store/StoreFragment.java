package com.example.green.ui.store;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.green.R;
import com.example.green.Store;
import com.example.green.StoreAdapter;

import java.util.ArrayList;


public class StoreFragment extends Fragment implements LocationListener {

    private StoreVIewModel notificationsViewModel;
    protected LocationManager locationManager;
    protected LocationListener locationListener;
    private double longtitude;
    private double latitude;
    private int PERMISSION_ID = 44;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(StoreVIewModel.class);
        View root = inflater.inflate(R.layout.fragment_store, container, false);
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_ID);
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();

        } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            getLocation();
        }

        RecyclerView recyclerView = root.findViewById(R.id.stores);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        // get sorted list of stores
        ArrayList<Store> stores = new ArrayList<>();
        Store store = new Store("Sunny", "95 Queens Park, Toronto, Ontario", "Clothes", "439-882-3000");
        Store store1 = new Store("Wendy's", "5 King College Circle, Toronto, Ontario", "Books", "439-332-2800");
        Store store2 = new Store("Green Organic", "95 Queens Park, Toronto, Ontario", "Organic Food", "439-882-3000");
        Store store3 = new Store("Dear Old Friend", "58 Charles Street, Toronto, Ontario", "Toys", "338-892-2901");
        Store store4 = new Store("Lucia's", "50 Carlton Street, Toronto, Ontario", "Clothes", "437-852-3300");
        stores.add(store1);
        stores.add(store2);
        stores.add(store3);
        stores.add(store4);
        StoreAdapter storeAdapter = new StoreAdapter(stores);
        recyclerView.setAdapter(storeAdapter);
        return root;
    }

    private void getLocation(){
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
            (getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_ID);

    } else {
        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        Location location1 = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        Location location2 = locationManager.getLastKnownLocation(LocationManager. PASSIVE_PROVIDER);

        if (location != null) {
            latitude = location.getLatitude();
            longtitude = location.getLongitude();
            System.out.println("CurrentLocation: ("+latitude+","+longtitude+")");

        } else  if (location1 != null) {
            latitude = location1.getLatitude();
            longtitude = location1.getLongitude();
            System.out.println("CurrentLocation: ("+latitude+","+longtitude+")");

        } else  if (location2 != null) {
            latitude = location2.getLatitude();
            longtitude = location2.getLongitude();
            System.out.println("CurrentLocation: ("+latitude+","+longtitude+")");
        }else{
            Toast.makeText(getActivity(),"Unble to Trace your location",Toast.LENGTH_SHORT).show();

        }
    }
}



    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Please Turn ON your GPS Connection")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onLocationChanged(Location location) {
        longtitude = location.getLongitude();
        latitude = location.getLatitude();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude","status");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Latitude","enable");
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Latitude","disable");

    }


}