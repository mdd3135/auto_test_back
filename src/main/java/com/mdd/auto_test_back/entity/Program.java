package com.mdd.auto_test_back.entity;

public class Program {
    int id;
    String content;
    String answer;
    String analysis;
    String input;
    String output;
    String language;
    int itemId;

    public Program(int id, String content, String answer, String analysis, String input, String output, String language,
            int itemId) {
        this.id = id;
        this.content = content;
        this.answer = answer;
        this.analysis = analysis;
        this.input = input;
        this.output = output;
        this.language = language;
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

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

}
