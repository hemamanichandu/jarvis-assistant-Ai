package com.jarvis.assistant.service.ai;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jarvis.assistant.dto.JarvisCommand;
import com.jarvis.assistant.service.memory.MemoryService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class JarvisAiService {

    private final ChatClient chatClient;
    private final ObjectMapper mapper;
//    private final ObjectMapper mapper = new ObjectMapper();
    private final MemoryService memoryService;


    public JarvisAiService(ChatClient chatClient,
                           ObjectMapper mapper,
                           MemoryService memoryService) {

        this.chatClient = chatClient;
        this.mapper = mapper;
        this.memoryService = memoryService;
    }

    public JarvisCommand processCommand(String userMessage) {


        StringBuilder memoryContext = new StringBuilder();

        memoryService.getRecentMemory()
                .forEach(memory -> {

                    memoryContext.append("""
                    
                    User: %s
                    Assistant: %s
                    """
                            .formatted(
                                    memory.getUserMessage(),
                                    memory.getAiResponse()
                            ));
                });


        String prompt = """
        You are a desktop AI assistant.

        Your task is to analyze the command and return ONLY JSON.

        Rules:
        1. Do not explain anything.
        2. Do not add markdown.
        3. Do not add extra text.
        4. target must NEVER be null.
        5. Extract app names correctly.

        Valid intents:
        - OPEN_APP
        - SEARCH_WEB
        - CREATE_FILE
        - UNKNOWN

        Examples:

        Input: open chrome

        Output:
        {
          "intent":"OPEN_APP",
          "target":"chrome",
          "response":"Opening Chrome"
        }

        Input: open vscode

        Output:
        {
          "intent":"OPEN_APP",
          "target":"vscode",
          "response":"Opening VS Code"
        }

        Input: search spring ai tutorials

        Output:
        {
          "intent":"SEARCH_WEB",
          "target":"spring ai tutorials",
          "response":"Searching Spring AI tutorials"
        }

        Previous conversation history:

        """ + memoryContext + """

        Current command:

        """ + userMessage;

        try {

            String aiResponse = chatClient.prompt()
                    .user(prompt)
                    .call()
                    .content();

            System.out.println(aiResponse);

            return mapper.readValue(aiResponse, JarvisCommand.class);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}