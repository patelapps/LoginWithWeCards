package com.CCS.LoginWithWeCards.UI.Handler;

/**
 * Created by mauliksantoki on 25/4/17.
 */

public interface ScreenHandler {

    void initControl();

    void initDailogHeight();

    void initlayouts();

    void initTypeFace();

    void initClickListener();

    void APICall();

    void hideSoftInput();

    void openAppInPlayStore();

    void showProgress();

    void hideProgress();

    boolean validation();

    void error(String message);

    void scrollUpDialog();
}
