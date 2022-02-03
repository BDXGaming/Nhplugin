package me.bdx.nhplugin.commands;

import me.bdx.nhplugin.files.ParseIntoJs;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadScriptsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender.hasPermission("nh.reloadscripts")){
            ParseIntoJs.getInstance().reload();
            sender.sendMessage(ChatColor.GREEN + "Scripts have been reloaded!");
        }

        return true;
    }
}
