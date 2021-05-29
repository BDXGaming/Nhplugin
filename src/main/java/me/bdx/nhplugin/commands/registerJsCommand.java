package me.bdx.nhplugin.commands;

import me.bdx.nhplugin.registerCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class registerJsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if (args.length > 0){
                if(player.hasPermission("nh.registerCommands")){
                    registerCommand.addComamnd(args[0]);
                    return true;
                }
            }
            else{
                return false;
            }
        }
        return false;
    }
}
