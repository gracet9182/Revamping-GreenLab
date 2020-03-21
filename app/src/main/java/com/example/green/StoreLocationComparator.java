package com.example.green;

import android.content.Context;
import android.location.Location;

import java.util.Comparator;

public class StoreLocationComparator  implements Comparator<Store> {
    Location currentLoc;
    Context context;

    public StoreLocationComparator(Location current, Context context){
        currentLoc = current;
        this.context = context;
    }
    @Override
    public int compare(Store place1, Store place2) {
        double lat1 = place1.getLatitude(context);
        double lon1 = place1.getLongitude(context);
        double lat2 = place2.getLatitude(context);
        double lon2 = place2.getLongitude(context);

        double distanceToPlace1 = distance(currentLoc.getLatitude(), currentLoc.getLongitude(), lat1, lon1);
        double distanceToPlace2 = distance(currentLoc.getLatitude(), currentLoc.getLongitude(), lat2, lon2);
        return (int) (distanceToPlace1 - distanceToPlace2);
    }

    public double distance(double fromLat, double fromLon, double toLat, double toLon) {
        double radius = 6378137;   // approximate Earth radius, *in meters*
        double deltaLat = toLat - fromLat;
        double deltaLon = toLon - fromLon;
        double angle = 2 * Math.asin( Math.sqrt(
                Math.pow(Math.sin(deltaLat/2), 2) +
                        Math.cos(fromLat) * Math.cos(toLat) *
                                Math.pow(Math.sin(deltaLon/2), 2) ) );
        return radius * angle;
    }
}
