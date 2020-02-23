package com.hassan.ta2mk.Common;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.hassan.ta2mk.Model.RegisterModel;

public class Commens {
    public static RegisterModel curent_user;
    public static ProgressDialog mProgressDialog;

    //for checking connection internet
    public static boolean isConnectToInternet(Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null) {
            NetworkInfo[] info = connectivityManager.getAllNetworkInfo();

            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED)
                        return true;
                }
            }
        }
        return false;
    }

    public static void Prograss(Context context,String Message){

        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setMessage(Message);
    }
}
