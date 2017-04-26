package com.CCS.LoginWithWeCards.API.Handler;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.CCS.LoginWithWeCards.Utils.AppContacts;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import okhttp3.ResponseBody;
import retrofit2.Response;

import static com.CCS.LoginWithWeCards.API.jsonKeys.DATA;
import static com.CCS.LoginWithWeCards.API.jsonKeys.MESSAGE;
import static com.CCS.LoginWithWeCards.API.jsonKeys.RESULT;
import static com.CCS.LoginWithWeCards.API.jsonKeys.STATUS;
import static com.CCS.LoginWithWeCards.API.jsonKeys.SUCCESS;


/**
 * Created by mauliksantoki on 26/4/17.
 */

public class APIResponseHandler extends APIResponse {


    private BufferedReader bufferedReader;
    private StringBuilder stringBuilder;
    private String line;

    private String apiResult = "";
    private String apiResultMessage = "";

    @Override
    public String getResult(Response<ResponseBody> reader) {

        bufferedReader = new BufferedReader(new InputStreamReader(reader.body().byteStream()));
        stringBuilder = new StringBuilder();

        try {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }

    @Override
    public void getData(Activity activity, String result, DataHandler handler) {
        try {
            JSONObject jsonObject = new JSONObject(result).optJSONObject(RESULT);
            apiResultMessage = jsonObject.optString(MESSAGE);
            if (jsonObject.optString(STATUS).equalsIgnoreCase(SUCCESS)) {
                apiResult = jsonObject.getJSONObject(DATA).toString();
                handler.getData(apiResult, apiResultMessage);
            } else {
                Toast.makeText(activity, apiResultMessage,
                        Toast.LENGTH_LONG).show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(activity, "please try again later...",
                    Toast.LENGTH_LONG).show();
            Log.e(AppContacts.Tag, e.getMessage());
        }
    }


}
