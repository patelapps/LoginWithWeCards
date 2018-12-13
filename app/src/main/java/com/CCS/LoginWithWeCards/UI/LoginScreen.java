package com.CCS.LoginWithWeCards.UI;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import com.CCS.LoginWithWeCards.API.Handler.APIResponse;
import com.CCS.LoginWithWeCards.API.Handler.APIResponseHandler;
import com.CCS.LoginWithWeCards.API.RestClient;
import com.CCS.LoginWithWeCards.CountryCodeInfo.CountryCodeInfoController;
import com.CCS.LoginWithWeCards.CustomView.CustomTextView;
import com.CCS.LoginWithWeCards.CustomView.DialogProgress;
import com.CCS.LoginWithWeCards.Model.Handler.LoginRequestHandler;
import com.CCS.LoginWithWeCards.Model.LoginRequest;
import com.CCS.LoginWithWeCards.Model.RequestData;
import com.CCS.LoginWithWeCards.R;
import com.CCS.LoginWithWeCards.UI.Handler.ScreenHandler;
import com.CCS.LoginWithWeCards.Utils.AppContacts;
import com.CCS.LoginWithWeCards.Utils.AppTypeface;
import com.CCS.LoginWithWeCards.Utils.OpenCustomTabsIntent;
import com.CCS.LoginWithWeCards.Utils.TypefaceUtils;
import com.CCS.LoginWithWeCards.Utils.Validate;
import com.CCS.LoginWithWeCards.Utils.appInstalledOrNot;
import com.CCS.LoginWithWeCards.databinding.LoginscreenBinding;
import com.google.android.material.textfield.TextInputEditText;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyTypefaceSpan;

import static com.CCS.LoginWithWeCards.API.jsonKeys.API_KEY;
import static com.CCS.LoginWithWeCards.API.jsonKeys.PACKAGENAME;
import static com.CCS.LoginWithWeCards.Utils.AppContacts.COUNTRY_CODE_LIMIT;
import static com.CCS.LoginWithWeCards.Utils.AppContacts.PHONE_NUMBER_LIMIT;
import static com.CCS.LoginWithWeCards.Utils.AppContacts.Tag;
import static com.CCS.LoginWithWeCards.Utils.AppContacts.loginActvity;
import static com.CCS.LoginWithWeCards.Utils.AppContacts.loginWithAppName;
import static com.CCS.LoginWithWeCards.Utils.AppContacts.loginWithAppPackageName;
import static com.CCS.LoginWithWeCards.Utils.AppContacts.loginWithWecardKey;
import static com.CCS.LoginWithWeCards.Utils.AppContacts.wecardsAppPackage;
import static com.CCS.LoginWithWeCards.Utils.errorHandler.showErrorHandler;
import static com.CCS.LoginWithWeCards.Utils.isNetworkAvailable.isNetworkAvailable;


/**
 * Created by mauliksantoki on 25/4/17.
 */

public class LoginScreen extends Dialog implements ScreenHandler {

    private final Activity activity;

    private AppTypeface appTypeface;

    private APIResponse apiResponseHandler;

    private String apiResult = "";

    private CustomTextView tvTitle;
    private CustomTextView tvSignInTitle;
    private CustomTextView tvToConWith;
    private TextInputEditText tietCountryCode;
    private TextInputEditText tietPhoneNumber;
    private TextInputEditText tietPassword;
    private CustomTextView tvToConWe;
    private CustomTextView tvPrivacy;
    private CustomTextView tvTerms;
    private CustomTextView tvCreateAccount;
    private CustomTextView btnSignin;
    private CustomTextView btnCancel;
    private CustomTextView btnOk;
    private ImageView ivClose;
    private ConstraintLayout layoutSignInWith;
    private ConstraintLayout layoutConfirm;
    private NestedScrollView svLogin;

    private DialogProgress progressDialogCustom;

    /*other variable*/
    private CountryCodeInfoController countryCodeInfoController;

    private LoginRequestHandler loginRequestHandler;

    private int bottomScrollHeight = 0;
    private LoginscreenBinding mBinding;

    public LoginScreen(Activity activity) {
        super(activity);
        this.activity = activity;
        show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        initDailogHeight();
        // sagar : 12/12/18 This works but then no data binding!
        setContentView(R.layout.loginscreen);

//        mBinding = LoginscreenBinding.inflate(R.layout.loginscreen);

        // sagar : 12/12/18 This also binds but no window margin
//        View view = LayoutInflater.from(getContext()).inflate(R.layout.loginscreen, null, false);
//        mBinding = LoginscreenBinding.bind(view);

        // sagar : 12/12/18 This works but no window margin
//        mBinding = DataBindingUtil.inflate(LayoutInflater.from(activity), R.layout.loginscreen, null, false);
//        setContentView(mBinding.getRoot());

        initControl();
        initDailogHeight();
        initlayouts();
        initTypeFace();
        initClickListener();

    }

