package com.example.rehome.connection;

import com.loopj.android.http.RequestParams;

public class RequestParamsFactory {
    public static RequestParams loginParams(String email, String password){
        RequestParams params = new RequestParams();
        params.add("email", email);
        params.put("password", password);
        return params;
    }
}
