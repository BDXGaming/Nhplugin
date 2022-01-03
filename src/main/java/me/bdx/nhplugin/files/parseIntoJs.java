package me.bdx.nhplugin.files;

import me.bdx.nhplugin.Nhplugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.scheduler.BukkitRunnable;
import org.openjdk.nashorn.api.scripting.NashornScriptEngineFactory;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class parseIntoJs {
    private static ScriptEngine engine;
    private static Invocable invocable;

    public static void start(){
        ScriptEngineFactory sef = new org.openjdk.nashorn.api.scripting.NashornScriptEngineFactory();
        engine = sef.getScriptEngine();
        try {
            engine.eval(new FileReader(Nhplugin.configcontroller.JS_ENTRY_FILE));
        } catch (ScriptException | FileNotFoundException e) {
            e.printStackTrace();
        }
        invocable = (Invocable) engine;
    }

    public static void JSParseCommand(String meth, Player sender, String[] args, String cmd){

        try {
            invocable.invokeFunction(meth, sender, meth, args, Nhplugin.chat, Nhplugin.managerapi, Nhplugin.getInstance());
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            System.out.println("No such function exists");
        }

    }

    public static void JSParseEvent(Event event){

        try {
            invocable.invokeFunction("onEvent", event, Nhplugin.managerapi, Nhplugin.getInstance());
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            int i =0;
        }
    }
}
