package com.example.rehome.models;

public class Device {
    private String status;
    private String state;
    private String name;
    private String code;


    public Device() {

    }

    public Device(String name, String code, String status, String state) {
        this.name = name;
        this.code = code;
        this.state = state;
        this.status = status;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
