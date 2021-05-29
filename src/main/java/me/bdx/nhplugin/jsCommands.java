package me.bdx.nhplugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class jsCommands extends BukkitCommand {

    public jsCommands(String perm, String name, String des, String usage, List<String> aliases){
        super(name, des, usage, aliases);
        if(!(perm.equals("none"))) {
            this.setPermission(perm);
        }
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage("For some reason this is a player only command");
            return true;
        }

        Player player = null;
        player = (Player) sender;

        if (sender.hasPermission("nh.cmd")) {
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
            try {
                engine.eval(new FileReader("test.js"));
            } catch (ScriptException | FileNotFoundException e) {
                e.printStackTrace();
            }

            Invocable invocable = (Invocable) engine;
            Object result = null;
            try {
                invocable.invokeFunction(label, player, label, args);
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
