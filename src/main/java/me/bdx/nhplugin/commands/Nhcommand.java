package me.bdx.nhplugin.commands;

import me.bdx.nhplugin.files.parseIntoJs;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.script.*;
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
            parseIntoJs.JSParseCommand(args[0], player, args, label);
            return true;


        } else {
            return false;
        }
    }
}


