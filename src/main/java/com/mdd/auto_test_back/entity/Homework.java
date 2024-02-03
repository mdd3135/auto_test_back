package com.mdd.auto_test_back.entity;

public class Homework {
    int id;
    String createTime;
    String startTime;
    String deadline;
    String homeworkName;
    int count;

    public Homework(int id, String createTime, String startTime, String deadline, String homeworkName,
            int count) {
        this.id = id;
        this.createTime = createTime;
        this.startTime = startTime;
        this.deadline = deadline;
        this.homeworkName = homeworkName;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getHomeworkName() {
        return homeworkName;
    }

    public void setHomeworkName(String homeworkName) {
        this.homeworkName = homeworkName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}