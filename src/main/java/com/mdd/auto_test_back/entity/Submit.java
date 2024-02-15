package com.mdd.auto_test_back.entity;

public class Submit {
    int id;
    int homeworkId;
    int userId;
    int itemId;
    int type;

    public Submit(int id, int homeworkId, int userId, int itemId, int type) {
        this.id = id;
        this.homeworkId = homeworkId;
        this.userId = userId;
        this.itemId = itemId;
        this.type = type;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(int homeworkId) {
        this.homeworkId = homeworkId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
