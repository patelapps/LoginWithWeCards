package com.CCS.LoginWithWeCards.API;


import com.CCS.LoginWithWeCards.Model.LoginRequest;
import com.CCS.LoginWithWeCards.Model.LogoutRequest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;


public class RestClient {

    private static GitApiInterface gitApiInterface;

    public static GitApiInterface getClient() {
        if (gitApiInterface == null) {
            Retrofit client = new Retrofit.Builder()
                    .baseUrl(urls.LIVE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


            gitApiInterface = client.create(GitApiInterface.class);
        }

        return gitApiInterface;
    }

    public interface GitApiInterface {
        @POST("?r=" + urls.API_VERSION + "open/user/login")
        Call<ResponseBody> login(@Body LoginRequest body);

        @POST("?r=" + urls.API_VERSION + "open/user/logout")
        Call<ResponseBody> logout(@Body LogoutRequest body);
    }
}
