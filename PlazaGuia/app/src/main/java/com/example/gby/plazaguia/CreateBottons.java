package com.example.gby.plazaguia;

import android.content.Context;
import android.database.Cursor;
import android.renderscript.Double2;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * Created by Gby on 22-Jul-17.
 */

public class CreateBottons {
    public double anchuraMapa,largoMapa;
    public float xLongitud,yLatitud;
    public RelativeLayout lytMapa;
    public Context context;
    public Cursor cTiendas,cpisos;
    //SQLControlador sqlControlador = new SQLControlador(this);
      //  sqlControlador.abrirBaseDeDatos();

    public CreateBottons(Context context,Cursor cTiendas,Cursor cpisos,
                         double anchuraMapa,double largoMapa,
                         RelativeLayout lytMapa) {
        this.context=context;
        this.cTiendas=cTiendas;
        this.anchuraMapa=anchuraMapa;
        this.largoMapa =largoMapa;
        this.lytMapa=lytMapa;
        this.cpisos=cpisos;
    }
    public void crearBotones(){
        do{//nombresTiendas.length
            cpisos.moveToFirst();
            do{
                if (cpisos.getInt(0)==cTiendas.getInt(0)){
            final Button btnTag = new Button(context);
            Cursor c;
            btnTag.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));
            btnTag.setText(cTiendas.getString(1));
            String id;
            //c = sqlControlador.seleccionarPiso(cTiendas.getInt(0));
                    id = cTiendas.getInt(0)+""+cpisos.getString(1);
                    btnTag.setId(Integer.parseInt(id));
                    obtenerUbicaciones(cpisos.getString(2));
                    btnTag.setY(yLatitud);
                    btnTag.setX(xLongitud);
                    btnTag.setVisibility(View.INVISIBLE);
                    lytMapa.addView(btnTag);
                    ((Button) btnTag.findViewById(Integer.parseInt(id))).setOnClickListener(new View.OnClickListener(){

                        @Override
                        public void onClick(View v) {
                    /*Toast toast = Toast.makeText(getApplicationContext(), "id "+btnTag.getId(), Toast.LENGTH_SHORT);
                    toast.show();*/
                        }
                    });
                }
            }while (cpisos.moveToNext());

        }while (cTiendas.moveToNext());
    }
    public void obtenerUbicaciones (String numero){
        char [] caracteres = numero.toCharArray();
        int i =0;
        Double dNum;
        String num="";
        while(!String.valueOf(caracteres[i]).equals(",") ){
            num = num+caracteres[i];
            i++;
        }
        dNum= Double.parseDouble(num);
        xLongitud=(float)((dNum*anchuraMapa)/21);
        num="";
        while(caracteres.length != i+1 ){
                i++;
                num = num+caracteres[i];
        }
        dNum= Double.parseDouble(num);
        yLatitud=(float)(largoMapa-((dNum*largoMapa)/14));
    }
}
