package com.example.gby.plazaguia;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public Button btnTiendas,btnEvacuacion,btnSeguridad,btnServicio,btnEmergencia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // vertical
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); // horizontal

        btnTiendas = (Button) findViewById(R.id.btnTiendas);
        btnEvacuacion = (Button) findViewById(R.id.btnEvacuacion);
        btnSeguridad = (Button) findViewById(R.id.btnSeguridad);
        btnServicio = (Button) findViewById(R.id.btnServicio);

        btnEmergencia=(Button) findViewById(R.id.btnEmergencia);

        // acciones de los botones
        btnTiendas.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,StoresActivity.class);
                startActivity(intent);
                //overridePendingTransition(R.anim.left_in, R.anim.left_out);
            }
        });
        btnEvacuacion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,EvacuationActivity.class);
                startActivity(intent);
                //overridePendingTransition(R.anim.left_in, R.anim.left_out);
            }
        });
        btnSeguridad.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SecurityActivity.class);
                startActivity(intent);
                //overridePendingTransition(R.anim.left_in, R.anim.left_out);
            }
        });
        btnServicio.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ServicesActivity.class);
                startActivity(intent);
                //overridePendingTransition(R.anim.left_in, R.anim.left_out);
            }
        });
        btnEmergencia.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = getLayoutInflater();

                View dialoglayout = inflater.inflate(R.layout.dialog_alert_emergency_type, null);

                Button btnSismoEvacuacion = (Button) dialoglayout.findViewById(R.id.btnSismo);
                //final EditText etMensaje = (EditText) dialoglayout.findViewById(R.id.et_EmailMensaje);

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setView(dialoglayout);
                //builder.show();
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                btnSismoEvacuacion.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this,EvacuationActivity.class);
                        startActivity(intent);
                        alertDialog.dismiss();
                    }
                });

            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //se puede hacer una pregunta para salir de la app
        //System.exit(0)
    }
}
