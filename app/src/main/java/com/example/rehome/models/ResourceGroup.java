package com.example.rehome.models;

public class ResourceGroup {
    private String name;
    private String description;
    private String id;
    private String createdAt;

    public ResourceGroup() {
        this("======", "======", "======");
        createdAt = "=====";
    }

    public ResourceGroup(String name, String description, String id) {
        this.name = name;
        this.description = description;
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
