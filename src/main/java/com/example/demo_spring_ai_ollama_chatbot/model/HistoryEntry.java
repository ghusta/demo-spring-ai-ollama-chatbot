package com.example.demo_spring_ai_ollama_chatbot.model;

public class HistoryEntry {

    private String prompt;

    private String response;

    public HistoryEntry(String prompt, String response) {
        this.prompt = prompt;
        this.response = response;
    }

    @Override
    public String toString() {
        return String.format("""
                            `history_entry`:
                                `prompt`: %s
                
                                `response`: %s
                            -----------------
                
                """, prompt, response);
    }

}
