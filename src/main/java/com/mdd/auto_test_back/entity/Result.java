package com.mdd.auto_test_back.entity;

public class Result {
    int id;
    int submitId;
    int itemId;
    float score;
    String feedback;

    public Result(int id, int submitId, int itemId, float score, String feedback) {
        this.id = id;
        this.submitId = submitId;
        this.itemId = itemId;
        this.score = score;
        this.feedback = feedback;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSubmitId() {
        return submitId;
    }

    public void setSubmitId(int submitId) {
        this.submitId = submitId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

}
