package com.example.rehome.connection;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class ResponseHandler {
    public static boolean IsSuccessed(JSONObject object){
        boolean result = false;
        try {
            result = object.getBoolean("success");
        } catch (JSONException e) {
            Log.e("DEV_ERR", "JSON parse error: ", e);
        }
        return result;
    }
}
