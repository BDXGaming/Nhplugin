//The exact match filtered words
var filteredWords = ["hat","rat"];

//The string contains any filter (these are the like super bad words which need to be filtered)
var anyMatchWords = ["loser", "loverfella"];

var whitelistedWords = ["chat", "crate", "that"];

/** This method alerts all users with the mapi.filter perm to the new chatfiltered message */
function alertStaffFilter(player, message, filter, managerapi){
    managerapi.getGlobalChat().broadcast("\n"+ChatColor.YELLOW +ChatColor.BOLD+ "User Filtered: "+ChatColor.WHITE + player.getName() +ChatColor.YELLOW +ChatColor.YELLOW+ "\nWas filtered for: " + ChatColor.RED + filter + ChatColor.YELLOW + " in " + ChatColor.RED + message, "mapi.filter")
}

/** This method checks if the message and filtered word are in the whitelist, returns true if so, false if not */
function checkFilter(filteredWord, message){
    var messageArgs = message.split(" ");
    var filteredMessageWord;


    for(var i = 0; i< messageArgs.length; i++){
        var word = messageArgs[i];
        if(word.contains(filteredWord)){
            filteredMessageWord = word
        }
    }
    for(var x = 0; x < whitelistedWords.length; x++){
        if(whitelistedWords[x] === filteredMessageWord){
            return true
        }
    }
    return false;
}

function GlobalChatEvent(event, managerapi){
    
    //The status of chat (true for enabled and false for disabled)
    var chatStatus = true
    if(!chatStatus){
        if(!event.getPlayer().hasPermission("managerapi.chat.bypass")){
            event.setCancelled(true)
            event.getPlayer().sendMessage(ChatColor.RED + "Chat is currently disabled!")
        }
    }
    var singleMessage = event.getMessage().toLowerCase().split(' ').join('')
    for(var i = 0; i< anyMatchWords.length; i++){
        if(singleMessage.contains(anyMatchWords[i])){

            if(!checkFilter(anyMatchWords[i], event.getMessage())){
                event.setCancelled(true)
                event.getPlayer().sendMessage(ChatColor.RED + "You are being filtered!")
                alertStaffFilter(event.getPlayer(), event.getMessage(), anyMatchWords[i], managerapi)

                break
            }
            else{
                break
            }

        }
    }

    for(var i = 0; i< filteredWords.length; i++){
        if(event.getMessage().toLowerCase().contains(filteredWords[i])){

                if(!checkFilter(filteredWords[i], event.getMessage())){
                    event.setCancelled(true)
                    event.getPlayer().sendMessage(ChatColor.RED + "You are being filtered!")
                    alertStaffFilter(event.getPlayer(), event.getMessage(), filteredWords[i], managerapi)

                    break
                }
                else{
                    break
                }

        }
    }
    
}
