package com.mdd.auto_test_back.entity;

public class ScoreAnalysis {
    int homeworkId;
    String homeworkName;
    String createTime;
    int count;
    float gainedScore;
    float totalScore;

    public ScoreAnalysis(int homeworkId, String homeworkName, String createTime, int count, float gainedScore,
            float totalScore) {
        this.homeworkId = homeworkId;
        this.homeworkName = homeworkName;
        this.createTime = createTime;
        this.count = count;
        this.gainedScore = gainedScore;
        this.totalScore = totalScore;
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

}