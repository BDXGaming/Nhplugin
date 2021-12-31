function playerQuitEvent(event, managerapi, nhplugin){
    event.getPlayer().getInventory().clear()
    var chat = managerapi.getChatInstance()
    players = Bukkit.getServer().getOnlinePlayers().toArray()
    

    for(var i=0; i<players.length; i++){

        for(var i=0; i<players.length; i++){

            player = players[i]
            makePlayerScoreboard(player,chat,managerapi)
        }
    }
}