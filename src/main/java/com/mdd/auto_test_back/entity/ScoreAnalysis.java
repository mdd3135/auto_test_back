package com.mdd.auto_test_back.entity;

public class ScoreAnalysis {
    int homeworkId;
    String homeworkName;
    int userId;
    String userName;
    String createTime;
    int count;
    float gainedScore;
    float totalScore;
    int isComplete;

    public ScoreAnalysis(int homeworkId, String homeworkName, int userId, String userName, String createTime, int count,
            float gainedScore, float totalScore, int isComplete) {
        this.homeworkId = homeworkId;
        this.homeworkName = homeworkName;
        this.userId = userId;
        this.userName = userName;
        this.createTime = createTime;
        this.count = count;
        this.gainedScore = gainedScore;
        this.totalScore = totalScore;
        this.isComplete = isComplete;
    }

    public int getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(int homeworkId) {
        this.homeworkId = homeworkId;
    }

    public String getHomeworkName() {
        return homeworkName;
    }

    public void setHomeworkName(String homeworkName) {
        this.homeworkName = homeworkName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public float getGainedScore() {
        return gainedScore;
    }

    public void setGainedScore(float gainedScore) {
        this.gainedScore = gainedScore;
    }

    public float getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(float totalScore) {
        this.totalScore = totalScore;
    }

    public int getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(int isComplete) {
        this.isComplete = isComplete;
    }

}