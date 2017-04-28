package com.CCS.LoginWithWeCards.Controller.Handler;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.util.Log;

import com.CCS.LoginWithWeCards.API.Handler.APIResponse;
import com.CCS.LoginWithWeCards.API.Handler.APIResponseHandler;
import com.CCS.LoginWithWeCards.API.RestClient;
import com.CCS.LoginWithWeCards.CustomView.ProgressDialogCustom;
import com.CCS.LoginWithWeCards.Model.LogoutRequest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.CCS.LoginWithWeCards.API.jsonKeys.DATA;
import static com.CCS.LoginWithWeCards.API.jsonKeys.LOGIN_TOKEN;
import static com.CCS.LoginWithWeCards.API.jsonKeys.USER_ID;
import static com.CCS.LoginWithWeCards.Utils.AppContacts.PREFS_PRIVATE;
import static com.CCS.LoginWithWeCards.Utils.AppContacts.Tag;
import static com.CCS.LoginWithWeCards.Utils.AppContacts.loginWithAppPackageName;
import static com.CCS.LoginWithWeCards.Utils.AppContacts.loginWithWecardKey;

/**
 * Created by mauliksantoki on 26/4/17.
 */

public class CommunicatorHandler extends Communicator {


    private BroadcastReceiver loginWidthWecardsReceiver;
    IntentFilter filter;

    private Activity activity;

    private SharedPreferences preferences;
    private LoginHandler loginHandler;

    private ProgressDialogCustom progressDialogCustom;

    private LogoutRequest logoutRequest;
    private APIResponse apiResponseHandler;


    @Override
    public void connect(Activity activity, final LoginHandler loginHandler) {
        this.activity = activity;
        filter = new IntentFilter();
        filter.addAction(loginWithWecardKey);
        this.loginHandler = loginHandler;
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

    @Override
    public void logout(Activity activity) {
        if (preferences == null) {
            preferences = activity.getSharedPreferences(PREFS_PRIVATE, Context.MODE_PRIVATE);
        }
        logoutRequest = new LogoutRequest();
        logoutRequest.setApi_key(loginWithWecardKey);
        logoutRequest.setPackagename(loginWithAppPackageName);
        logoutRequest.setLogin_token(preferences.getString(LOGIN_TOKEN, ""));
        logoutRequest.setUser_id(preferences.getString(USER_ID, ""));
        logoutAPI();
    }

    @Override
    public void logoutAPI() {
        showProgress();
        if (apiResponseHandler == null) {
            apiResponseHandler = new APIResponseHandler();
        }

        RestClient.GitApiInterface service = RestClient.getClient();
        Call<ResponseBody> call = service.logout(logoutRequest);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    loginHandler.logoutResult(apiResponseHandler.getResult(response));
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString(USER_ID, "");
                    editor.putString(LOGIN_TOKEN, "");
                    editor.commit();
                } else {
                    Log.e(Tag, "fail" + response);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(Tag, t.getMessage());
            }
        });

        hideProgress();

    }

    @Override
    public void showProgress() {
        if (progressDialogCustom == null) {
            progressDialogCustom = new ProgressDialogCustom(activity);
        }
        progressDialogCustom.show();
    }

    @Override
    public void hideProgress() {

        if (progressDialogCustom != null) {
            progressDialogCustom.hide();
        }

    }
}
