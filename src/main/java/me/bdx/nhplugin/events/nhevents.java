package me.bdx.nhplugin.events;

import me.bdx.managerapi.customEvents.GlobalChatEvent;
import me.bdx.managerapi.customEvents.altChatChannelReceiveEvent;
import me.bdx.managerapi.customEvents.customPacketReceiveEvent;
import me.bdx.managerapi.customEvents.globalChatReceiveEvent;
import me.bdx.nhplugin.Nhplugin;
import me.bdx.nhplugin.files.parseIntoJs;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.event.server.TabCompleteEvent;
import javax.script.ScriptException;

public class nhevents implements Listener {

    @EventHandler
    public static void onPlayerJoin(PlayerJoinEvent event){
        parseIntoJs.JSParseEvent(event);
    }

    @EventHandler
    public static void onPlayerQuit(PlayerQuitEvent event){
        parseIntoJs.JSParseEvent(event);
    }

    @EventHandler
    public static void PlayerCommandPreprocessEvent(PlayerCommandPreprocessEvent event){
        try {
            parseIntoJs.getInvocable().invokeFunction("OnPlayerCommandProcess", event, Nhplugin.chat, Nhplugin.managerapi, Nhplugin.getInstance());
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            int i =0;
        }
    }

    @EventHandler
    public static void ServerCommandEvent(ServerCommandEvent event){

        try {
            parseIntoJs.getInvocable().invokeFunction("OnServerCommandEvent", event, Nhplugin.chat, Nhplugin.managerapi, Nhplugin.getInstance());
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            int i =0;
        }
    }

    @EventHandler
    public static void TabCompleteEvent(TabCompleteEvent event){
        parseIntoJs.JSParseEvent(event);
    }

    @EventHandler
    public static void globalChatEvent(GlobalChatEvent event){
        parseIntoJs.JSParseEvent(event);
    }

    @EventHandler
    public static void playerKickEvent(PlayerKickEvent event){
        parseIntoJs.JSParseEvent(event);
    }

    @EventHandler
    public static void customPacketReceiveEvent(customPacketReceiveEvent event){
        parseIntoJs.JSParseEvent(event);
    }

    @EventHandler
    public static void globalChatReceiveEvent(globalChatReceiveEvent event){
        parseIntoJs.JSParseEvent(event);
    }

    @EventHandler
    public static void altChannelEvent(altChatChannelReceiveEvent event){
        parseIntoJs.JSParseEvent(event);
    }

    @EventHandler
    public static void InventoryClickEvent(InventoryClickEvent event){
        parseIntoJs.JSParseEvent(event);
    }

    @EventHandler
    public static void InventoryInteractEvent(InventoryInteractEvent event){
        parseIntoJs.JSParseEvent(event);
    }

    @EventHandler
    public static void PlayerClickEvent(PlayerInteractEvent event){
        parseIntoJs.JSParseEvent(event);
    }


}


