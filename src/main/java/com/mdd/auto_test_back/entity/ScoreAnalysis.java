package com.mdd.auto_test_back.entity;

public class ScoreAnalysis {
    int homeworkId;
    String homeworkName;
    String createTime;
    int count;
    float gainedSocre;
    float totalScore;

    public ScoreAnalysis(int homeworkId, String homeworkName, String createTime, int count, float gainedSocre,
            float totalScore) {
        this.homeworkId = homeworkId;
        this.homeworkName = homeworkName;
        this.createTime = createTime;
        this.count = count;
        this.gainedSocre = gainedSocre;
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

    public float getGainedSocre() {
        return gainedSocre;
    }

    public void setGainedSocre(float gainedSocre) {
        this.gainedSocre = gainedSocre;
    }

    public float getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(float totalScore) {
        this.totalScore = totalScore;
    }

}