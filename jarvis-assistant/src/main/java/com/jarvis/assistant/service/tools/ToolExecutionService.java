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
                        "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe"
                );
                Runtime.getRuntime().exec(
                        "rundll32 url.dll,FileProtocolHandler https://google.com"
                );
                Runtime.getRuntime().exec("notepad");
                Runtime.getRuntime().exec("calc");
                /*Runtime.getRuntime().exec(
                        "C:\\Users\\donka\\Desktop\\resume.pdf"
                );
                Runtime.getRuntime().exec(
                        "explorer.exe C:\\Users\\donka\\Downloads"
                );
                Runtime.getRuntime().exec(
                        "cmd /c start chrome https://youtube.com"
                );*/
                return "Chrome opened";



            case "calc":
              /* *//* Runtime.getRuntime().exec(
                        "C:\\Users\\donka\\OneDrive\\Desktop\\Instagram.lnk"
                );*//*
                Runtime.getRuntime().exec(
                        "cmd /c start \"\" \"C:\\Users\\donka\\OneDrive\\Desktop\\Instagram.lnk\""
                );*/
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
                        "cmd /c start spotify:track:4cOdK2wGLETKBW3PvgPWqT"
                );

                return "Playing song";

            default:
                return "Application not supported";
        }
    }
}