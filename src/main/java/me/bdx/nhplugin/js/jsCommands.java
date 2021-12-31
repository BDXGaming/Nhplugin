package me.bdx.nhplugin.js;

import me.bdx.nhplugin.files.parseIntoJs;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import javax.script.*;
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

        Player player = (Player) sender;

        if (sender.hasPermission("nh.cmd")) {

            parseIntoJs.JSParseCommand(label, player, args, label);
            return true;

        } else {
            return false;
        }
    }
}
