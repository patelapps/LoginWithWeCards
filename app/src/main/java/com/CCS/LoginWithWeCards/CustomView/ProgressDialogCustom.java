package com.CCS.LoginWithWeCards.CustomView;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.widget.TextView;

import com.CCS.LoginWithWeCards.R;


/**
 * Created by bhavika on 5/9/16.
 */

public class ProgressDialogCustom extends ProgressDialog {


    public static ProgressDialogCustom getProgressDialogCustom(Context context) {
        ProgressDialogCustom progressDialogCustom = new ProgressDialogCustom(context);

        ((TextView) progressDialogCustom.findViewById(android.R.id.title)).setTypeface(Typeface.createFromAsset(context.getAssets(), "Roboto-Thin.ttf"));
        return progressDialogCustom;
    }


    public ProgressDialogCustom(Context context) {
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
