load("scripts/utils/scoreboard.js");
load("scripts/utils/dataQueue.js");

function playerJoinEvent(event, managerapi, nhplugin){

    var player = event.getPlayer()
    if(checkIfPlayerInQueue(player.getName(), nhplugin)){
        var queueData = getQueueData(player.getName(), nhplugin)

        for(var i = 0; i <queueData.length; i++){
            var currentData = queueData[i]
            Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), currentData);
        }
        removeFromJoinQueue(player.getName(), nhplugin)
    }else{
        player.teleport(event.getPlayer().getWorld().getSpawnLocation().add(0,0,0))
        player.setFoodLevel(20)
    }

    var chat = managerapi.getChatInstance()

    if(player.isOp()){
        player.setAllowFlight(true)
        player.setFlying(true)
    }

    else if(player.hasPermission("essentials.fly")){
        player.setAllowFlight(true)
        player.setFlying(true)
    }

    else if(managerapi.getEssentials().getUser(player.getUniqueId()).isHidden()){
        player.setAllowFlight(true)
        player.setFlying(true)
    }
    
    players = Bukkit.getServer().getOnlinePlayers().toArray()

    for(var i=0; i<players.length; i++){

        player = players[i]
        
        makePlayerScoreboard(player, chat, managerapi)
    }

}