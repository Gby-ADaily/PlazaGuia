package com.example.gby.plazaguia;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Random;

public class SplashScreen extends AppCompatActivity {
    public SQLControlador sqlControlador ;
    public ConnectionNetwork cd;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // vertical
        // setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); // horizontal
/*
        sqlControlador = new SQLControlador(this).abrirBaseDeDatos();

        sqlControlador.insertarTienda("PizzaHut");
        sqlControlador.insertarTienda("KFC");
        sqlControlador.insertarTienda("Inkafarma");
        sqlControlador.insertarTienda("Macdonal");
        sqlControlador.insertarTienda("Bata");
        sqlControlador.insertarTienda("Ripley");
        sqlControlador.insertarTienda("PlazaVea");
        sqlControlador.insertarTienda("Tottus");

        sqlControlador.insertarPiso(1,1,"3,10");
        sqlControlador.insertarPiso(2,1,"3,7");
        sqlControlador.insertarPiso(3,1,"3,3");
        sqlControlador.insertarPiso(4,1,"11,9");
        sqlControlador.insertarPiso(5,1,"8,2");
        sqlControlador.insertarPiso(6,1,"13,2");
        sqlControlador.insertarPiso(7,1,"17,12");
        sqlControlador.insertarPiso(8,1,"18,4");

        sqlControlador.insertarTienda("Coney Park");
        sqlControlador.insertarTienda("Claro");
        sqlControlador.insertarTienda("Menn");

        sqlControlador.insertarPiso(9,2,"2,7");
        sqlControlador.insertarPiso(10,2,"8,2");
        sqlControlador.insertarPiso(4,2,"10,10");
        sqlControlador.insertarPiso(7,2,"16,10");
        sqlControlador.insertarPiso(11,2,"18.5,3.5");

        sqlControlador.insertarTienda("Platanitos");
        sqlControlador.insertarTienda("Moixx");
        //sqlControlador.insertarTienda("Tottus");
        sqlControlador.insertarTienda("Norky");
        sqlControlador.insertarTienda("Movistar");

        sqlControlador.insertarPiso(12,3,"4,12");
        sqlControlador.insertarPiso(6,3,"3,4");
        sqlControlador.insertarPiso(13,3,"6,4");
        sqlControlador.insertarPiso(8,3,"11.5,10");
        sqlControlador.insertarPiso(14,3,"10,3");
        sqlControlador.insertarPiso(15,3,"18,8");
        sqlControlador.cerrar();*/

        //****************************
        cd = new ConnectionNetwork(this);
        isNetwork();

        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.lytFonfoLogo);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String [] colores= {"#F44336","#E91E63","#9C27B0","#673AB7","#3F51B5",
                        "#2196F3","#03A9F4","#00BCD4","#009688","#4CAF50" ,
                        "#8BC34A", "#CDDC39","#FFEB3B","#FFC107","#FF9800",
                        "#FF5722","#795548","#9E9E9E","#607D8B", "#00E676"};
                int i= (int) (Math.random()*colores.length);

                linearLayout.setBackgroundColor(Color.parseColor(colores[i]));
                handler.postDelayed(this, 900);
            }
        },900);

        new Handler().postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void run() {
                //Explode explode= new Explode();
                //getWindow().setAllowEnterTransitionOverlap(false);
                //isNetwork();

                Intent intent = new Intent(SplashScreen.this,MainActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        },1000);
        //checkDataBase();

    }

    // ********************** funcion para verificar conección a Internet
    private boolean isNetwork(){
        if(cd.isConnected()){
            Toast.makeText(SplashScreen.this, "Internet", Toast.LENGTH_SHORT).show();
            return true;
        }else{
            Toast.makeText(SplashScreen.this, "No Internet", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


}
