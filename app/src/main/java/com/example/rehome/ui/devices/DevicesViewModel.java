package com.example.rehome.ui.devices;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rehome.ConstantUrls;
import com.example.rehome.connection.HttpClient;
import com.example.rehome.connection.JsonConverter;
import com.example.rehome.connection.ResponseHandler;
import com.example.rehome.models.Device;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.util.List;

import cz.msebera.android.httpclient.Header;

public class DevicesViewModel extends ViewModel {

    private MutableLiveData<List<Device>> devices;


    public LiveData<List<Device>> getData() {
        if (devices == null) {
            devices = new MutableLiveData<>();
            loadData();
        }
        return devices;
    }

    private void loadData() {
        HttpClient.get(ConstantUrls.userDevices, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                if (ResponseHandler.IsSuccessed(response)) {
                    Log.d("DEV_POINT", "requested devices data success");
                    devices.postValue(JsonConverter.DeviceList(response));
                }
            }
        });
    }
}