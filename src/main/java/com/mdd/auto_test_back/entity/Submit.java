package com.mdd.auto_test_back.entity;

public class Submit {
    int id;
    int homeworkId;
    int userId;

    public Submit(int id, int homeworkId, int userId) {
        this.id = id;
        this.homeworkId = homeworkId;
        this.userId = userId;
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
