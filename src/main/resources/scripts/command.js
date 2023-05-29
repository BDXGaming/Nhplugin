// loads all the functions which are used as command handlers

// The list of commands which are NOT cancelled by default
var cancelExemptCommands = []

// The Map of String command names to functions which handle the commands
var commandFunctions = {}

// The list of commands which are handled by JS
var commandList = Object.keys(commandFunctions);

/**
 * Checks if the given name is a name of a command
 * @param {*} commandName The command name to check
 * @returns
 */
function checkIfCommand(commandName){
    for(var i=0; i<=commandList.length; i++){
        var cmd = commandList[i]
        if(commandName === cmd){
            return true;
        }
    }
    return false;
}

/** Checks if the command is cancelled before the command event is called
 *  @param commandName The name of the command being checked
 *  @return isCancelled Whether or not the event should be cancelled before calling the command
 */
function checkIfCancelled(commandName){
    for(var i =0; i < cancelExemptCommands.length; i++){
            var exemptCMD = cancelExemptCommands[i]
            if(commandName === exemptCMD){
                return false
            }
    }
    return true
}

/**
 * Processes the command and command events and cancels the non-exempt commands
 * @param {*} event
 * @param {*} player
 * @param {*} commandName
 * @param {*} args
 * @param {*} nhplugin
 */
function commandProcesser(event, player, commandName, args, nhplugin){

    // Checks if the command is a listened to command
    if(checkIfCommand(commandName)){

        // Checks if the command is a non-cancelled event (PlayerCommandPreprocessEvent)
        if(checkIfCancelled(commandName)){
            event.setCancelled(true)
        }

        // Gets the function which handles the command
        var func = commandFunctions[commandName]

        // Passes the args of the PlayerCommandPreprocessEvent
        func(player, commandName, args, event, nhplugin)
    }
}