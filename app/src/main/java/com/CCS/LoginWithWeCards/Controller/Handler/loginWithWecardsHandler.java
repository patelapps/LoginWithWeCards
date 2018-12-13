package com.CCS.LoginWithWeCards.Controller.Handler;

/**
 * Created by mauliksantoki on 25/4/17.
 */

public interface loginWithWecardsHandler {
    void setAppPackageName();

    void setAppIcon();

    void setAppName();

    void setAppLoginID();


    void login();

    void logout();

    void unRegisterCommunicator();

    boolean validation();

    void error(String message);



}
