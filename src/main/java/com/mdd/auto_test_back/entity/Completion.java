package com.mdd.auto_test_back.entity;

public class Completion {
    private int id;
    private String content;
    private String answer;
    private String analysis;
    private int itemId;

    public Completion(int id, String content, String answer, String analysis, int itemId) {
        this.id = id;
        this.content = content;
        this.answer = answer;
        this.analysis = analysis;
        this.itemId = itemId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

}
