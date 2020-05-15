package com.example.rehome.ui.groups;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rehome.ConstantUrls;
import com.example.rehome.connection.HttpClient;
import com.example.rehome.connection.JsonConverter;
import com.example.rehome.connection.ResponseHandler;
import com.example.rehome.models.ResourceGroup;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.util.List;

import cz.msebera.android.httpclient.Header;

public class GroupsViewModel extends ViewModel {

    private MutableLiveData<List<ResourceGroup>> resourceGroups;

    public LiveData<List<ResourceGroup>> getData() {
        if (resourceGroups == null) {
            resourceGroups = new MutableLiveData<>();
            loadData();
        }
        return resourceGroups;
    }

    private void loadData() {
        HttpClient.get(ConstantUrls.userResGroups, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                if (ResponseHandler.IsSuccessed(response)) {
                    Log.d("DEV_POINT", "requested resGroups data success");
                    resourceGroups.postValue(JsonConverter.ResourceGroupList(response));
                }
            }
        });
    }
}