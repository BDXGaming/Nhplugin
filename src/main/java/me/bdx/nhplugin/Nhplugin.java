package me.bdx.nhplugin;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import me.bdx.managerapi.Managerapi;
import me.bdx.nhplugin.commands.dummyCommand;
import me.bdx.nhplugin.commands.registerJsCommand;
import me.bdx.nhplugin.events.bungeeReceiveEvent;
import me.bdx.nhplugin.events.nhevents;
import me.bdx.nhplugin.events.playerChatEvent;
import me.bdx.nhplugin.files.DataQueue;
import me.bdx.nhplugin.files.parseIntoJs;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Method;

public final class Nhplugin extends JavaPlugin implements PluginMessageListener {
    private static JavaPlugin instance;
    private DataQueue dataQueue;
    public static Chat chat;
    public static Managerapi managerapi;

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

        dataQueue = new DataQueue();
        //sets the instance
        instance= this;

        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        if (rsp != null) {
            chat = rsp.getProvider();
        }

        Plugin managerapiPlugin = getServer().getPluginManager().getPlugin("Managerapi");
        managerapi = (Managerapi) managerapiPlugin;

        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);

        getCommand("js").setExecutor(new Nhcommand());
        getCommand("newTicket").setExecutor(new nhnewticket());
        getCommand("registercommand").setExecutor(new registerJsCommand());
        getCommand("dummycommand").setExecutor(new dummyCommand());

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

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        this.getServer().getMessenger().unregisterOutgoingPluginChannel(this);
        this.getServer().getMessenger().unregisterIncomingPluginChannel(this);
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equals("BungeeCord")) {
            return;
        }
        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String subchannel = in.readUTF();
        bungeeReceiveEvent event = new bungeeReceiveEvent(subchannel, in);
        parseIntoJs.JSParseEvent(event);
    }

    public static JavaPlugin getInstance() {
        return instance;
    }

    public DataQueue getDataQueue(){
        return dataQueue;
    }



}
