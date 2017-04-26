package com.CCS.LoginWithWeCards.Controller.Handler;

import android.app.Activity;

/**
 * Created by mauliksantoki on 26/4/17.
 */

public abstract class Communicator {

    public abstract void connect(Activity activity, LoginHandler loginHandler);

    public abstract void disConnect(Activity activity);
}
