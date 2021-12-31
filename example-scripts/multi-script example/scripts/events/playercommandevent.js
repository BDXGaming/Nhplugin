//This event is called after the js command processor has ran (so could suffer from some delays and cant disable js commands)
function PlayerCommandPreprocessEvent(event, managerapi){

    chat = managerapi.getChatInstance()
    if(event.getMessage().contains("@staff")){
        var title = ChatColor.translateAlternateColorCodes('&', '&cStaff Alert')
        var subtext = ChatColor.translateAlternateColorCodes('&', '&bSee staff chat')
        event.getPlayer().sendTitle(title, subtext, 1, 100, 1);
        players = Bukkit.getServer().getOnlinePlayers().toArray()

        for(var i=0; i<players.length; i++){

            player = players[i]

            if(player.hasPermission("managerapi.staff")){
                player.sendTitle(title, subtext, 1, 100, 1);
            }

            
        }
    }

    else if(event.getMessage().contains("@dev")){
        var title = ChatColor.translateAlternateColorCodes('&', '&cDev Alert')
        var subtext = ChatColor.translateAlternateColorCodes('&', '&bSee dev chat')
        event.getPlayer().sendTitle(title, subtext, 1, 100, 1);
        players = Bukkit.getServer().getOnlinePlayers().toArray()

        for(var i=0; i<players.length; i++){

            player = players[i]

            if(player.hasPermission("nhplugin.dev")){
                player.sendTitle(title, subtext, 1, 100, 1);
            }

            
        }
    }

}