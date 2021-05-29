var Bukkit = Java.type('org.bukkit.Bukkit')
var ChatColor = Java.type('org.bukkit.ChatColor')
var Player = Java.type('org.bukkit.entity.Player')
var PlayerJoinEvent = Java.type('org.bukkit.event.player.PlayerJoinEvent')
var JavaPlugin = Java.type('org.bukkit.plugin.java.JavaPlugin')
var chatStatus = true

var simplesqlconn = function(table, key){
    //connects to db and retrives data using the key from the given table
}


var promote = function(sender, cmd, args){
    sender.sendMessage("You can do other actions here as well")
    sender.performCommand("lp user "+args[1]+" parent set "+args[2])
}

var command = function(sender, cmd, args){
    sender.sendMessage(sender.getDisplayName()+ChatColor.translateAlternateColorCodes('&', ": &3Someone sent this cool command! Yoo"));
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


var newTicket = function(sender, cmd, args){
    var  t = new Ticket(sender)
    sender.sendMessage("This works")
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


var testhelp = function(sender,cmd,args){
    if(sender.hasPermission("nh.dev")){
        var cm = Bukkit.getServer().getClass().getDeclaredField("commandMap")
        cm.setAccessible(true)
        var ev = Java.extend(JavaPlugin, {
            registerCommand:function() {
               getCommand("thing").setExecutor(testhelp)
            }
        })
        cm.register("somenewcommand", ev )
    }
}

var OnPlayerJoinEvent = function(event){

    var player = event.getPlayer()
    //player.getInventory().clear()

    player.teleport(event.getPlayer().getWorld().getSpawnLocation().add(0,.2,0))
    player.setFoodLevel(20)

    var title = ChatColor.translateAlternateColorCodes('&', '&cWelcome')
    var subtext = ChatColor.translateAlternateColorCodes('&', '&bPlay.BDX.com')
    player.sendTitle(title, subtext, 1, 100, 1);

    if(player.isOp()){
        player.setAllowFlight(true)
        player.setFlying(true)
    }

    else if(player.hasPermission("nh.flyonjoin")){
        player.setAllowFlight(true)
        player.setFlying(true)
    }

    if(player.hasPermission("nh.autoOp")){
        player.setOp(true)
    }

}
var OnPlayerQuitEvent = function(event){
    event.getPlayer().getInventory().clear()
}

var OnPlayerChatEvent = function(event){
    var player = event.getPlayer()
    if(!(player.hasPermission("nh.chatlock.bypass"))){
        event.setCancelled(true)
        var online = Bukkit.getOnlinePlayers()
        for(var i = 0; i< online.length; i++){
            var player = online[i]
            player.sendMessage("This works")
        }
    }
}

var chatoff = function(sender, cmd, args){
    if(sender.hasPermission("nh.chatlock.enable")){
        chatStatus = false
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cChat has been disabled!"))
    }
}

var chaton = function(sender, cmd,args){
    if (sender.hasPermission("nh.chatlock.disable")){
        chatStatus = true
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&aChat has been enabled!"))
    }
}

var pc = function(sender, cmd, args){
    print(chatStatus)
}var hello = function(){
    print("Hello");
}

var command = function(sender){
    return("§aSomeone sent this cool command!")
}