function playerChatEvent(event, managerapi, nhplugin){
    var player = event.getPlayer()
    //Bukkit.broadcast(managerapi.getEssentials().getUser(event.getPlayer()).getNick()+": "+ChatColor.GRAY + event.getMessage() , "managerapi.chat")
    if(event.getMessage().contains("@everyone")){

        if(event.getPlayer().hasPermission("nhplugin.alert.all")){
            var title = ChatColor.translateAlternateColorCodes('&', '&cAlert')
            var subtext = ChatColor.translateAlternateColorCodes('&', '&bSee chat')
            player.sendTitle(title, subtext, 1, 100, 1);
            
            players = Bukkit.getServer().getOnlinePlayers().toArray()

            for(var i=0; i<players.length; i++){

                player = players[i]

                player.sendTitle(title, subtext, 1, 100, 1);
            }
        }

        
    }

    

    if(!(player.hasPermission("nh.chatlock.bypass"))){
        //event.setCancelled(true)
        // var online = Bukkit.getOnlinePlayers()
        // for(var i = 0; i< online.length; i++){
        //     var player = online[i]
        //     player.sendMessage("This works")
        // }
    }

    if(event.getMessage().contains("[tp]")){
        player.sendMessage("This feature is not yet ready!")
    }
}