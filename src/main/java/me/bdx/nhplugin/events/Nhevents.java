package me.bdx.nhplugin.events;

import me.bdx.nhplugin.Nhplugin;
import me.bdx.nhplugin.files.ParseIntoJs;
import org.bukkit.Bukkit;
import org.bukkit.event.*;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.event.server.TabCompleteEvent;
import org.bukkit.plugin.IllegalPluginAccessException;
import org.bukkit.plugin.RegisteredListener;
import org.bukkit.plugin.java.JavaPlugin;

import javax.script.ScriptException;

public class Nhevents implements Listener {

    public Nhevents(){

        try{
            RegisteredListener registeredListener = new RegisteredListener(this,
                    (listener, event) -> Nhevents.onEvent(event),
                    EventPriority.NORMAL, Nhplugin.getInstance(), false);
            for (HandlerList handler : HandlerList.getHandlerLists()){
                handler.register(registeredListener);
            }
        }catch (IllegalPluginAccessException e){
            Bukkit.getConsoleSender().sendMessage(e.getMessage());
        }

    }

    /**
     * The main event handler. This handles all events registered in JS
     * @param event The event
     * @param <T> The class of the Event
     */
    public static <T extends Event> void onEvent(T event){
        if(JavaPlugin.getPlugin(Nhplugin.class).listenedEvents.contains(event.getEventName())){
            ParseIntoJs.getInstance().JSParseEvent(event);
        }
    }

    @EventHandler
    public static void scriptLoadEvent(ScriptLoadEvent event){
        ParseIntoJs.getInstance().JSParseEvent(event);
    }

    @EventHandler
    public static void PlayerCommandPreprocessEvent(PlayerCommandPreprocessEvent event){
        try {
            ParseIntoJs.getInstance().getInvocable().invokeFunction("OnPlayerCommandProcess", event,
                    Nhplugin.chat, Nhplugin.getInstance());
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            int i =0;
        }
    }

    @EventHandler
    public static void ServerCommandEvent(ServerCommandEvent event){

        try {
            ParseIntoJs.getInstance().getInvocable().invokeFunction("OnServerCommandEvent", event, Nhplugin.chat,
                    Nhplugin.getInstance());
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            int i =0;
        }
    }

    @EventHandler
    public static void TabCompleteEvent(TabCompleteEvent event){
        ParseIntoJs.getInstance().JSParseEvent(event);
    }

}


