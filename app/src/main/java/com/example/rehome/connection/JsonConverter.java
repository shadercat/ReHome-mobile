package com.example.rehome.connection;

import android.util.Log;

import com.example.rehome.models.Device;
import com.example.rehome.models.DeviceInfo;
import com.example.rehome.models.ResourceGroup;
import com.example.rehome.models.Trigger;
import com.example.rehome.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

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

    public static User UserData(JSONObject object) {
        User user = new User();
        try {
            JSONObject data = object.getJSONObject("data");
            user.setName(data.getString("name"));
            user.setEmail(data.getString("email"));
            user.setId(data.getString("_id"));
            user.setDate(parseDate(data.getString("createdAt")));
        } catch (JSONException e) {
            Log.e(DEV_ERR, "JSON parse error: ", e);
        }
        return user;
    }

    public static DeviceInfo DeviceInfoData(JSONObject object) {
        DeviceInfo deviceInfo = new DeviceInfo();
        try {
            JSONObject data = object.getJSONObject("data");
            deviceInfo.setName(data.getString("deviceName"));
            deviceInfo.setCode(data.getString("deviceCode"));

            JSONObject status = data.getJSONObject("deviceStatus");
            deviceInfo.setState(status.getString("state"));
            deviceInfo.setStatus(status.getString("status"));

            JSONObject type = data.getJSONObject("deviceType");
            deviceInfo.setType(type.getString("name"));
            deviceInfo.setTypeCode(type.getString("code"));

            JSONArray triggers = type.getJSONArray("triggers");
            JSONObject trigger;
            for (int i = 0; i < triggers.length(); i++) {
                trigger = triggers.getJSONObject(i);
                String name = trigger.getString("name");
                String code = trigger.getString("code");
                String description = trigger.getString("description");
                deviceInfo.triggers.add(new Trigger(name, code, description));
            }

            deviceInfo.setCreatedAt(parseDate(data.getString("createdAt")));
        } catch (JSONException e) {
            Log.e(DEV_ERR, "JSON parse error: ", e);
        }
        return deviceInfo;
    }

    private static String parseDate(String mongoDate) {
        String stringDate = "unknown";
        try {
            SimpleDateFormat mongodt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
            mongodt.setTimeZone(TimeZone.getTimeZone("UTC"));
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            stringDate = date.format(mongodt.parse(mongoDate));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringDate;
    }

}
