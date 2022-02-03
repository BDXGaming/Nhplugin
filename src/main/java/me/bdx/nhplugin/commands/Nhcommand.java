package me.bdx.nhplugin.commands;

import me.bdx.nhplugin.files.ParseIntoJs;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Nhcommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage("For some reason this is a player only command");
            return true;
        }

        Player player = (Player) sender;

        if (sender.hasPermission("nh.cmd")) {
            ParseIntoJs.getInstance().JSParseCommand(args[0], player, args, label);
            return true;


        } else {
            return false;
        }
    }
}


