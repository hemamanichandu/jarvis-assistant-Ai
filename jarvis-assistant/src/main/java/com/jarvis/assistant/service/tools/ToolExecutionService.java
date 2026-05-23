package com.jarvis.assistant.service.tools;

import com.jarvis.assistant.dto.JarvisCommand;
import org.springframework.stereotype.Service;

@Service
public class ToolExecutionService {

    public String execute(JarvisCommand command) {

        try
        {

            if ("OPEN_APP".equalsIgnoreCase(command.getIntent()))
            {

                return openApplication(command.getTarget());
            }


            return "Unknown command";

        }
        catch (Exception e) {

            return "Error executing command";
        }
    }




    private String openApplication(String app) throws Exception {

        switch (app.toLowerCase()) {

            case "chrome":
                Runtime.getRuntime().exec(
                        "chrome path"
                );
                //or google path
                Runtime.getRuntime().exec(
                        "google path"
                );
                return "Chrome opened";

            case "calc":
             
                Runtime.getRuntime().exec("calc");

                return "calculater opened";

            case "vscode":
                Runtime.getRuntime().exec(
                        "C:\\Users\\donka\\AppData\\Local\\Programs\\Microsoft VS Code\\Code.exe"
                );
                return "VS Code opened";

            case "spotify":

                Runtime.getRuntime().exec("spotify");

                return "Spotify opened";



            case "play_song":

                Runtime.getRuntime().exec(
                        "endpoint for an song"
                );

                return "Playing song";

            default:
                return "Application not supported";
        }
    }
}
