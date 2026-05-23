package com.jarvis.assistant.controller;

import com.jarvis.assistant.dto.SpotifyCommand;
import com.jarvis.assistant.service.ai.SpotifyAiService;
import com.jarvis.assistant.service.tools.SpotifyService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spotify")
@CrossOrigin("*")
public class SpotifyController {

    private final SpotifyAiService aiService;
    private final SpotifyService spotifyService;

    public SpotifyController(SpotifyAiService aiService,
                             SpotifyService spotifyService) {

        this.aiService = aiService;
        this.spotifyService = spotifyService;
    }

    @GetMapping("/play")
//    http://localhost:8080/spotify/play?message=play believer on spotify
    public String playSong(@RequestParam String message)
    {

        SpotifyCommand command =
                aiService.processCommand(message);

        return spotifyService.playSong(command);
    }
}