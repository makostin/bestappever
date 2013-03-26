package com.mmf.util;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v4.app.FragmentActivity;

public class InternetConnectionUtil {

	public static boolean hasInternetConnection(Context context) {
		ConnectivityManager mConManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		return (mConManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected() || mConManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected());
	}

	public static void showInternetConnectionSettings(FragmentActivity activity) {
        activity.startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS));
	}
}
