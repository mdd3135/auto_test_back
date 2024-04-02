package com.mdd.auto_test_back.entity;

public class Discussion {
    int id;
    int homeworkId;
    int userId;
    String content;
    int isTop;

    public Discussion(int id, int homeworkId, int userId, String content, int isTop) {
        this.id = id;
        this.homeworkId = homeworkId;
        this.userId = userId;
        this.content = content;
        this.isTop = isTop;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIsTop() {
        return isTop;
    }

    public void setIsTop(int isTop) {
        this.isTop = isTop;
    }

}
