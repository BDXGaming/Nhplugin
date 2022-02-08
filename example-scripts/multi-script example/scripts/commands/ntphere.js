load("scripts/utils/onlinePlayers.js")
load("scripts/utils/dataQueue.js")

/** sends the given player to the server where the sender is located */
function ntphere(sender, cmd, args, managerapi, event, nhplugin){
    if(sender.hasPermission("nh.networkteleport.here")){
        if (args.length >1){

        var user = args[1]
        if(!checkIfPlayerIsOnline(managerapi, user)){
            print(user)
            sender.sendMessage(ChatColor.RED + "Error: "+ ChatColor.GOLD + "The Player "+ ChatColor.RED +"" +user+"" + ChatColor.GOLD + " is currently not online!")
            return
        }

        var playerServer = getPlayerServer(managerapi, user)

        if(playerServer === bungeeServerName){
            var player = Bukkit.getPlayer(user)
            player.teleport(sender.getLocation());
            

        }else{
            var out = ByteStreams.newDataOutput();
            out.writeUTF("ConnectOther");
            out.writeUTF(user)
            out.writeUTF(bungeeServerName);
            sender.sendPluginMessage(nhplugin, "BungeeCord", out.toByteArray());
            
            addJoinQueue(user, ["minecraft:tp "+user+" "+sender.getName()], nhplugin)
        }

        sender.sendMessage(ChatColor.GOLD + "Teleported Player "+ ChatColor.RED + user + ChatColor.GOLD + " to your location!")
    }
    }else{
        sender.sendMessage(ChatColor.RED + permissionErrorMessage)
    }

}
}