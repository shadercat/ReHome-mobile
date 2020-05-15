package com.example.rehome.connection;

import android.util.Log;

import com.example.rehome.models.Device;
import com.example.rehome.models.ResourceGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonConverter {
    private static final String DEV_ERR = "DEV_ERR_JsonConverter";

    public static List<Device> DeviceList(JSONObject object) {
        List<Device> devices = new ArrayList<>();
        try {
            JSONArray array = object.getJSONArray("data");
            JSONObject obj;
            JSONObject deviceStatus;
            for (int i = 0; i < array.length(); i++) {
                obj = array.getJSONObject(i);
                String name = obj.getString("deviceName");
                String code = obj.getString("deviceCode");
                deviceStatus = obj.getJSONObject("deviceStatus");
                String state = deviceStatus.getString("state");
                String status = deviceStatus.getString("status");
                devices.add(new Device(name, code, status, state));
            }
        } catch (JSONException e) {
            Log.e(DEV_ERR, "JSON parse error: ", e);
        }
        return devices;
    }

    public static List<ResourceGroup> ResourceGroupList(JSONObject object) {
        List<ResourceGroup> resourceGroups = new ArrayList<>();
        try {
            JSONArray array = object.getJSONArray("data");
            JSONObject obj;
            for (int i = 0; i < array.length(); i++) {
                obj = array.getJSONObject(i);
                String name = obj.getString("name");
                String description = obj.getString("description");
                String id = obj.getString("_id");
                resourceGroups.add(new ResourceGroup(name, description, id));
            }
        } catch (JSONException e) {
            Log.e(DEV_ERR, "JSON parse error: ", e);
        }
        return resourceGroups;
    }
}
