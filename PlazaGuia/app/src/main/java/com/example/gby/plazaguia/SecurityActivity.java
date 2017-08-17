package com.example.gby.plazaguia;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SecurityActivity extends AppCompatActivity {
    public ImageButton btnCambiarSiguiente,btnCambiarAnterior;
    public LinearLayout lytMapa;
    public TextView txtNumeroPiso;
    public int numeroPiso = 1, primeroPiso=1,ultimoPiso=3;
    public String tipoMapa ="seguridad",packName;
    public MapLocation mapLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // vertical
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); // horizontal

        btnCambiarSiguiente = (ImageButton) findViewById(R.id.btnCambiarSiguiente);
        btnCambiarAnterior = (ImageButton) findViewById(R.id.btnCambiarAnterior);
        lytMapa = (LinearLayout) findViewById(R.id.lytMapa);
        txtNumeroPiso = (TextView) findViewById(R.id.txtNumeroPiso);

        //ponerMapa(numeroPiso);
        packName =this.getPackageName();

        btnCambiarSiguiente.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                numeroPiso++;
                ponerMapa(numeroPiso);
            }
        });
        btnCambiarAnterior.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                numeroPiso--;
                ponerMapa(numeroPiso);
            }
        });
        //int i=Integer.parseInt("hello123".replaceAll("[\\D]",""));

    }
    public void ponerMapa(int piso){
        mapLocation = new MapLocation(tipoMapa,numeroPiso,getResources(),packName);
        lytMapa.setBackground(mapLocation.obtenerMapa());
        txtNumeroPiso.setText(Integer.toString(numeroPiso));
        visualizarBoton(piso);
    }
    public void visualizarBoton(int piso) {
        if(primeroPiso == piso){
            btnCambiarAnterior.setVisibility(View.INVISIBLE);
        }else if(ultimoPiso == piso){
            btnCambiarSiguiente.setVisibility(View.INVISIBLE);
        }else {
            btnCambiarAnterior.setVisibility(View.VISIBLE);
            btnCambiarSiguiente.setVisibility(View.VISIBLE);
        }
    }

}
