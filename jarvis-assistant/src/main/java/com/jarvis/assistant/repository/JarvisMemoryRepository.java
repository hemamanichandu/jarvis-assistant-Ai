package com.jarvis.assistant.repository;

import com.jarvis.assistant.entity.ChatMemory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JarvisMemoryRepository
        extends JpaRepository<ChatMemory, Long> {

    List<ChatMemory> findTop5ByOrderByCreatedAtDesc();
}