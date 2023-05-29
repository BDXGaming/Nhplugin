package me.bdx.nhplugin;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import me.bdx.nhplugin.commands.*;
import me.bdx.nhplugin.events.BungeeReceiveEvent;
import me.bdx.nhplugin.events.Nhevents;
import me.bdx.nhplugin.events.PlayerChatEvent;
import me.bdx.nhplugin.files.*;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Objects;


class ConfigCommmand  {
    public String alias;
    public String command;
    public String description;

    public ConfigCommmand(String alias, String command, String description) {
        this.alias = alias;
        this.command = command;
        this.description = description;
    }
}


public final class Nhplugin extends JavaPlugin implements PluginMessageListener {

    private static Nhplugin instance;
    private DataQueue dataQueue;
    public static Chat chat;
    private NhpluginConfig nhpluginConfig;
    public static ConfigController configcontroller;
    public ArrayList<String> listenedEvents;
    private static ArrayList<ConfigCommmand> customCommands;

    /**
     * Registers the commands that are listed in the config file into the commmand map
     */
    private void registerCommands() {
        try {
            final Field bukkitCommandMap = getServer().getClass().getDeclaredField("commandMap");

            bukkitCommandMap.setAccessible(true);
            CommandMap commandMap = (CommandMap) bukkitCommandMap.get(getServer());

            for (ConfigCommmand configCommmand : customCommands) {
                commandMap.register("nhplugin" , new Command(configCommmand.alias, configCommmand.description,
                        "/<command>", new ArrayList<>()) {

                    @Override
                    public boolean execute(CommandSender sender, String arg1, String[] arg2) {
                        Player p = ((sender instanceof Player)?(Player)sender:null);
                        if (p == null) {
                            return true;
                        }
                        return true;
                    }
                });
            }
        } catch (NoSuchFieldException | IllegalAccessException | IllegalArgumentException | SecurityException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates the instances of the commands that will be used to register the command
     */
    private void createCommands() {
        ConfigCommmand cmd;
        for (Object cmdname : Objects.requireNonNull(NhpluginConfig.get().getList("commandNames"))) {
            Bukkit.getConsoleSender().sendMessage("[NhPlugin] Found command \"" + cmdname + "\"");
            cmd = new ConfigCommmand((String) cmdname, cmdname+"1", "A JS Command");
            customCommands.add(cmd);
        }
    }

    @Override
    public void onEnable() {
        customCommands = new ArrayList<>();

        boolean first = false;
        if (!(getDataFolder().exists())) {
            first = true;
        }

        // Plugin startup logic
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN +" Starting up");

        //sets the instance
        instance= this;

        //Sets up the config
        NhpluginConfig.setup();
        createCommands();

        //Creates the Data Queue
        dataQueue = new DataQueue();

        //Creates the controller for all config values
        configcontroller = new ConfigController();

        listenedEvents = new ArrayList<>();

        if (first) {
            new JavaScriptFileManager();
        }

        //Starts the scriptloader
        ParseIntoJs.getInstance();

        //Gets the instance of Vault chat
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        if (rsp != null) {
            chat = rsp.getProvider();
        }

        //Registers BungeeCord Channels
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);

        //Sets command executors
        getCommand("dummycommand").setExecutor(new DummyCommand());
        getCommand("reloadscripts").setExecutor(new ReloadScriptsCommand());

        // Registers the command in the config
        registerCommands();

        //Resisters event listeners
        getServer().getPluginManager().registerEvents(new Nhevents(), this);
        getServer().getPluginManager().registerEvents(new PlayerChatEvent(), this);

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
    public NhpluginConfig getConfigManger(){return nhpluginConfig;}
    public ArrayList<String> getListenedEvents(){return listenedEvents;}
    public void addListenedEvent(String event){listenedEvents.add(event);}
    public void removeListenedEvent(String event){listenedEvents.remove(event);}


}
