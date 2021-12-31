package me.bdx.nhplugin.events;

import me.bdx.managerapi.customEvents.GlobalChatEvent;
import me.bdx.managerapi.customEvents.customPacketReceiveEvent;
import me.bdx.managerapi.customEvents.globalChatReceiveEvent;
import me.bdx.nhplugin.Nhplugin;
import me.bdx.nhplugin.files.parseIntoJs;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.event.server.TabCompleteEvent;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import javax.script.*;

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

        ScriptEngineFactory sef = new org.openjdk.nashorn.api.scripting.NashornScriptEngineFactory();
        ScriptEngine engine = sef.getScriptEngine();
        try {
            engine.eval(new FileReader(Nhplugin.configcontroller.JS_ENTRY_FILE));
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Invocable invocable = (Invocable) engine;
        Object result = null;
        try {
            invocable.invokeFunction("OnPlayerCommandProcess", event, Nhplugin.chat, Nhplugin.managerapi, Nhplugin.getInstance());
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

}
