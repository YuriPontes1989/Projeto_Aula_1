package com.example.projeto_aula_1;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

public class GPSTracker implements LocationListener {

    Context context;
    //
    public GPSTracker(Context c){
        context = c;
    }

    //
    public Location getLocation(){
        //
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)!=
        PackageManager.PERMISSION_GRANTED) {
            //
            Toast.makeText(context, "Não foi permitir", Toast.LENGTH_SHORT).show();
            return null;
        }
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean isGPSEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);

        //
        if (isGPSEnabled){
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000,10,this);
            Location l = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            return l;
        } else {
            //
            Toast.makeText(context, "Por Favor, Habilitar o GPS!",Toast.LENGTH_LONG).show();
        }
        //
        return null;
    }
    //
    @Override
    public void onProviderDisabled (@NonNull String provider){ }
    //
    @Override
    public void onLocationChanged(@NonNull Location location){ }
    //
    public void onStatusChanged (String provider, int status, Bundle extras){ }
    

}
