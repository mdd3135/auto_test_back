package com.mdd.auto_test_back.entity;

public class ItemBank {
    private int id;
    private int questionId;
    private String createTime;
    private int type;
    private float score;
    private String description;

    public ItemBank(int id, int questionId, String createTime, int type, float score, String description) {
        this.id = id;
        this.questionId = questionId;
        this.createTime = createTime;
        this.type = type;
        this.score = score;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
