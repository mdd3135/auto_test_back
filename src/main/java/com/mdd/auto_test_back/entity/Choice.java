package com.mdd.auto_test_back.entity;

public class Choice {
    int id;
    String content;
    String options;
    String answer;
    String analysis;
    int isMultiple;
    int itemId;

    public Choice(int id, String content, String options, String answer, String analysis, int isMultiple, int itemId) {
        this.id = id;
        this.content = content;
        this.options = options;
        this.answer = answer;
        this.analysis = analysis;
        this.isMultiple = isMultiple;
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

    public String getOptions() {
        return options;
    }

    public void setOption(String options) {
        this.options = options;
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

    public int getIsMultiple() {
        return isMultiple;
    }

    public void setIsMultiple(int isMultiple) {
        this.isMultiple = isMultiple;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

}
