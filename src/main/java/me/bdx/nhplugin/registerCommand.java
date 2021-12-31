package me.bdx.nhplugin;

import java.util.ArrayList;
import java.util.List;
import me.bdx.nhplugin.Ticket;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Scoreboard;

public class registerCommand {

    private static ArrayList<String> commands= new ArrayList<>();

    public static void addComamnd(String name) {
        try {
            List<String> aliases = new ArrayList<String>();
            Nhplugin.registerFakeCommand(new jsCommands("none", name, "A Test Command", "/<command> <args>", aliases), Nhplugin.getInstance());
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
        if(commands.size() > 0) {
            if (commands.contains(name)) {
                throw new NullPointerException("This command is already registered!");
            } else {
                commands.add(name);
            }
        }
        else{
            commands.add(name);
        }
    }

    public static ArrayList<String> getCommandList(){
        return commands;
    }
}
