

function TabCompleteEvent(event, managerapi){
    var command = event.getBuffer();
    command = command.substring(1,command.length)
    var args = command.split(" ")
    var current = event.getCompletions()
    var players = managerapi.getGlobalPlayers().getOnlinePlayers()

    //event.getSender().sendMessage(args[0] + players)

    //Add all network online players to the seen command tab completion
    if(args[0] === "seen"){
        for(var i = 0; i < current.length; i++){
            current.remove(i);
        }
        StringUtil.copyPartialMatches(args[1], players, current);
        Collections.sort(current);
        event.setCompletions(current);
    }

    //Add the tab completion for subcommands of the /js command
    else if(args[0] === "js" ){
        
        if(args.length <=2){
            for(var i = 0; i < current.length; i++){
                current.remove(i);
            }
            current.addAll(["info", "test", "kick", "inv", "ds"])
        }

        event.setCompletions(current);
    }

    //Fix names being tab completed for /sc and /dc
    else if(args[0] === "sc" || args[0] === "dc"){
        for(var i = 0; i < current.length; i++){
            current.remove(i);
        }
        event.setCompletions(current);
    }

    else if(args[0] === "ntp" || args[0] === "ntphere" || args[0] === "tp" || args[0] === "tphere"){
        if(event.getSender().hasPermission("nh.networkteleport")){
            for(var i = 0; i < current.length; i++){
                current.remove(i);
            }
            StringUtil.copyPartialMatches(args[1], players, current);
            Collections.sort(current);
            event.setCompletions(current);
        } 
    }
}