package com.CCS.LoginWithWeCards.Utils;

import android.app.Activity;
import android.text.SpannableString;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.CCS.LoginWithWeCards.CustomView.WeCardCustomTextView;
import com.CCS.LoginWithWeCards.R;

import androidx.appcompat.app.AlertDialog;
import uk.co.chrisjenx.calligraphy.CalligraphyTypefaceSpan;

public final class Utils {
    private Utils() {
    }

    public static void showAlertDialog(Activity activity, String message) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.layout_dialog, null);

        dialogBuilder.setView(dialogView);

        WeCardCustomTextView ctvMessage = dialogView.findViewById(R.id.ctvMessage);
        ctvMessage.setText(message);
        WeCardCustomTextView cbtnOk = dialogView.findViewById(R.id.cbtn_ok);
        AlertDialog alertDialog = dialogBuilder.create();
        cbtnOk.setOnClickListener(v -> {
            alertDialog.cancel();
        });
        alertDialog.show();
    }
}
