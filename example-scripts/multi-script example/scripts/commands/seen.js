load("scripts/utils/onlinePlayers.js")

/** Checks if the player is on the current server
 *  Iterates over the players on the local server and those on the network
 *  If the player is on the local server returns true, else returns false
 *  @param player The player who is being checked
 *  @param serverPlayerList the list of players on the current server
 *  @param managerapi the managerapi instance
 *  @return playerServerStatus
 */
function checkIfPlayerIsOnServer(player, serverPlayerList, managerapi){
    for(var i = 0; i <serverPlayerList.length; i++){
        if(serverPlayerList[i] === player){
            return true
        }
    }

    var networkOnline = getNetworkOnlinePlayers(managerapi)
    for(var i = 0; i <networkOnline.length; i++){
        if(networkOnline[i] === player){
            return false
        }
    }
    return true;
}

function seen(player, cmd, args, managerapi, event){

    /** Ensures that the command is executed with a player as an arg (or any args) */
    if(args.length <=1){
        event.setCancelled(true)
        player.sendMessage(ChatColor.RED + "You must provide a player!")
        return
    }

    var players = getOnlinePlayerNames()
    var target = args[1]

    /** Checks if the player is on the current server
     *  If they are on the current server then the regular seen command is executed
     *  Otherwise the JS implementation is executed
     */
    if(checkIfPlayerIsOnServer(target, players, managerapi)){
        return
    }

    /** Ensures that the player has the required permission to use the command */
    if(!player.hasPermission("essentials.seen")){
        event.setCancelled(true)
        player.sendMessage(ChatColor.RED + permissionErrorMessage)
        return
    }

    event.setCancelled(true);
    var server = getPlayerServer(managerapi, target)
    var offlinePlayer = Bukkit.getOfflinePlayer(target)
    player.sendMessage(ChatColor.GOLD + "Player "+ ChatColor.RED + target + ChatColor.GOLD + " is currently "+ChatColor.GREEN+"online"+ChatColor.GOLD+" on " + ChatColor.RED+ server)
    if(player.hasPermission("essentials.seen.location")){
        player.sendMessage(ChatColor.GOLD +" - UUID: "+ ChatColor.WHITE + offlinePlayer.getUniqueId().toString())
    }
    if(player.hasPermission("essentials.seen.ip")){
        player.sendMessage(ChatColor.GOLD +" - IP Address: "+ ChatColor.WHITE +"/"+ managerapi.getEssentials().getUser(target).getLastLoginAddress().toString())
    }
 
}