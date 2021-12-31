package me.bdx.nhplugin.files;

import me.bdx.nhplugin.Nhplugin;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.openjdk.nashorn.api.scripting.NashornScriptEngineFactory;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class parseIntoJs {

    public static void JSParseCommand(String meth, Player sender, String[] args, String cmd){
        ScriptEngineFactory sef = new org.openjdk.nashorn.api.scripting.NashornScriptEngineFactory();
        ScriptEngine engine = sef.getScriptEngine();
        try {
            engine.eval(new FileReader("test.js"));
        } catch (ScriptException | FileNotFoundException e) {
            e.printStackTrace();
        }
        Invocable invocable = (Invocable) engine;
        try {
            invocable.invokeFunction(meth, sender, meth, args, Nhplugin.chat, Nhplugin.managerapi, Nhplugin.getInstance());
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            System.out.println("No such function exists");
        }

    }

    public static void JSParseEvent(Event event){
        ScriptEngineFactory sef = new NashornScriptEngineFactory();
        ScriptEngine engine = sef.getScriptEngine();
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
            invocable.invokeFunction("onEvent", event, Nhplugin.managerapi, Nhplugin.getInstance());
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            int i =0;
        }
    }
}
