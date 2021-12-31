load("scripts/command.js");
load("scripts/utils/scoreboard.js");
load("scripts/eventHandler.js")

var Bukkit = Java.type('org.bukkit.Bukkit')
var ChatColor = Java.type('org.bukkit.ChatColor')
var Player = Java.type('org.bukkit.entity.Player')
var PlayerJoinEvent = Java.type('org.bukkit.event.player.PlayerJoinEvent')
var JavaPlugin = Java.type('org.bukkit.plugin.java.JavaPlugin')
var Scoreboard = Java.type('org.bukkit.scoreboard.Scoreboard')
var DisplaySlot = Java.type('org.bukkit.scoreboard.DisplaySlot')
var ScorebaordManager = Java.type('org.bukkit.scoreboard.ScoreboardManager')
var GameMode = Java.type("org.bukkit.GameMode")
var ArrayList = Java.type("java.util.ArrayList")
var ByteStreams = Java.type("com.google.common.io.ByteStreams")
var StringUtil = Java.type("org.bukkit.util.StringUtil")
var Collections = Java.type("java.util.Collections")

//Constanstants defined in JS
var serverName = "server display name"
var bungeeServerName = "server-name"
var chatStatus = false
var permissionErrorMessage = "You do not have the required permission to use this command!"


//Functions
function stringToCommand(message){
    var command = message.substring(1, message.length)
    return command.split(" ")
}

var test = function(sender, cmd, args, chat, ma){
    var prefix = chat.getPlayerPrefix(sender)
    Bukkit.broadcast(ChatColor.translateAlternateColorCodes('&', prefix + sender.getName()) , "managerapi.staff")
    Bukkit.broadcast(ChatColor.translateAlternateColorCodes('&', ma.getGlobalPlayers().getOnlinePlayers().toString()) , "managerapi.staff")
    ma.getGlobalChat().broadcast(ChatColor.translateAlternateColorCodes('&', "&cSent from JS!"), "managerapi.staff")
}

var promote = function(sender, cmd, args){
    sender.sendMessage("You can do other actions here as well")
    sender.performCommand("lp user "+args[1]+" parent set "+args[2])
}

var command = function(sender, cmd, args){
    sender.sendMessage(sender.getDisplayName()+ChatColor.translateAlternateColorCodes('&', ": &3Someone sent this cool command! Hello"));
    sender.sendMessage(testExport())
}

var info = function(sender, cmd, args){
    if(sender.hasPermission("nh.see")){
        if (args.length >1){
            var user = args[1]
            var player = Bukkit.getPlayer(user)
            var msg = "\n"
            msg += ChatColor.translateAlternateColorCodes('&', "&cPlayer: "+ player.getName() + "\n")
            msg += ChatColor.translateAlternateColorCodes('&',"&aUUID: ") + player.getUniqueId() +"\n"
            msg += "Addr: " + player.getAddress()+"\n"
            msg += ChatColor.translateAlternateColorCodes('&',"&aDisplayname: ") + player.getDisplayName() +"\n"
            sender.sendMessage(msg)
        }
    }
    else{
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&','&4You do not have the perms to use this command!'))
    }
}

var inv = function(sender, cmd, args){
    if (args.length > 1){
        if (sender.hasPermission("nh.inv.edit")){
            var user = args[1]
            var player = Bukkit.getPlayer(user)
            var inv = player.getInventory()
            sender.openInventory(inv)
        }
        else if (sender.hasPermission("nh.inv.see")){
            var user = args[1]
            var player = Bukkit.getPlayer(user)
            var inv = player.getInventory()

        }
        else{
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&','&4You do not have the perms to use this command!'))
        }
    }
    else if(args.length >=0){
        if (sender.hasPermission("nh.inv.edit")){
            var user = args[0]
            var player = Bukkit.getPlayer(user)
            var inv = player.getInventory()
            sender.openInventory(inv)
        }
        else if (sender.hasPermission("nh.inv.see")){
            var user = args[0]
            var player = Bukkit.getPlayer(user)
            var inv = player.getInventory()

        }
        else{
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&','&4You do not have the perms to use this command!'))
        }
    }
    else{
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&','&eUse /js inv <user>'))
    }
} 

var kick = function(sender, cmd, args){
    if (sender.hasPermission("mod.kick")){
        if(args.length > 1){
            var player = Bukkit.getPlayer(args[1])
            if(!(player.isOp())){
                if(args.length >2){
                    print(player.isOp())
                    var msg =""
                    for(var i = 0; i < args.length-2; i++){
                        msg+= args[i+2]+" "
                    }
                    player.kickPlayer(ChatColor.translateAlternateColorCodes('&',msg))
                }
                else{
                    player.kickPlayer(ChatColor.translateAlternateColorCodes('&',"&cYou have been kicked from the server!"))
                }
            }
            else{
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&','&cYou cannot kick this user'))
            }
        }
        else{
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&','&eUse /js kick <user> <reason>'))
        }
        }
    else{
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&','&4You do not have the perms to use this command!'))
    }
}

var dname = function(sender, cmd, args){

    sender.sendMessage(args[1])
    var player = Bukkit.getPlayer(args[1])

    sender.sendMessage(player.getDisplayName())

}

var alert = function(sender, cmd, args){
    var msg = ""
    for(var i =1; i < args.length; i++){
        msg += args[i] + " "
    }
    
    var title = ChatColor.translateAlternateColorCodes('&', '&c' +msg)
    var subtext = ChatColor.translateAlternateColorCodes('&', '')
    sender.sendTitle(title, subtext, 1, 100, 1);

    players = Bukkit.getServer().getOnlinePlayers().toArray()

    for(var i=0; i<players.length; i++){

        player = players[i]

        player.sendTitle(title, subtext, 1, 100, 1);
    }
}

var quit = function(sender, cmd, args){

    if(sender.hasPermission('nh.quit')){
        var p = Bukkit.getPlayer(sender.getUniqueId())
        p.kickPlayer(ChatColor.translateAlternateColorCodes('&', "&aBye! &r&fCome Again Soon!"))
    }
}

/** This event is called when a command is executed.
 *  This is the only independently called event as it is used for command processing
 *  @warning DO NOT DELETE
 */
var OnPlayerCommandProcess = function(event, chat, managerapi, nhplugin){

    //This processes the command to check if we are handling the command in js or not
    var commandArgs = stringToCommand(event.getMessage())
    commandProcesser(event, event.getPlayer(), commandArgs[0], commandArgs, managerapi, nhplugin)

    eventHandler(event, managerapi);

}

var OnServerCommandEvent = function(event, chat, managerapi, nhplugin){
    var commandArgs = stringToCommand(event.getCommand())
    commandProcesser(event, event.getSender(), commandArgs[0], commandArgs, managerapi, nhplugin)

    eventHandler(event, managerapi);
}

var ds = function(sender,cmd, args){
    sender.sendMessage(sender.getDisplayName())
}

/** The main event handler entrypoint
 *  @param event The event which called the method from the Java Plugin
 *  @param managerapi The instance of the ManagerAPI plugin
 *  @param nhplugin The instance of the Nhplugin invoking the JS
 */
var onEvent = function(event, managerapi, nhplugin){
    eventHandler(event, managerapi, nhplugin);
}
