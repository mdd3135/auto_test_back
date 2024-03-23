package com.mdd.auto_test_back.entity.Response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatResponse {
    List<Choices> choices;

    public ChatResponse() {
    }

    public ChatResponse(List<Choices> choices) {
        this.choices = choices;
    }

    public List<Choices> getChoices() {
        return choices;
    }

    public void setChoices(List<Choices> choices) {
        this.choices = choices;
    }

}
