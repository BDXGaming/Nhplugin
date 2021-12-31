load("scripts/utils/scoreboard.js");

function sbreload(player, cmd, args, managerapi, event){
    var chat = managerapi.getChatInstance()
    players = Bukkit.getServer().getOnlinePlayers().toArray()

    for(var i=0; i<players.length; i++){

        player = players[i]
        
        makePlayerScoreboard(player, chat, managerapi)
    }
}