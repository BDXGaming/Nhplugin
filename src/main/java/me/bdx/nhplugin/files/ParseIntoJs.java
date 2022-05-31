package me.bdx.nhplugin.files;

import me.bdx.nhplugin.Nhplugin;
import me.bdx.nhplugin.events.ScriptLoadEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.openjdk.nashorn.api.scripting.NashornScriptEngineFactory;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class ParseIntoJs {
    private ScriptEngine engine;
    private Invocable invocable;
    private static ParseIntoJs parseIntoJs;

    private ParseIntoJs(){
        start();
    }

    /**
     * Gets the instance of the ParseIntoJs class
     * @return instance of class
     */
    public static ParseIntoJs getInstance(){
        if(parseIntoJs == null){
            parseIntoJs = new ParseIntoJs();
        }
        return parseIntoJs;
    }


    /**
     * When invoked this method gets and loads the Nashorn script engine
     */
    public void start(){

        ScriptEngineFactory sef = new  NashornScriptEngineFactory();
        engine = sef.getScriptEngine();
        try {
            engine.eval(new FileReader(Nhplugin.configcontroller.JS_ENTRY_FILE));
            Bukkit.getScheduler().runTask(JavaPlugin.getPlugin(Nhplugin.class), () -> {
                Bukkit.getServer().getPluginManager().callEvent(new ScriptLoadEvent());
            });

        } catch (ScriptException | FileNotFoundException e) {
            e.printStackTrace();
        }
        invocable = (Invocable) engine;

        //Reloads the scripts once per minute to ensure that they are up to date (runs async as to prevent lagging the rest of the server)
        new BukkitRunnable(){
            @Override
            public void run(){
                reload();
            }
        }.runTaskTimerAsynchronously(Nhplugin.getInstance(), 1, 1200);

    }

    /**
     * When invoked this method refreshes both the instance of Nashorn and the scripts which are being evaluated
     */
    public void reload(){
        ScriptEngineFactory sef = new NashornScriptEngineFactory();
        engine = sef.getScriptEngine();
        try {
            engine.eval(new FileReader(Nhplugin.configcontroller.JS_ENTRY_FILE));
            Bukkit.getScheduler().runTask(JavaPlugin.getPlugin(Nhplugin.class), () -> {
                Bukkit.getServer().getPluginManager().callEvent(new ScriptLoadEvent());
            });
        } catch (ScriptException | FileNotFoundException e) {
            e.printStackTrace();
        }
        invocable = (Invocable) engine;
    }

    /**
     * Gets the current invokable
     * @return the Invocable
     */
    public Invocable getInvocable(){
        return invocable;
    }

    /**
     * Parses a command and executes the js command from the JS file
     * @param meth String
     * @param sender Player
     * @param args String[]
     * @param cmd String
     */
    public void JSParseCommand(String meth, Player sender, String[] args, String cmd){

        try {
            invocable.invokeFunction(meth, sender, meth, args, Nhplugin.chat, Nhplugin.managerapi, Nhplugin.getInstance());
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            Bukkit.getLogger().warning("No such function exists");
        }

    }

    /**
     * Parses a given event and executes the JS event
     * @param event Event
     */
    public void JSParseEvent(Event event){

        try {
            invocable.invokeFunction("onEvent", event, Nhplugin.managerapi, Nhplugin.getInstance());
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            int i =0;
        }
    }
}
