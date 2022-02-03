package me.bdx.nhplugin.events;

import me.bdx.nhplugin.files.ParseIntoJs;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatEvent implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public static void onPlayerChat(AsyncPlayerChatEvent event){
        ParseIntoJs.getInstance().JSParseEvent(event);
    }
}
