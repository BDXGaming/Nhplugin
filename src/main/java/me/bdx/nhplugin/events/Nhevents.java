package me.bdx.nhplugin.events;

import me.bdx.managerapi.customEvents.AltChatChannelReceiveEvent;
import me.bdx.managerapi.customEvents.CustomPacketReceiveEvent;
import me.bdx.managerapi.customEvents.GlobalChatEvent;
import me.bdx.managerapi.customEvents.GlobalChatReceiveEvent;
import me.bdx.nhplugin.Nhplugin;
import me.bdx.nhplugin.files.ParseIntoJs;
import org.bukkit.Bukkit;
import org.bukkit.event.*;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.event.server.TabCompleteEvent;
import org.bukkit.plugin.IllegalPluginAccessException;
import org.bukkit.plugin.RegisteredListener;
import org.bukkit.plugin.java.JavaPlugin;

import javax.script.ScriptException;

public class Nhevents implements Listener {

    public Nhevents(){

        try{
            RegisteredListener registeredListener = new RegisteredListener(this, (listener, event) -> Nhevents.onEvent(event), EventPriority.NORMAL, Nhplugin.getInstance(), false);
            for (HandlerList handler : HandlerList.getHandlerLists()){
                //Bukkit.getConsoleSender().sendMessage(String.valueOf(handler.getRegisteredListeners()));
                handler.register(registeredListener);
            }
        }catch (IllegalPluginAccessException e){
            Bukkit.getConsoleSender().sendMessage(e.getMessage());
        }

    }

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
    public static void onPlayerJoin(PlayerJoinEvent event){
        ParseIntoJs.getInstance().JSParseEvent(event);
    }

    @EventHandler
    public static void onPlayerQuit(PlayerQuitEvent event){
        ParseIntoJs.getInstance().JSParseEvent(event);
    }

    @EventHandler
    public static void PlayerCommandPreprocessEvent(PlayerCommandPreprocessEvent event){
        try {
            ParseIntoJs.getInstance().getInvocable().invokeFunction("OnPlayerCommandProcess", event, Nhplugin.chat, Nhplugin.managerapi, Nhplugin.getInstance());
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            int i =0;
        }
    }

    @EventHandler
    public static void ServerCommandEvent(ServerCommandEvent event){

        try {
            ParseIntoJs.getInstance().getInvocable().invokeFunction("OnServerCommandEvent", event, Nhplugin.chat, Nhplugin.managerapi, Nhplugin.getInstance());
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

    @EventHandler
    public static void globalChatEvent(GlobalChatEvent event){
        ParseIntoJs.getInstance().JSParseEvent(event);
    }

    @EventHandler
    public static void playerKickEvent(PlayerKickEvent event){
        ParseIntoJs.getInstance().JSParseEvent(event);
    }

    @EventHandler
    public static void customPacketReceiveEvent(CustomPacketReceiveEvent event){
        ParseIntoJs.getInstance().JSParseEvent(event);
    }

    @EventHandler
    public static void globalChatReceiveEvent(GlobalChatReceiveEvent event){
        ParseIntoJs.getInstance().JSParseEvent(event);
    }

    @EventHandler
    public static void altChannelEvent(AltChatChannelReceiveEvent event){
        ParseIntoJs.getInstance().JSParseEvent(event);
    }

    @EventHandler
    public static void inventoryClickEvent(InventoryClickEvent event){
        ParseIntoJs.getInstance().JSParseEvent(event);
    }

    @EventHandler
    public static void inventoryInteractEvent(InventoryInteractEvent event){
        ParseIntoJs.getInstance().JSParseEvent(event);
    }

    @EventHandler
    public static void playerClickEvent(PlayerInteractEvent event){
        ParseIntoJs.getInstance().JSParseEvent(event);
    }

    @EventHandler
    public static void playerDeathEvent(PlayerDeathEvent event){ParseIntoJs.getInstance().JSParseEvent(event);}

    @EventHandler
    public static void playerDeathEvent(PlayerKickEvent event){ParseIntoJs.getInstance().JSParseEvent(event);}

    @EventHandler
    public static void playerItemdropEvent(PlayerDropItemEvent event){ParseIntoJs.getInstance().JSParseEvent(event);}
}


