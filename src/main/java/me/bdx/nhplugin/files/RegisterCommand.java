package me.bdx.nhplugin.files;

import java.util.ArrayList;
import java.util.List;

import me.bdx.nhplugin.Nhplugin;
import me.bdx.nhplugin.js.JsCommands;

public class RegisterCommand {

    private static ArrayList<String> commands= new ArrayList<>();

    public static void addComamnd(String name) {
        try {
            List<String> aliases = new ArrayList<String>();
            Nhplugin.registerFakeCommand(new JsCommands("none", name, "A Test Command", "/<command> <args>", aliases), Nhplugin.getInstance());
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
