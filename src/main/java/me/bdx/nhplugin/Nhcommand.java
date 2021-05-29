package me.bdx.nhplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class Nhcommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage("For some reason this is a player only command");
            return true;
        }

        Player player = (Player) sender;

        if (sender.hasPermission("nh.cmd")) {
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
                invocable.invokeFunction((String)args[0], player, cmd, args);
            } catch (ScriptException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                System.out.println("No such function exists");
            }
            return true;


        } else {
            return false;
        }
    }
}


