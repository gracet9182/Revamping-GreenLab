package com.example.green;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Store {
    private String name;
    private String location;
    private String category;
    private String contacts;
    private double lati;
    private double longti;
    private Context context;

    public Store(String name, String location, String category, String contacts) {
        this.name = name;
        this.location = location;
        this.category = category;
        this.contacts = contacts;

    }
    public Store(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public int getImage() {
        return R.drawable.secondhand;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setUpAddress(){
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        String result = null;
        try{
            List<Address> addressList = geocoder.getFromLocationName(location, 1);
            if (addressList != null && addressList.size() > 0) {
                Address address = addressList.get(0);
                lati = address.getLatitude();
                longti = address.getLongitude();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double getLatitude(Context context) {
        setContext(context);
        setUpAddress();
        return lati;
    }

    public double getLongitude(Context context) {
        setContext(context);
        setUpAddress();
        return longti;
    }

}

