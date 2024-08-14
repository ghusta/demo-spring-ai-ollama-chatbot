package com.example.demo_spring_ai_ollama_chatbot.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HelpDeskRequest {

    @JsonProperty("prompt_message")
    private String promptMessage;

    @JsonProperty("history_id")
    private String historyId;

    public HelpDeskRequest() {
    }

    public String getPromptMessage() {
        return promptMessage;
    }

    public void setPromptMessage(String promptMessage) {
        this.promptMessage = promptMessage;
    }

    public String getHistoryId() {
        return historyId;
    }

    public void setHistoryId(String historyId) {
        this.historyId = historyId;
    }
}