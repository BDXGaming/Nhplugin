function getOnlinePlayerNames(){

    var players =  Bukkit.getServer().getOnlinePlayers();
    var playerNames = []

    for(var i = 0; i < players.length; i++){
        var player = players[i]
        playerNames.push(player.getName())
    }
    return playerNames;
}

function getNetworkOnlinePlayers(managerapi){
    return managerapi.getGlobalPlayers().getOnlinePlayers()
}

function checkIfPlayerIsOnline(managerapi, playerName){
    var globalPlayers = getNetworkOnlinePlayers(managerapi)

    for(var i =0; i<globalPlayers.length; i++){
        if(playerName === globalPlayers[i]){
            return true
        }
    }
    return false
}

/** This method assumes that the player is currently online
 *  This will NOT work if the player is not currenlty in the onlinePlayers array
 *  @return serverName String
 */
function getPlayerServer(managerapi, player){
    return managerapi.getGlobalPlayers().getPlayerServer(player)
}