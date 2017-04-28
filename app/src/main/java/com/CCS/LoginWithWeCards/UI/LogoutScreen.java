package com.CCS.LoginWithWeCards.UI;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.ViewGroup;
import android.view.Window;

import com.CCS.LoginWithWeCards.API.Handler.APIResponse;
import com.CCS.LoginWithWeCards.API.Handler.APIResponseHandler;
import com.CCS.LoginWithWeCards.API.RestClient;
import com.CCS.LoginWithWeCards.Controller.Handler.LoginHandler;
import com.CCS.LoginWithWeCards.CustomView.ProgressDialogCustom;
import com.CCS.LoginWithWeCards.Model.LogoutRequest;
import com.CCS.LoginWithWeCards.UI.Handler.LogoutHandler;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.CCS.LoginWithWeCards.API.jsonKeys.LOGIN_TOKEN;
import static com.CCS.LoginWithWeCards.API.jsonKeys.USER_ID;
import static com.CCS.LoginWithWeCards.Utils.AppContacts.PREFS_PRIVATE;
import static com.CCS.LoginWithWeCards.Utils.AppContacts.Tag;
import static com.CCS.LoginWithWeCards.Utils.AppContacts.loginWithAppPackageName;
import static com.CCS.LoginWithWeCards.Utils.AppContacts.loginWithWecardKey;

/**
 * Created by mauliksantoki on 28/4/17.
 */

public class LogoutScreen extends Dialog implements LogoutHandler {

    private ProgressDialogCustom progressDialogCustom;
    private APIResponse apiResponseHandler;

    private LoginHandler loginHandler;
    private SharedPreferences preferences;

    private Activity activity;
    private LogoutRequest logoutRequest;

    public LogoutScreen(Activity activity, LoginHandler loginHandler) {
        super(activity);
        this.activity = activity;
        this.loginHandler = loginHandler;
        show();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initControl();

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
                hideProgress();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(Tag, t.getMessage());
                hideProgress();
            }
        });
    }

    @Override
    public void initControl() {
        preferences = activity.getSharedPreferences(PREFS_PRIVATE, Context.MODE_PRIVATE);

        logoutRequest = new LogoutRequest();
        logoutRequest.setApi_key(loginWithWecardKey);
        logoutRequest.setPackagename(loginWithAppPackageName);
        logoutRequest.setLogin_token(preferences.getString(LOGIN_TOKEN, ""));
        logoutRequest.setUser_id(preferences.getString(USER_ID, ""));
    }

    @Override
    public void initDailogHeight() {
        Rect displayRectangle = new Rect();
        Window window = getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, (int) (displayRectangle.height() * 0.95f));
//        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setCanceledOnTouchOutside(false);
    }

    @Override
    public void initlayouts() {

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
