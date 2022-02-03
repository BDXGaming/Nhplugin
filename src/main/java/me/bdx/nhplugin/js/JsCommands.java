package me.bdx.nhplugin.js;

import me.bdx.nhplugin.files.ParseIntoJs;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import java.util.List;

public class JsCommands extends BukkitCommand {

    public JsCommands(String perm, String name, String des, String usage, List<String> aliases){
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

            ParseIntoJs.getInstance().JSParseCommand(label, player, args, label);
            return true;

        } else {
            return false;
        }
    }
}
