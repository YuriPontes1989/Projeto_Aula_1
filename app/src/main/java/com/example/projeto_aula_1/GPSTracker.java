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
    // Criando o metodo construtor da classe GPStracker
    public GPSTracker(Context c){
        context = c;
    }

    // criando o  metodo get location que exige o retorno de um valor do tipo location
    public Location getLocation(){
        //  vendo se o usario tem permissão para acessar a localização do dispotivo
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)!=
        PackageManager.PERMISSION_GRANTED) {
            // se a permissão não foi concedida exibiremos está mensagem n foi recebida e retornaremos a um valor nulo apos isso utlizamos o serviço de localizacão na variavel lm e verificamos se o gps esta ativo ( boolean is gps enable)
            Toast.makeText(context, "Não foi permitir", Toast.LENGTH_SHORT).show();
            return null;
        }
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean isGPSEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);

        // caso o gps esteja ativado mandamos uma solicitação de atualização de localização do dispositivo e amarzenamos sua ultima localização conhecida a retornaremos a localização logo apos
        if (isGPSEnabled){
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000,10,this);
            Location l = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            return l;
        } else {
            // caso  contrario printar na tela "Por favor habilite o gps'
            Toast.makeText(context, "Por Favor, Habilitar o GPS!",Toast.LENGTH_LONG).show();
        }
        // caso nenhum dos parametros n seja comprido  retornaremos nullo
        return null;
    }
    // sobrescrevendo o metado abaixo que registra nosso provedor(locationListener) ficar desativado.Esta é sobrescrição obrigatoria pois o location listener é uma interface
    @Override
    public void onProviderDisabled (@NonNull String provider){ }
    //sobrescrevendo o metado abaixo que é chamado quando a localizção  muda.Esta é sobrescrição obrigatoria pois o location listener é uma interface
    @Override
    public void onLocationChanged(@NonNull Location location){ }
    //sobrescrevendo o metado abaixo é chamado quando  ocorre mudança de status.Esta é sobrescrição obrigatoria pois o location listener é uma interface
    public void onStatusChanged (String provider, int status, Bundle extras){ }
    

}
