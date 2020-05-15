package com.example.rehome.ui.user;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rehome.ConstantUrls;
import com.example.rehome.connection.HttpClient;
import com.example.rehome.connection.JsonConverter;
import com.example.rehome.connection.ResponseHandler;
import com.example.rehome.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class UserViewModel extends ViewModel {

    private MutableLiveData<User> user;

    public LiveData<User> getData() {
        if (user == null) {
            user = new MutableLiveData<>();
            loadData();
        }
        return user;
    }

    private void loadData() {
        HttpClient.get(ConstantUrls.userData, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                if (ResponseHandler.IsSuccessed(response)) {
                    Log.d("DEV_POINT", "requested user data success");
                    user.postValue(JsonConverter.UserData(response));
                }
            }
        });
    }
}
