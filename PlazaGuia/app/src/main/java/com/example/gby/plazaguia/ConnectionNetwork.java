package com.example.gby.plazaguia;

import android.app.Service;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Gby on 17-Jun-17.
 */

public class ConnectionNetwork {

    Context context;

    public ConnectionNetwork(Context context) {
        this.context = context;
    }

    public boolean isConnected(){
        ConnectivityManager conecttivity = (ConnectivityManager)
                context.getSystemService(Service.CONNECTIVITY_SERVICE);
        if (conecttivity != null){
            NetworkInfo info = conecttivity.getActiveNetworkInfo();
            if (info != null){
                if(info.getState() == NetworkInfo.State.CONNECTED){
                    return true ;
                }
            }
        }
        return false;
    }
}
