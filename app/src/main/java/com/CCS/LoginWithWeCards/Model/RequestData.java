package com.CCS.LoginWithWeCards.Model;

import android.app.Activity;
import android.content.Intent;

import com.CCS.LoginWithWeCards.Model.Handler.LoginRequestHandler;

import static com.CCS.LoginWithWeCards.API.jsonKeys.DATA;
import static com.CCS.LoginWithWeCards.API.jsonKeys.DEVICE_TYPE;
import static com.CCS.LoginWithWeCards.Utils.AppContacts.LOGINWITH;
import static com.CCS.LoginWithWeCards.Utils.AppContacts.loginWithAppPackageName;
import static com.CCS.LoginWithWeCards.Utils.AppContacts.loginWithWecardKey;


/**
 * Created by mauliksantoki on 26/4/17.
 */

public class RequestData extends LoginRequestHandler {
    @Override
    public LoginRequest getRequestData() {

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setApi_key(loginWithWecardKey);
        loginRequest.setPackagename(loginWithAppPackageName);
        loginRequest.setDevice_type(DEVICE_TYPE);
        loginRequest.setLogin_with(LOGINWITH);
        return loginRequest;
    }

    @Override
    public void loginCompleted(Activity activity, String result) {
        Intent intent = new Intent();
        intent.setAction(loginWithWecardKey);
        intent.putExtra(DATA, result);
        activity.sendBroadcast(intent);
    }
}
