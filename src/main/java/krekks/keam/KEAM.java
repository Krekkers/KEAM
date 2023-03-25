package krekks.keam;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public final class KEAM extends JavaPlugin {

    PluginManager pluginManager = Bukkit.getPluginManager();
    public static Config config;
    public Plugin PLUGIN;
    @Override
    public void onEnable() {
        // Plugin startup logic
        PLUGIN = this;
        PLUGIN.saveDefaultConfig();
        config = new Config(PLUGIN.getConfig());

        //test

        //runs every once in a while to send the ingame messages!
        new BukkitRunnable() {
            int prevRan = -1; // previous roll
            int random; // random number
            @Override
            public void run() {
                random = randomIntFromListSize(config.Messages); // if the random is the same as prevRan than it will have to reroll. So a while loop fixes that issue
                if(config.Messages.size() >= 2)
                    prevRan = random;
                while(prevRan == random){
                    random = randomIntFromListSize(config.Messages);
                }
                Message pickedMessage = config.Messages.get(random);
                for(Player p : Bukkit.getOnlinePlayers()){
                    p.spigot().sendMessage(pickedMessage.getTextComponent());
                    if(pickedMessage.usesTitle())
                        p.sendTitle(pickedMessage.getTitle(), "", 10,50, 10);
                }
            }
        }.runTaskTimer(PLUGIN, 200, config.interval);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static int randomIntFromListSize(List<?> l){
        return (int) (Math.random() * l.size());
    }

}
