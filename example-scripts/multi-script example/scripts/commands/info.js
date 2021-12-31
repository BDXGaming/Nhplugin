function info(sender, cmd, args, managerapi, event, nhplugin){
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