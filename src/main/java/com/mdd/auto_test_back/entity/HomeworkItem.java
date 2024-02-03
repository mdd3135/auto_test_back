package com.mdd.auto_test_back.entity;

public class HomeworkItem {
    int id;
    int homeworkId;
    int itemId;

    public HomeworkItem(int id, int homeworkId, int itemId) {
        this.id = id;
        this.homeworkId = homeworkId;
        this.itemId = itemId;
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

    public int getitemId() {
        return itemId;
    }

    public void setitemId(int itemId) {
        this.itemId = itemId;
    }
}
