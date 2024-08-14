package com.example.demo_spring_ai_ollama_chatbot.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.autoconfigure.ollama.OllamaConnectionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/ollama")
public class OllamaApiController {

    private static final Logger log = LoggerFactory.getLogger(OllamaApiController.class);

    private final RestClient ollamaRestClient;

    public OllamaApiController(OllamaConnectionDetails ollamaConnectionDetails) {
        log.debug("Ollama base url: {}", ollamaConnectionDetails.getBaseUrl());
        ollamaRestClient = RestClient.builder()
                .baseUrl(ollamaConnectionDetails.getBaseUrl())
                .build();
    }

    /**
     * Equiv. of <code>ollama -v</code>
     */
    @GetMapping("/version")
    public OllamaVersion ollamaVersion() {
        OllamaVersion ollamaVersion = ollamaRestClient.get()
                .uri("/api/version")
                .retrieve()
                .body(OllamaVersion.class);
        if (ollamaVersion != null) {
            return ollamaVersion;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Equiv. of <code>ollama list</code>
     */
    @GetMapping("/models")
    public Models modelsList() {
        return ollamaRestClient.get()
                .uri("/api/tags")
                .retrieve()
                .body(Models.class);
    }

    public record OllamaVersion(
            String version) {
    }

    // TODO: See : mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

    public record ModelDetails(
            @JsonProperty("parent_model") String parentModel,
            String format,
            String family,
            List<String> families,
            @JsonProperty("parameter_size") String parameterSize,
            @JsonProperty("quantization_level") String quantizationLevel) {
    }

    public record Model(
            String name,
            String model,
            @JsonProperty("modified_at") String modifiedAt,
            long size,
            String digest,
            ModelDetails details) {
    }

    public record Models(
            List<Model> models) {
    }

}
