package me.bdx.nhplugin.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class nhevents implements Listener {

    @EventHandler
    public static void onPlayerJoin(PlayerJoinEvent event){
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        try {
            engine.eval(new FileReader("test.js"));
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Invocable invocable = (Invocable) engine;
        Object result = null;
        try {
            invocable.invokeFunction("OnPlayerJoinEvent", event);
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            System.out.println("No such function exists");
        }
    }

    @EventHandler
    public static void onPlayerQuit(PlayerQuitEvent event){
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        try {
            engine.eval(new FileReader("test.js"));
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Invocable invocable = (Invocable) engine;
        Object result = null;
        try {
            invocable.invokeFunction("OnPlayerQuitEvent", event);
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            int i =0;
        }
    }

}
