package me.bdx.nhplugin.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class playerChatEvent implements Listener {

    @EventHandler
    public static void onPlayerChat(AsyncPlayerChatEvent event){
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        try {
            engine.eval(new FileReader("test.js"));
        } catch (ScriptException | FileNotFoundException e) {
            e.printStackTrace();
        }

        Invocable invocable = (Invocable) engine;
        Object result = null;
        try {
            invocable.invokeFunction("OnPlayerChatEvent", event);
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            int i =0;
        }
    }
}
