package com.mdd.auto_test_back.entity;

public class Submit {
    int id;
    Integer homeworkId;
    int userId;
    Integer itemId;
    int type;

    public Submit(int id, Integer homeworkId, int userId, Integer itemId, int type) {
        this.id = id;
        this.homeworkId = homeworkId;
        this.userId = userId;
        this.itemId = itemId;
        this.type = type;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
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

    public Integer getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(Integer homeworkId) {
        this.homeworkId = homeworkId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
