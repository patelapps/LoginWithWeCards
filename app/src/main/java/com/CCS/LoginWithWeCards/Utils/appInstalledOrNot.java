package com.CCS.LoginWithWeCards.Utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

/**
 * Created by mauliksantoki on 24/4/17.
 * <p>
 * this code is use fro check app install in phone or not when app install then return true other wise returen false..
 */

public class appInstalledOrNot {

    public static boolean appInstalledOrNot(String packagename, Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packagename, 0);
            /*we add login with wecards in version code 5 ,version 1.0.4*/
            // sagar : 18/12/18 getLongVersionCode crashes with error: No such virtual method in system framework before pie!

            // sagar : 18/12/18 Build version of host application i.e. phD
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O_MR1) {
                return packageInfo.versionCode > 4;
            } else {
                return packageInfo.getLongVersionCode() > 4;
            }

        } catch (PackageManager.NameNotFoundException e) {
            Log.e(AppConstants.Tag, e.getMessage());

        }
        return false;
    }
}
