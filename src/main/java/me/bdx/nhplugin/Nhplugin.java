package me.bdx.nhplugin;

import me.bdx.nhplugin.commands.registerJsCommand;
import me.bdx.nhplugin.events.nhevents;
import me.bdx.nhplugin.events.playerChatEvent;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Method;

public final class Nhplugin extends JavaPlugin {
    private static JavaPlugin instance;

    public static void registerFakeCommand(Command whatCommand, Plugin plugin)
            throws ReflectiveOperationException {

        //Getting command map from CraftServer
        Method commandMap = plugin.getServer().getClass().getMethod("getCommandMap", null);

        //Invoking the method and getting the returned object (SimpleCommandMap)
        Object cmdmap = commandMap.invoke(plugin.getServer(), null);

        //getting register method with parameters String and Command from SimpleCommandMap
        Method register = cmdmap.getClass().getMethod("register", String.class,Command.class);

        //Registering the command provided above
        register.invoke(cmdmap, whatCommand.getName(),whatCommand);
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN +" Starting up");

        //sets the instance
        instance= this;

        getCommand("js").setExecutor(new Nhcommand());
        getCommand("newTicket").setExecutor(new nhnewticket());
        getCommand("registercommand").setExecutor(new registerJsCommand());
        getServer().getPluginManager().registerEvents(new nhevents(), this);
        getServer().getPluginManager().registerEvents(new playerChatEvent(), this);

    }

    @Override
    public boolean onCommand(CommandSender sender,Command command, String label,String[] args){
        if(sender instanceof Player){
            for (String name: registerCommand.getCommandList()){
                if (command.getName().equalsIgnoreCase(name)){
                    Player player = (Player) sender;

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
                            invocable.invokeFunction(command.getName(), player, command, args);
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
        }
        return true;
    }

    public static JavaPlugin getInstance() {
        return instance;
    }



}
