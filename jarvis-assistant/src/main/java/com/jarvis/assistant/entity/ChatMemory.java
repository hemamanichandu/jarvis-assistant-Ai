package com.jarvis.assistant.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ChatMemory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userMessage;

    private String aiResponse;

    private LocalDateTime createdAt;

    public ChatMemory() {
    }

    public ChatMemory(String userMessage,
                      String aiResponse,
                      LocalDateTime createdAt) {

        this.userMessage = userMessage;
        this.aiResponse = aiResponse;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public String getAiResponse() {
        return aiResponse;
    }

    public void setAiResponse(String aiResponse) {
        this.aiResponse = aiResponse;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}