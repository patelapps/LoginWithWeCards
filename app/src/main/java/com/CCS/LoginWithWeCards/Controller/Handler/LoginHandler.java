package com.CCS.LoginWithWeCards.Controller.Handler;

import androidx.annotation.Keep;

/**
 * Created by mauliksantoki on 24/4/17.
 */
@Keep
public interface LoginHandler {

    void loginResult(String Result);

    void logoutResult(String Result);


}
