package com.jarvis.assistant.service.memory;

import com.jarvis.assistant.entity.ChatMemory;
import com.jarvis.assistant.repository.JarvisMemoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MemoryService {

    private final JarvisMemoryRepository repository;

    public MemoryService(JarvisMemoryRepository repository)
    {
        this.repository = repository;
    }

    public void save(String userMessage,
                     String aiResponse)
    {

        ChatMemory memory = new ChatMemory(
                userMessage,
                aiResponse,
                LocalDateTime.now()
        );

        repository.save(memory);
    }

    public List<ChatMemory> getHistory() {
        return repository.findAll();
    }

    public List<ChatMemory> getRecentMemory() {
        return repository.findTop5ByOrderByCreatedAtDesc();
    }


}