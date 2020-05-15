package com.example.rehome.ui.devices.info;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rehome.ConstantUrls;
import com.example.rehome.connection.HttpClient;
import com.example.rehome.connection.JsonConverter;
import com.example.rehome.connection.ResponseHandler;
import com.example.rehome.models.DeviceInfo;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class DeviceInfoViewModel extends ViewModel {

    private MutableLiveData<DeviceInfo> deviceInfo;
    private String deviceCode;

    public LiveData<DeviceInfo> getData(String deviceCode) {
        if (deviceInfo == null) {
            deviceInfo = new MutableLiveData<>();
        }
        this.deviceCode = deviceCode;
        deviceInfo.postValue(new DeviceInfo());
        loadData();
        return deviceInfo;
    }

    private void loadData() {
        HttpClient.get(ConstantUrls.getDeviceInfo(deviceCode), null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                if (ResponseHandler.IsSuccessed(response)) {
                    Log.d("DEV_POINT", "requested device info data success");
                    deviceInfo.postValue(JsonConverter.DeviceInfoData(response));
                }
            }
        });
    }
}
