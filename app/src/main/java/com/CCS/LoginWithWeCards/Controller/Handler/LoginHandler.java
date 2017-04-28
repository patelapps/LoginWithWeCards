package com.CCS.LoginWithWeCards.Controller.Handler;

import android.os.Parcelable;
import android.support.annotation.Keep;

/**
 * Created by mauliksantoki on 24/4/17.
 */
@Keep
public interface LoginHandler extends Parcelable {

    public void loginResult(String Result);

    public void logoutResult(String Result);


}
