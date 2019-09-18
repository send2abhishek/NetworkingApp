package com.attra.networkingapp.Models;

public class SampleModel {

    public int userId;
    public int id;
    public String title;
    public String body;

    public SampleModel(int userId, int id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}


