package com.example.projeto_aula_1;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Dlecarando as variaveis private
    private ImageView imageViewFoto;
    private Button btnGeo;

    // sobrescrevendo o metodo onCreate que é o primeiro a ser executado no main activity; utiliza "savedInstanceState"  para poder recuperá-lo depois, ja na terceira linha ele esta setando o layout

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    //Criando o botão de GeoLocalização e está pedindo permissão para acessar a localização do celular
        btnGeo = (Button) findViewById(R.id.btn_gps);
            ActivityCompat.requestPermissions(this, new String [] {Manifest.permission.ACCESS_FINE_LOCATION}, 123);//
    // está dizendo o que o botão irá fazer algo quando pressionado
        btnGeo.setOnClickListener(new View.OnClickListener() {
            //sobrescrevendo o metado onclick do clicklistener e estamos indicado que vamos utilizar aplicação GPStracker e puxar  a localização do dispositivo atraves dela com getlocation
            @Override
            public void onClick(View view) {
                GPSTracker g = new GPSTracker(getApplication());
                Location l = g.getLocation();

                // Se a localização for diferente de nulo vai pegar a latitude e longitude e printar na tela a informação de latitude e londitude
                if (l != null){
                    double lat = l.getLatitude();
                    double lon = l.getLongitude();
                    Toast.makeText(getApplication(), "LATITUDE:" + lat +"\n LONGITUDE:" + lon, Toast.LENGTH_LONG).show();
                }




            }
        });

        // uma verificação de o usario deu a permissão para utilização da camera, caso contrario  irá pedir a permissão
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA))!=PackageManager.PERMISSION_GRANTED{
            ActivityCompat.requestPermissions(this, new String [] {Manifest.permission.CAMERA}, 0 );
        }

        // vai exibir  a image viewfoto e adicionando um clicklistener a ela
        imageViewFoto = (ImageView) findViewById(R.id.image_foto);
        findViewById(R.id.btn_pic).setOnClickListener(new View.OnClickListener() {

            // sobrescrevendo o metado on lick com nosso metodo tirar foto
            @Override
            public void onClick(View view) {
                tirarFoto();

            }
        });

        // declarando a função tirar foto que abre a camera do dispositivo e retorna a foto tirada
        private void tirarFoto(){
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 1);

        }
        // sobresvendo o acitividresult para receber o resultados da activity de tirar foto
        @Override
                protected void onActivityResult(int requestCode, int resultCode, @Nullable intent data){
            // vai pegar a imagem que usamos e vai transforma-la em bitmap e depois setar ela além disso irá chamar novamento o metodo on activity result
            if (requestCode == 1 && resultCode == RESULT_OK){
                Bundle extras = data.getExtras();
                Bitmap imagem = (Bitmap) extras.get("data");
                imageViewFoto.setImageBitmap(imagem);
            }
            super.onActivityResult(requestCode, resultCode, data);

        }

        }


    }
