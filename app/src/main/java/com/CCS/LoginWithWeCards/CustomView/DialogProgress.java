package com.CCS.LoginWithWeCards.CustomView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.text.SpannableString;
import android.text.Spanned;

import com.CCS.LoginWithWeCards.R;
import com.CCS.LoginWithWeCards.Utils.AppTypeface;

import uk.co.chrisjenx.calligraphy.CalligraphyTypefaceSpan;


/**
 * Created by bhavika on 5/9/16.
 */

public class DialogProgress extends ProgressDialog {
    AppTypeface typeface;

    public DialogProgress(Activity context) {
        super(context, getStyle());

        typeface = new AppTypeface(context);
        setCancelable(false);

        String message = "Please Wait ...";
        SpannableString spannableString = new SpannableString(message);
        CalligraphyTypefaceSpan typefaceSpan = new CalligraphyTypefaceSpan(typeface.getRegularFont());
        spannableString.setSpan(typefaceSpan, 0, message.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        setMessage(spannableString);
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
