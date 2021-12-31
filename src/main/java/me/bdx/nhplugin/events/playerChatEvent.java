package me.bdx.nhplugin.events;

import me.bdx.managerapi.Managerapi;
import me.bdx.managerapi.customEvents.GlobalChatEvent;
import me.bdx.nhplugin.Nhplugin;
import me.bdx.nhplugin.files.parseIntoJs;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.CommandMap;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.openjdk.nashorn.api.scripting.NashornScriptEngineFactory;

import javax.script.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class playerChatEvent implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public static void onPlayerChat(AsyncPlayerChatEvent event){
        parseIntoJs.JSParseEvent(event);
    }
}
