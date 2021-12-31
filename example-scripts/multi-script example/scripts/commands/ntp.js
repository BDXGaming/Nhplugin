load("scripts/utils/onlinePlayers.js")

function ntp(sender, cmd, args, managerapi, event, nhplugin){
    if(args.length < 1){
        sender.sendMessage(ChatColor.YELLOW + "A user is required for this command!")
        return
    }
    if(sender.hasPermission("nh.networkteleport")){
        var user = args[1]
        var playerServer = getPlayerServer(managerapi, user)

        if(playerServer === bungeeServerName){
            var player = Bukkit.getPlayer(user)
            sender.teleport(player.getLocation());
            sender.sendMessage(ChatColor.GOLD + "Teleported to "+ ChatColor.RED + user)

        }else{
            var out = ByteStreams.newDataOutput();
            out.writeUTF("Connect");
            out.writeUTF(playerServer);
            sender.sendMessage(ChatColor.GOLD + "Teleported to "+ ChatColor.RED + user)
            sender.sendPluginMessage(nhplugin, "BungeeCord", out.toByteArray());
            managerapi.getChatapi().sendCustomPacket("minecraft:tp "+sender.getName() +" "+ user);
            
            //addJoinQueue(user, ["minecraft:tp "+user+" "+sender.getName()], nhplugin)
        }
    }else{
        sender.sendMessage(ChatColor.RED + permissionErrorMessage)
    }

}