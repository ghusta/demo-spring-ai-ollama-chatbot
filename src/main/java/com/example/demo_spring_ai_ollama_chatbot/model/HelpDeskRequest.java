package com.example.demo_spring_ai_ollama_chatbot.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record HelpDeskRequest(
        @JsonProperty("prompt_message") String promptMessage,
        @JsonProperty("history_id") String historyId) {
}
