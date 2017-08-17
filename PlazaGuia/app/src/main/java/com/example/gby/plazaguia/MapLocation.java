package com.example.gby.plazaguia;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;

/**
 * Created by Gby on 19-Jul-17.
 */

public class MapLocation {
    Bitmap bmpOriginal;
    BitmapDrawable bmpBackground;
    int piso,mapa;
    String tipoMapa,packgeName;
    Resources res ;
    public MapLocation(String tipoMapa,int piso,Resources res,String packgeName){
        this.piso = piso;
        this.tipoMapa = tipoMapa;
        this.res = res;
        this.packgeName = packgeName;
    }
    public BitmapDrawable obtenerMapa(){
        String nombreMap = "mapa_"+tipoMapa+"_"+piso;
        mapa = res.getIdentifier(nombreMap, "mipmap", packgeName); //this.getPackageName()
        Bitmap bmpOriginal = BitmapFactory.decodeResource(res, mapa);
        bmpBackground = new BitmapDrawable(res, bmpOriginal);
        return bmpBackground;
    }
}
