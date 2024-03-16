package com.mdd.auto_test_back.entity;

public class Collect {
    int id;
    int userId;
    int itemId;

    public Collect(int id, int userId, int itemId) {
        this.id = id;
        this.userId = userId;
        this.itemId = itemId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}
