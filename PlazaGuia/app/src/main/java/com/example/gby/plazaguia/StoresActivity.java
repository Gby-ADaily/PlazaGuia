package com.example.gby.plazaguia;

import android.content.pm.ActivityInfo;
import android.content.pm.ComponentInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class StoresActivity extends AppCompatActivity {
    public ImageButton btnCambiarSiguiente,btnCambiarAnterior;
    public RelativeLayout lytMapa;
    public TextView txtNumeroPiso;
    public int numeroPiso = 1, primeroPiso=1,ultimoPiso=3;
    public String tipoMapa ="tienda",packName;
    public double anchuraMapa,largoMapa;
    public MapLocation mapLocation;
    public CreateBottons createBottons;
    public SQLControlador sqlControlador;
    public Cursor cTiendas,cpisos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stores);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // vertical
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); // horizontal

        btnCambiarSiguiente = (ImageButton) findViewById(R.id.btnCambiarSiguiente);
        btnCambiarAnterior = (ImageButton) findViewById(R.id.btnCambiarAnterior);
        lytMapa = (RelativeLayout) findViewById(R.id.lytMapa);
        txtNumeroPiso = (TextView) findViewById(R.id.txtNumeroPiso);
        // MEDIDAS
        packName =this.getPackageName();
        sqlControlador = new SQLControlador(this);
        sqlControlador.abrirBaseDeDatos();
        visualizarBoton(numeroPiso);

        ViewTreeObserver vto = lytMapa.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                lytMapa.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                anchuraMapa= lytMapa.getMeasuredWidth();
                largoMapa=lytMapa.getMeasuredHeight();

                crearBotones();
                visualizarTiendas(numeroPiso);
            }
        });
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
        visualizarTiendas(piso);
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

    public void crearBotones(){
        cTiendas =sqlControlador.leerTiendas();
        cpisos=sqlControlador.leerPisos();
        createBottons = new CreateBottons(this,cTiendas,cpisos,
                anchuraMapa, largoMapa,lytMapa);
        //cTiendas.close();
        //cpisos.close();
        createBottons.crearBotones();
    }
    public void visualizarTiendas(int piso){
        cTiendas.moveToFirst();
        //Cursor c;
        do{
            cpisos.moveToFirst();
            Cursor c = sqlControlador.seleccionarPiso(cTiendas.getInt(0));
            String id;
            do{
                int o=c.getInt(1),
                        p=c.getInt(0),
                l=cTiendas.getInt(0);
                id = cTiendas.getInt(0)+""+c.getInt(1);
                Button btn =(Button) findViewById(Integer.parseInt(id));
                if (c.getInt(1) == piso){
                    btn.setVisibility(View.VISIBLE);
                    //break;
                }else{
                    btn.setVisibility(View.INVISIBLE);
                }
            }while (c.moveToNext());
            //cpisos.close();
        }while (cTiendas.moveToNext());
    }
}
