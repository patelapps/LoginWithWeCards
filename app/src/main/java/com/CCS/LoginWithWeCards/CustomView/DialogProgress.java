package com.CCS.LoginWithWeCards.CustomView;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;

import com.CCS.LoginWithWeCards.R;


/**
 * Created by bhavika on 5/9/16.
 */

public class DialogProgress extends ProgressDialog {


    public DialogProgress(Context context) {
        super(context, getStyle());
        setMessage("Please Wait ...");
        setCancelable(false);
    }

    private static int getStyle() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            return R.style.AppAlertTheme;
        } else {
            return R.style.AppAlertTheme19;
        }
    }
}
