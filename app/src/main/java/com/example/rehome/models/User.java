package com.example.rehome.models;

public class User {
    private String name;
    private String email;
    private String id;
    private String date;

    public User() {
        this("unknown", "unknown", "unknown", "unknown");
    }

    public User(String name, String email, String id) {
        this(name, email, id, "unknown");
    }

    public User(String name, String email, String id, String date) {
        this.name = name;
        this.email = email;
        this.id = id;
        this.date = date;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
