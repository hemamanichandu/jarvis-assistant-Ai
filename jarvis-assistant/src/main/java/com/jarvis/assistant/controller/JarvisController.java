package com.jarvis.assistant.controller;

import com.jarvis.assistant.dto.JarvisCommand;
import com.jarvis.assistant.entity.ChatMemory;
import com.jarvis.assistant.service.ai.JarvisAiService;
import com.jarvis.assistant.service.memory.MemoryService;
import com.jarvis.assistant.service.tools.ToolExecutionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jarvis")
@CrossOrigin("*")
public class JarvisController {

    private final JarvisAiService aiService;
    private final ToolExecutionService toolService;
    private final MemoryService memoryService;

    public JarvisController(JarvisAiService aiService,
                            ToolExecutionService toolService,
                            MemoryService memoryService) {

        this.aiService = aiService;
        this.toolService = toolService;
        this.memoryService = memoryService;
    }

    @GetMapping("/command")
    public JarvisCommand command(@RequestParam String message) {

        JarvisCommand command = aiService.processCommand(message);

        toolService.execute(command);

        memoryService.save(message, command.getResponse());

        return command;
    }

    @GetMapping("/history")
    public List<ChatMemory> history() {
        return memoryService.getHistory();
    }
}