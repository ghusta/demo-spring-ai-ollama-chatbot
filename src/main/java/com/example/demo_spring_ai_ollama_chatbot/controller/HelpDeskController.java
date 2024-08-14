package com.example.demo_spring_ai_ollama_chatbot.controller;

import com.example.demo_spring_ai_ollama_chatbot.model.HelpDeskRequest;
import com.example.demo_spring_ai_ollama_chatbot.model.HelpDeskResponse;
import com.example.demo_spring_ai_ollama_chatbot.service.HelpDeskChatbotAgentService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/helpdesk")
public class HelpDeskController {

    private final HelpDeskChatbotAgentService helpDeskChatbotAgentService;

    public HelpDeskController(HelpDeskChatbotAgentService helpDeskChatbotAgentService) {
        this.helpDeskChatbotAgentService = helpDeskChatbotAgentService;
    }

    @PostMapping("/chat")
    public ResponseEntity<HelpDeskResponse> chat(@RequestBody HelpDeskRequest helpDeskRequest) {
        var chatResponse = helpDeskChatbotAgentService.call(helpDeskRequest.getPromptMessage(), helpDeskRequest.getHistoryId());

        HttpHeaders headers = new HttpHeaders();
        if (helpDeskRequest.getHistoryId() != null && !helpDeskRequest.getHistoryId().isBlank()) {
            headers.add("X-History-Id", helpDeskRequest.getHistoryId());
        }
        return new ResponseEntity<>(new HelpDeskResponse(chatResponse), headers, HttpStatus.OK);
    }

}