package krekks.keam;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;


public class Message {

    private String message;
    private String link; // optional
    private boolean usesTitle = false;
    private String title;

    public Message(String msg, String url, String title){
        this.message = msg;
        this.link = url;
        this.title = title;
        if(title != ""){
            this.usesTitle = true;
        }
    }


    public String getMessage() {
        return message;
    }
    public String getLink() {
        return link;
    }
    public String getTitle(){
        return title;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public void setLink(String link) {
        this.link = link;
    }
    public boolean usesTitle(){
        return this.usesTitle;
    }
    public TextComponent getTextComponent(){
        TextComponent text = new TextComponent(ChatColor.translateAlternateColorCodes('&', this.message));
        text.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, this.link));

        return text;
    }

}
