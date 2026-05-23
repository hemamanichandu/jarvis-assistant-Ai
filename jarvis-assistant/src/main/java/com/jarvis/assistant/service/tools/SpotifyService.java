package com.jarvis.assistant.service.tools;

import com.jarvis.assistant.dto.JarvisCommand;
import com.jarvis.assistant.dto.SpotifyCommand;
import org.springframework.stereotype.Service;

@Service
public class SpotifyService {



    public String playSong(SpotifyCommand command) {

        try {

            String songName = command.getSongName();

            Runtime.getRuntime().exec(
//                    "cmd /c start https://open.spotify.com/search/" + songName
                    "cmd /c start spotify:search:" + songName
            );

            return "Playing " + songName;

        } catch (Exception e) {

            e.printStackTrace();

            return "Failed to play song";
        }
    }
}