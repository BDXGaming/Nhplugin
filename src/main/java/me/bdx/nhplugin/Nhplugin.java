package me.bdx.nhplugin;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import me.bdx.managerapi.Managerapi;
import me.bdx.nhplugin.commands.Nhcommand;
import me.bdx.nhplugin.commands.DummyCommand;
import me.bdx.nhplugin.commands.RegisterJsCommand;
import me.bdx.nhplugin.commands.ReloadScriptsCommand;
import me.bdx.nhplugin.events.BungeeReceiveEvent;
import me.bdx.nhplugin.events.Nhevents;
import me.bdx.nhplugin.events.PlayerChatEvent;
import me.bdx.nhplugin.files.*;
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
import java.util.ArrayList;

public final class Nhplugin extends JavaPlugin implements PluginMessageListener {
    private static Nhplugin instance;
    private DataQueue dataQueue;
    public static Chat chat;
    public static Managerapi managerapi;
    private NhpluginConfig nhpluginConfig;
    public static ConfigController configcontroller;
    public RegisterCommand registerCommand;
    public ArrayList<String> listenedEvents;

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

        //Sets up the config
        NhpluginConfig.setup();

        //Creates the Data Queue
        dataQueue = new DataQueue();

        //Creates the controller for all config values
        configcontroller = new ConfigController();

        registerCommand = new RegisterCommand();

        listenedEvents = new ArrayList<>();
        //listenedEvents.add(PlayerChatEvent.class.getName());

        //Starts the scriptloader
        ParseIntoJs.getInstance();

        //Gets the instance of Vault chat
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        if (rsp != null) {
            chat = rsp.getProvider();
        }

        //Gets the instance of ManangerAPI
        Plugin managerapiPlugin = getServer().getPluginManager().getPlugin("Managerapi");
        managerapi = (Managerapi) managerapiPlugin;

        //Registers BungeeCord Channels
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);

        //Sets command executors
        getCommand("js").setExecutor(new Nhcommand());
        getCommand("registercommand").setExecutor(new RegisterJsCommand());
        getCommand("dummycommand").setExecutor(new DummyCommand());
        getCommand("reloadscripts").setExecutor(new ReloadScriptsCommand());

        //Resisters event listeners
        getServer().getPluginManager().registerEvents(new Nhevents(), this);
        getServer().getPluginManager().registerEvents(new PlayerChatEvent(), this);

    }

    @Override
    public boolean onCommand(CommandSender sender,Command command, String label,String[] args){
        if(sender instanceof Player){
            for (String name: RegisterCommand.getCommandList()){
                if (command.getName().equalsIgnoreCase(name)){
                    Player player = (Player) sender;

                    if (sender.hasPermission("nh.cmd")) {
                        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
                        try {
                            engine.eval(new FileReader(Nhplugin.configcontroller.JS_ENTRY_FILE));
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
        BungeeReceiveEvent event = new BungeeReceiveEvent(subchannel, in);
        ParseIntoJs.getInstance().JSParseEvent(event);
    }

    public static JavaPlugin getInstance() {
        return instance;
    }

    //This is used to get the data queue where JS can store data which can be referenced later
    public DataQueue getDataQueue(){
        return dataQueue;
    }
    public NhpluginConfig getConfigManger(){return nhpluginConfig; }
    public RegisterCommand getRegisterCommand(){return registerCommand; }
    public ArrayList<String> getListenedEvents(){return listenedEvents; }
    public void addListenedEvent(String event){listenedEvents.add(event);}
    public void removeListenedEvent(String event){listenedEvents.remove(event);}


}
