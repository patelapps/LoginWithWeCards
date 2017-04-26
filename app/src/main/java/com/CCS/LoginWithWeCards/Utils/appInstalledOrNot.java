package com.CCS.LoginWithWeCards.Utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

/**
 * Created by mauliksantoki on 24/4/17.
 * <p>
 * this code is use fro check app install in phone or not when app install then return true other wise returen false..
 */

public class appInstalledOrNot {

    public static boolean appInstalledOrNot(String packagename, Context context) {
        try {
            context.getPackageManager().getPackageInfo(packagename, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(AppContacts.Tag, e.getMessage());

        }
        return false;
    }
}
