package com.example.rehome.ui.groups.info;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rehome.ConstantUrls;
import com.example.rehome.connection.HttpClient;
import com.example.rehome.connection.JsonConverter;
import com.example.rehome.connection.ResponseHandler;
import com.example.rehome.models.Device;
import com.example.rehome.models.ResourceGroup;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.util.List;

import cz.msebera.android.httpclient.Header;

public class GroupInfoViewModel extends ViewModel {

    private MutableLiveData<ResourceGroup> resourceGroup;
    private MutableLiveData<List<Device>> devices;
    private String id;

    public LiveData<ResourceGroup> getGroupData(String id) {
        this.id = id;
        if (resourceGroup == null) {
            resourceGroup = new MutableLiveData<>();
        }
        loadGroupData();
        return resourceGroup;
    }

    public LiveData<List<Device>> getDevicesList(String id) {
        this.id = id;
        if (devices == null) {
            devices = new MutableLiveData<>();
        }
        loadDevicesList();
        return devices;
    }

    private void loadGroupData() {
        HttpClient.get(ConstantUrls.getResGroup(this.id), null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                if (ResponseHandler.IsSuccessed(response)) {
                    resourceGroup.postValue(JsonConverter.ResourceGroupData(response));
                }
            }
        });
    }

    private void loadDevicesList() {
        HttpClient.get(ConstantUrls.getDevicesFromGroup(this.id), null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                if (ResponseHandler.IsSuccessed(response)) {
                    devices.postValue(JsonConverter.DevicesFromGroup(response));
                }
            }
        });
    }
}