    @Override
    public void initControl() {
        appTypeface = new AppTypeface(activity);
        countryCodeInfoController = new CountryCodeInfoController(activity);
        apiResponseHandler = new APIResponseHandler();
        loginRequestHandler = new RequestData();
    }


    @Override
    public void initDailogHeight() {
        // retrieve display dimensions
        Rect displayRectangle = new Rect();
        Window window = getWindow();
        if (window != null) {
            window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
            getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
        setCanceledOnTouchOutside(false);
    }

    @Override
    public void initlayouts() {

        /*fvid*/
        tvTitle = findViewById(R.id.tv_title);
        tvSignInTitle = findViewById(R.id.tv_sign_in_title);
        tvToConWith = findViewById(R.id.tv_to_con_with);
        tietCountryCode = findViewById(R.id.tiet_country_code);
        tietPhoneNumber = findViewById(R.id.tiet_phone_number);
        tietPassword = findViewById(R.id.tiet_password);
        tvToConWe = findViewById(R.id.tv_to_con_we);
        tvPrivacy = findViewById(R.id.tv_privacy);
        tvTerms = findViewById(R.id.tv_terms);
        tvCreateAccount = findViewById(R.id.tv_create_account);
        btnSignin = findViewById(R.id.btnSignin);
        btnCancel = findViewById(R.id.btnCancel);
        btnOk = findViewById(R.id.btnOk);
        ivClose = findViewById(R.id.iv_close);
        layoutSignInWith = findViewById(R.id.layout_sign_in_with);
        layoutConfirm = findViewById(R.id.layout_confirm);
        svLogin = findViewById(R.id.svLogin);

        /*set*/
        tvToConWith.setText(activity.getResources().getString(R.string.label_to_continue_with).replace("#", loginWithAppName));

        customizeEditTexts(tietCountryCode, tietPhoneNumber, tietPassword);

        tietCountryCode.setText(String.format("%s%s", "+", countryCodeInfoController.getUserCountryCode().getDialingCode()));
        if (tietCountryCode.getText() != null) {
            tietCountryCode.setSelection(tietCountryCode.getText().toString().trim().length());
        }

        tietCountryCode.setFilters(new InputFilter[]{new InputFilter.LengthFilter(COUNTRY_CODE_LIMIT)});
        tietPhoneNumber.setFilters(new InputFilter[]{new InputFilter.LengthFilter(PHONE_NUMBER_LIMIT)});

        tvCreateAccount.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                tvCreateAccount.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                bottomScrollHeight = tvCreateAccount.getHeight();
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) tvCreateAccount.getLayoutParams();
                bottomScrollHeight = bottomScrollHeight + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
            }
        });

        tvToConWe.setText(activity.getResources().getString(R.string.label_to_continue_we_cards_will).replace("#", loginWithAppName));
    }

    @Override
    public void initTypeFace() {

    }

    @Override
    public void initClickListener() {
        /*this method for user can not remove + from edit text*/
        tietCountryCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!(s.length() > 0)) {
                    tietCountryCode.setText("+");

                } else {
                    if (!s.toString().startsWith("+")) {
                        tietCountryCode.setText(String.format("%s%s", "+", s.toString().trim().replace("+", "")));
                    }
                }
                if (tietCountryCode.getText() != null) {
                    tietCountryCode.setSelection(tietCountryCode.getText().length());
                }
            }
        });

        tietCountryCode.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                tietPhoneNumber.requestFocus();
                return true;
            }
            return false;
        });


        tietPassword.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_GO) {
                btnSignin.performClick();
                return true;
            }
            return false;
        });


        btnSignin.setOnClickListener(v -> {
            if (validation()) {
                APICall();
            }

        });

        btnCancel.setOnClickListener(v -> dismiss());

        btnOk.setOnClickListener(v -> {
            loginRequestHandler.loginCompleted(activity, apiResult);
            dismiss();

        });

        ivClose.setOnClickListener(v -> dismiss());

        tvCreateAccount.setOnClickListener(v -> openAppInPlayStore());


        tietPhoneNumber.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                scrollUpDialog();
            }
        });

        tvPrivacy.setOnClickListener(v -> OpenCustomTabsIntent.openCustomTabsIntent(activity, AppContacts.URL_PRIVACY));
        tvTerms.setOnClickListener(v -> OpenCustomTabsIntent.openCustomTabsIntent(activity, AppContacts.URL_TERMS));
    }

    @Override
    public void APICall() {
        hideSoftInput();
        showProgress();

        RestClient.GitApiInterface service = RestClient.getClient();

        LoginRequest loginRequest = loginRequestHandler.getRequestData();
        if (tietCountryCode.getText() != null) {
            loginRequest.setCountry_code(tietCountryCode.getText().toString().trim().replace("+", ""));
        }
        if (tietPassword.getText() != null) {
            loginRequest.setPassword(tietPassword.getText().toString().trim());
        }
        if (tietPhoneNumber.getText() != null) {
            loginRequest.setPhone_number(tietPhoneNumber.getText().toString().trim());
        }

        Call<ResponseBody> call = service.login(loginRequest);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    apiResult = apiResponseHandler.getResult(response);
                    apiResponseHandler.getData(activity, apiResult, (data, message) -> {
                        apiResult = data;

                        // sagar : 13/12/18 Change. No need to confirm twice.
                        /*layoutSignInWith.setVisibility(View.GONE);
                        layoutConfirm.setVisibility(View.VISIBLE);
                        tvTitle.setText(activity.getResources().getString(R.string.confirmlogin));*/

                        loginRequestHandler.loginCompleted(activity, apiResult);
                        dismiss();

                    });
                } else {
                    showErrorHandler(activity, AppContacts.CONTACT_US);
                }

                hideProgress();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(Tag, t.getMessage());
            }
        });
    }

    @Override
    public void hideSoftInput() {
        InputMethodManager inputManager =
                (InputMethodManager) activity.
                        getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager != null) {
            inputManager.hideSoftInputFromWindow(
                    getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public void openAppInPlayStore() {
        if (appInstalledOrNot.appInstalledOrNot(wecardsAppPackage, activity)) {
            /*
             * if application  install that time this message call
             */
            Intent loginWithWecards = new Intent(loginActvity);
            loginWithWecards.putExtra(API_KEY, loginWithWecardKey);
            loginWithWecards.putExtra(PACKAGENAME, loginWithAppPackageName);
            activity.startActivity(loginWithWecards);
        } else {
            /*
             * if application  not install that time this message call
             */
            activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + wecardsAppPackage)));
        }
    }

    @Override
    public void showProgress() {
        if (progressDialogCustom == null) {
            progressDialogCustom = new DialogProgress(activity);
        }
        progressDialogCustom.show();
    }

    @Override
    public void hideProgress() {
        if (progressDialogCustom != null) {
            progressDialogCustom.dismiss();
        }
    }

    @Override
    public boolean validation() {
        if (!isNetworkAvailable(activity)) {
            error(activity.getResources().getString(R.string.noInternetConnection));
            return false;
        } else if (Validate.isEmpty(tietCountryCode.getText().toString().replace("+", "")) &&
                Validate.isEmpty(tietPhoneNumber.getText().toString()) &&
                Validate.isEmpty(tietPassword.getText().toString())) {
            error(activity.getResources().getString(R.string.enterRequiredInformation));
            return false;
        } else if (Validate.isEmpty(tietCountryCode.getText().toString().replace("+", ""))) {
            error(activity.getResources().getString(R.string.enterCoutryCode));
            return false;
        } else if (Validate.isEmpty(tietPhoneNumber.getText().toString())) {
            error(activity.getResources().getString(R.string.enterPhoneNumber));
            return false;
        } else if (Validate.isEmpty(tietPassword.getText().toString())) {
            error(activity.getResources().getString(R.string.enterPassword));
            return false;
        }
        return true;
    }

    @Override
    public void error(String message) {
//        showErrorHandler(activity, message);
        showAlertDialog(message);
    }

    @Override
    public void scrollUpDialog() {
        /*svLogin.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                svLogin.post(new Runnable() {
                    public void run() {
                        ObjectAnimator scrollup = ObjectAnimator.ofInt(svLogin, "scrollY", (svLogin.getBottom() - bottomScrollHeight));
                        scrollup.setDuration(1000);
                        scrollup.start();
                    }
                });
            }
        });*/
    }

    private void customizeEditTexts(TextInputEditText... ets) {
        if (ets != null && ets.length > 0) {
            for (TextInputEditText et : ets) {
                et.setTypeface(appTypeface.getRegularFont());
                et.setBackground(ContextCompat.getDrawable(activity, R.drawable.til_bottomline));
            }
        }
    }

    private void showAlertDialog(String title) {
        android.app.AlertDialog.Builder alertDialog = new android.app.AlertDialog.Builder(activity);

        // Typeface on message
        SpannableString spannableString = new SpannableString(title);
        CalligraphyTypefaceSpan typefaceSpan = new CalligraphyTypefaceSpan(TypefaceUtils.getInstance().getRegularTypeface(activity));
        spannableString.setSpan(typefaceSpan, 0, title.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Setting Dialog Title
        alertDialog.setMessage(spannableString);

        alertDialog.setPositiveButton("OK", (dialog, which) -> dialog.cancel());

        // Showing Alert Message
        alertDialog.show();
    }

}
