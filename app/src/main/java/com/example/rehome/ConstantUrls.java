package com.example.rehome;

public class ConstantUrls {
    public static final String Main = "https://rehome-backend.azurewebsites.net";
    public static final String isAuthorized = "/user/authorized";
    public static final String login = "/user/login";
    public static final String logout = "/user/logout";
    public static final String register = "/user/create";
    public static final String userData = "/user/";
    public static final String userDevices = "/user/devices";
    public static final String userResGroups = "/user/resourcegroups";
    public static final String registerDevice = "/device/register";
    public static final String createResGroup = "/resourcegroup/create";
    public static final String getRecommendations = "/recommendations";


    public static String getDeviceInfo(String deviceCode) {
        return String.format("/device/%s", deviceCode);
    }

    public static String deleteDevice(String deviceCode) {
        return String.format("/device/%s/delete", deviceCode);
    }

    public static String getResGroup(String resId) {
        return String.format("/resourcegroup/%s", resId);
    }

    public static String getDevicesFromGroup(String resId) {
        return String.format("/resourcegroup/%s/devices", resId);
    }

    public static String deleteResGroup(String resId) {
        return String.format("/resourcegroup/%s/devices/delete", resId);
    }

    public static String addDeviceToResGroup(String resId) {
        return String.format("/resourcegroup/%s/devices/add", resId);
    }

    public static String deleteDeviceFromResGroup(String resId, String deviceCode) {
        return String.format("/resourcegroup/%s/devices/%s/delete", resId, deviceCode);
    }

    public static String deleteResourceGroup(String resId) {
        return String.format("/resourcegroup/%s/delete", resId);
    }
}
