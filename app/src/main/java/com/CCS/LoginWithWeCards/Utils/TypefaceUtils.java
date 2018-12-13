package com.CCS.LoginWithWeCards.Utils;

import android.content.Context;
import android.graphics.Typeface;



/**
 * Created by sagar on 9/5/17.
 */

public class TypefaceUtils {


    public static final int INT_CODE_REGULAR_FONT = 0;
    public static final int INT_CODE_BOLD_FONT = 1;


    private static final String REGULAR_FONT = "Muli.ttf";
    private static final String BOLD_FONT = "Muli_bold.ttf";


    private static volatile TypefaceUtils mTypefaceUtils;
    private static volatile Typeface regularTypeface;
    private static volatile Typeface boldTypeface;


    //Private constructor
    private TypefaceUtils() {
        //Prevent from the reflection
        if (mTypefaceUtils != null) {
            throw new RuntimeException(AppContacts.STR_MSG_ERROR_TYPEFACE_REFLECTION);
        }
    }

    /**
     * Gives {@link #mTypefaceUtils} instance of this class with secure singleton pattern
     * <p>
     * Uses thread safety and double check lock on volatile return type
     * <p>
     *
     * @return singleton and volatile {@link #mTypefaceUtils}
     * see {@link #mTypefaceUtils}
     * @since 1.0
     */
    public static TypefaceUtils getInstance() {
        if (mTypefaceUtils == null) {
            synchronized (TypefaceUtils.class) {
                if (mTypefaceUtils == null) {
                    mTypefaceUtils = new TypefaceUtils();
                }
            }
        }
        return mTypefaceUtils;
    }

    /**
     * Gives {@link #boldTypeface} instance of this class with secure singleton pattern
     * <p>
     * Uses thread safety and double check lock on volatile return type
     * <p>
     *
     * @return singleton and volatile {@link #boldTypeface}
     * see {@link #boldTypeface}
     * @since 1.0
     */
    public Typeface getBoldTypeface(Context mContext) {
        if (boldTypeface == null) {
            synchronized (TypefaceUtils.class) {
                if (boldTypeface == null) {
                    boldTypeface = Typeface.createFromAsset(mContext.getAssets(), BOLD_FONT);
                }
            }
        }
        return boldTypeface;
    }

    /**
     * Gives {@link #regularTypeface} instance of this class with secure singleton pattern
     * <p>
     * Uses thread safety and double check lock on volatile return type
     * <p>
     *
     * @return singleton and volatile {@link #regularTypeface}
     * see {@link #regularTypeface}
     * @since 1.0
     */
    public Typeface getRegularTypeface(Context mContext) {
        if (regularTypeface == null) {
            synchronized (TypefaceUtils.class) {
                if (regularTypeface == null) {
                    regularTypeface = Typeface.createFromAsset(mContext.getAssets(), REGULAR_FONT);
                }
            }
        }
        return regularTypeface;
    }
}
