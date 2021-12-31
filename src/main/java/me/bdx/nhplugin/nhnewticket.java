package me.bdx.nhplugin;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
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

public class nhnewticket implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage("For some reason this is a player only command");
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
                result =  invocable.invokeFunction("newTicket", player, cmd, args);
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
