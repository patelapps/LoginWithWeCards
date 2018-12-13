package com.CCS.LoginWithWeCards.Utils;

import android.graphics.drawable.Drawable;

/**
 * Created by mauliksantoki on 24/4/17.
 */

public class AppContacts {

    public static final String wecardsAppPackage = "com.CCS.WeCards";
    public static final String loginActvity = wecardsAppPackage + ".LoginWithWecards";

    public static String loginWithAppPackageName;
    public static String loginWithAppName;
    public static Drawable loginWithAppIcon;
    public static String loginWithWecardKey;

    public final static String Tag = "Login With Wecards";

    public final static String DEVICE_TYPE = "2";
    public final static String LOGINWITH = "1";

    public static final int COUNTRY_CODE_LIMIT = 5;
    public static final int PHONE_NUMBER_LIMIT = 15;

    public static final String PREFS_PRIVATE = "PREFS_PRIVATE";

    public static final String CONTACT_US = "Any queries/feedback email to info@ccs.sg";

    public static final String STR_MSG_ERROR_TYPEFACE_REFLECTION
            = "Use getInstance() method to get single instance of this class.";


    public static final String URL_PRIVACY = "https://www.we.cards/privacy_policy.php?from=mobile";
    public static final String URL_TERMS = "https://www.we.cards/terms_and_condition.php?from=mobile";
}
