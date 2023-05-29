var fileLocationBase = "plugins/Nhplugin/scripts/"

load(fileLocationBase  +"command.js");
load(fileLocationBase+"eventHandler.js");

// Defining Classes from Java over Nashorn
var Bukkit = Java.type('org.bukkit.Bukkit')
var ChatColor = Java.type('org.bukkit.ChatColor')
var Player = Java.type('org.bukkit.entity.Player')
var PlayerJoinEvent = Java.type('org.bukkit.event.player.PlayerJoinEvent')
var JavaPlugin = Java.type('org.bukkit.plugin.java.JavaPlugin')
var Scoreboard = Java.type('org.bukkit.scoreboard.Scoreboard')
var DisplaySlot = Java.type('org.bukkit.scoreboard.DisplaySlot')
var ScoreboardManager = Java.type('org.bukkit.scoreboard.ScoreboardManager')
var GameMode = Java.type("org.bukkit.GameMode")
var ArrayList = Java.type("java.util.ArrayList")
var ByteStreams = Java.type("com.google.common.io.ByteStreams")
var StringUtil = Java.type("org.bukkit.util.StringUtil")
var Collections = Java.type("java.util.Collections")
var ItemStack = Java.type("org.bukkit.inventory.ItemStack")
var Material = Java.type("org.bukkit.Material")
var Inventory = Java.type("org.bukkit.inventory.Inventory")
var Event = Java.type("org.bukkit.event.Event")
var spigot = Java.type("org.bukkit.Server.Spigot")
var FileWriter=Java.type("java.io.FileWriter");
var BukkitLocation = Java.type("org.bukkit.Location")

var loaded = false

if(!(loaded)){
    loaded = true
}

// Default JS permission denied message
var permissionErrorMessage = ChatColor.RED + "You do not have the required permission to use this command!"

//Functions
function stringToCommand(message){
    var command = message.substring(1, message.length)
    return command.split(" ")
}

/** This event is called when a command is executed.
 *  This is the only independently called event as it is used for command processing
 *  @warning DO NOT DELETE
 */
var OnPlayerCommandProcess = function(event, chat, nhplugin){

    //This processes the command to check if we are handling the command in js or not
    var commandArgs = stringToCommand(event.getMessage())
    commandProcesser(event, event.getPlayer(), commandArgs[0], commandArgs, nhplugin)

    eventHandler(event);

}

var OnServerCommandEvent = function(event, chat, nhplugin){

    var commandArgs = stringToCommand(event.getCommand())
    commandProcesser(event, event.getSender(), commandArgs[0], commandArgs, nhplugin)

    eventHandler(event);
}

/** The main event handler entrypoint
 *  @param event The event which called the method from the Java Plugin
 *  @param managerapi The instance of the ManagerAPI plugin
 *  @param nhplugin The instance of the Nhplugin invoking the JS
 */
var onEvent = function(event, nhplugin){
    eventHandler(event, nhplugin);
}
