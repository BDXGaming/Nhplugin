package me.bdx.nhplugin.events;

import me.bdx.managerapi.customEvents.GlobalChatEvent;
import me.bdx.managerapi.customEvents.altChatChannelReceiveEvent;
import me.bdx.managerapi.customEvents.customPacketReceiveEvent;
import me.bdx.managerapi.customEvents.globalChatReceiveEvent;
import me.bdx.nhplugin.Nhplugin;
import me.bdx.nhplugin.files.ParseIntoJs;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.event.server.TabCompleteEvent;
import javax.script.ScriptException;

public class Nhevents implements Listener {

    @EventHandler
    public static void onPlayerJoin(PlayerJoinEvent event){
        ParseIntoJs.JSParseEvent(event);
    }

    @EventHandler
    public static void onPlayerQuit(PlayerQuitEvent event){
        ParseIntoJs.JSParseEvent(event);
    }

    @EventHandler
    public static void PlayerCommandPreprocessEvent(PlayerCommandPreprocessEvent event){
        try {
            ParseIntoJs.getInvocable().invokeFunction("OnPlayerCommandProcess", event, Nhplugin.chat, Nhplugin.managerapi, Nhplugin.getInstance());
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            int i =0;
        }
    }

    @EventHandler
    public static void ServerCommandEvent(ServerCommandEvent event){

        try {
            ParseIntoJs.getInvocable().invokeFunction("OnServerCommandEvent", event, Nhplugin.chat, Nhplugin.managerapi, Nhplugin.getInstance());
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            int i =0;
        }
    }

    @EventHandler
    public static void TabCompleteEvent(TabCompleteEvent event){
        ParseIntoJs.JSParseEvent(event);
    }

    @EventHandler
    public static void globalChatEvent(GlobalChatEvent event){
        ParseIntoJs.JSParseEvent(event);
    }

    @EventHandler
    public static void playerKickEvent(PlayerKickEvent event){
        ParseIntoJs.JSParseEvent(event);
    }

    @EventHandler
    public static void customPacketReceiveEvent(customPacketReceiveEvent event){
        ParseIntoJs.JSParseEvent(event);
    }

    @EventHandler
    public static void globalChatReceiveEvent(globalChatReceiveEvent event){
        ParseIntoJs.JSParseEvent(event);
    }

    @EventHandler
    public static void altChannelEvent(altChatChannelReceiveEvent event){
        ParseIntoJs.JSParseEvent(event);
    }

    @EventHandler
    public static void inventoryClickEvent(InventoryClickEvent event){
        ParseIntoJs.JSParseEvent(event);
    }

    @EventHandler
    public static void inventoryInteractEvent(InventoryInteractEvent event){
        ParseIntoJs.JSParseEvent(event);
    }

    @EventHandler
    public static void playerClickEvent(PlayerInteractEvent event){
        ParseIntoJs.JSParseEvent(event);
    }

    @EventHandler
    public static void playerDeathEvent(PlayerDeathEvent event){ParseIntoJs.JSParseEvent(event);}


}


