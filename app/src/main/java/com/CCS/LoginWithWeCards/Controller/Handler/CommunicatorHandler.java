package com.CCS.LoginWithWeCards.Controller.Handler;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import static com.CCS.LoginWithWeCards.API.jsonKeys.DATA;
import static com.CCS.LoginWithWeCards.Utils.AppContacts.loginWithWecardKey;

/**
 * Created by mauliksantoki on 26/4/17.
 */

public class CommunicatorHandler extends Communicator {


    private BroadcastReceiver loginWidthWecardsReceiver;
    IntentFilter filter;

    @Override
    public void connect(Activity activity, final LoginHandler loginHandler) {
        filter = new IntentFilter();
        filter.addAction(loginWithWecardKey);
        loginWidthWecardsReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (loginHandler != null) {
                    loginHandler.loginResult(intent.getStringExtra(DATA));
                }
            }
        };
        activity.registerReceiver(loginWidthWecardsReceiver, filter);
    }

    @Override
    public void disConnect(Activity activity) {
        activity.unregisterReceiver(loginWidthWecardsReceiver);
    }


}
