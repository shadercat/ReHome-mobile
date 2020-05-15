package com.example.rehome.models;

import java.util.ArrayList;
import java.util.List;

public class DeviceInfo {
    private String status;
    private String state;
    private String name;
    private String code;
    private String type;
    private String typeCode;
    private String createdAt;
    public List<Trigger> triggers;


    public DeviceInfo() {
        this("=====", "=====", "=====", "=====", "=====", "=====", "=====");
    }

    public DeviceInfo(String name, String code, String status, String state, String type, String typeCode, String createdAt) {
        this.name = name;
        this.code = code;
        this.state = state;
        this.status = status;
        this.type = type;
        this.typeCode = typeCode;
        this.createdAt = createdAt;
        this.triggers = new ArrayList<>();
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
