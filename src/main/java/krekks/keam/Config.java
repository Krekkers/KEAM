package krekks.keam;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Config {

    public ArrayList<Message> Messages = new ArrayList<>();
    public int interval;
    public Config(FileConfiguration config){
        interval = config.getInt("interval") * 20 * 60;

        // load messages
        List<?> rawMessages = config.getList("Messages");
        System.out.println(rawMessages);
        assert rawMessages != null;
        for (Object rawMessage : rawMessages) {
            LinkedHashMap<?, ?> messageMap = (LinkedHashMap<?, ?>) rawMessage;
            String message = (String) messageMap.get("message");
            String url = (String) messageMap.get("url");
            String title = "";
            if(messageMap.get("title") != null){
                title = (String) messageMap.get("title");
            }
            Messages.add(new Message(message, url, title));
        }
    }



}
