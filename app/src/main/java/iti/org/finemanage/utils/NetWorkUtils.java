package iti.org.finemanage.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import iti.org.finemanage.base.MyApplication;

/**
 * Created by xueli on 2017/1/14.
 */

public class NetWorkUtils {

    public static boolean isNetworkAvailable() {
        ConnectivityManager connectivity = (ConnectivityManager) MyApplication.getContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                // 当前网络是连接的
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    // 当前所连接的网络可用
                    return true;
                }
            }
        }
        return false;
    }
}
