package com.jarvis.assistant.service.ai;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jarvis.assistant.dto.SpotifyCommand;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class SpotifyAiService {

    private final ChatClient chatClient;
    private final ObjectMapper mapper = new ObjectMapper();

    public SpotifyAiService(ChatClient.Builder builder) {

        this.chatClient = builder.build();
    }

    public SpotifyCommand processCommand(String userMessage) {

        String prompt = """
                You are a Spotify AI assistant.

                Your task is to analyze the command
                and return ONLY raw JSON.

                Rules:
                1. Do NOT explain anything.
                2. Do NOT add markdown.
                3. Do NOT add ```json.
                4. Return ONLY valid JSON.
                5. songName must NEVER be null.

                Valid intents:
                - PLAY_SONG
                - UNKNOWN

                Example:

                Input:
                play believer on spotify

                Output:
                {
                  "intent":"PLAY_SONG",
                  "target":"spotify",
                  "songName":"Believer",
                  "response":"Playing Believer on Spotify"
                }

                Input:
                play faded

                Output:
                {
                  "intent":"PLAY_SONG",
                  "target":"spotify",
                  "songName":"Faded",
                  "response":"Playing Faded on Spotify"
                }

                Analyze this command:
                """ + userMessage;

        try {

            String aiResponse = chatClient.prompt()
                    .user(prompt)
                    .call()
                    .content();

            aiResponse = aiResponse
                    .replace("```json", "")
                    .replace("```", "")
                    .trim();

            System.out.println(aiResponse);

            return mapper.readValue(aiResponse, SpotifyCommand.class);

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }
}