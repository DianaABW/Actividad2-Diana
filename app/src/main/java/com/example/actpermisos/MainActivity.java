package com.example.actpermisos;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.Manifest;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE = 100;
    private TextView camara, huella, bluetooth, call;
    private Button b1, b2, b3;
    boolean cam,huell,blue,tel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        begin();
        b2.setOnClickListener(this::verificar);
        b1.setOnClickListener(this::solicitar);

    }

    private void begin(){
        camara=findViewById(R.id.camara);
        huella=findViewById(R.id.huella);
        bluetooth=findViewById(R.id.bluetooth);
        call=findViewById(R.id.call);

        b1=findViewById(R.id.button1);
        b2=findViewById(R.id.button2);
        //Se desabilita el boton de solicitar permisos
        b1.setEnabled(false);
    }
    private void verificar(View x){

        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
            // El permiso de ubicación ya está otorgado.
            camara.setText("Permiso de camara otorgado");
            cam=true;
        } else {
            // El permiso de ubicación no está otorgado, debes solicitarlo.
            camara.setText("Permiso de camara denegado");
            cam=false;
        }
        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                android.Manifest.permission.USE_BIOMETRIC) == PackageManager.PERMISSION_GRANTED){
            // El permiso de ubicación ya está otorgado.
            huella.setText("Permiso de huella otorgado");
            huell=true;
        } else {
            // El permiso de ubicación no está otorgado, debes solicitarlo.
            huella.setText("Permiso de huella denegado");
            huell=false;
        }
        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                android.Manifest.permission.BLUETOOTH) == PackageManager.PERMISSION_GRANTED) {
            // El permiso de ubicación ya está otorgado.
            bluetooth.setText("Permiso de ubicacion otorgado");
            blue=true;
        } else {
            // El permiso de ubicación no está otorgado, debes solicitarlo.
            bluetooth.setText("Permiso de ubicacion denegado");
            blue=false;
        }
        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            // El permiso de ubicación ya está otorgado.
            call.setText("Permiso de llamar otorgado");
            tel=true;
        } else {
            // El permiso de ubicación no está otorgado, debes solicitarlo.
            call.setText("Permiso de llamar denegado");
            tel=false;
        }
        b1.setEnabled(true);
    }
    private void solicitar(View v){
        if(cam==false){
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{android.Manifest.permission.CAMERA}, REQUEST_CODE);
        }
        if(huell == false){
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{android.Manifest.permission.USE_BIOMETRIC}, REQUEST_CODE);
        }
        if(blue==false){
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{android.Manifest.permission.BLUETOOTH}, REQUEST_CODE);
        }
        if(tel==false){
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{android.Manifest.permission.CALL_PHONE}, REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==REQUEST_CODE){
            if(permissions.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Permisos concedidos", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this, "Permisos denegados", Toast.LENGTH_LONG).show();
                if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, android.Manifest.permission.CAMERA)
                        || ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, android.Manifest.permission.USE_BIOMETRIC)
                        || ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, android.Manifest.permission.BLUETOOTH)
                        || ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.CALL_PHONE)){
                    //SI
                }else{
                    Toast.makeText(this, "Despues de dos intentos fallidos, se deben activar los permisos desde la configuracion del celular.", Toast.LENGTH_LONG).show();
                }

            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}