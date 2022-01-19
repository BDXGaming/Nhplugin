package me.bdx.nhplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DummyCommand implements CommandExecutor {

    /**
     * This command serves as an empty command which can be called without any result. This command has multiple aliases
     * which are listened for in the PlayerCommandPreprocessEvent to be passed to Nashorn
     * @param sender CommandSender
     * @param command Command
     * @param label String
     * @param args String[]
     * @return true Boolean
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return true;
    }
}
