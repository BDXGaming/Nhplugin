//Command loading
load("scripts/commands/test.js")
load("scripts/commands/info.js")
load("scripts/commands/creative.js")
load("scripts/commands/seen.js")
load("scripts/commands/ntphere.js")
load("scripts/commands/ntp.js")
load("scripts/commands/sbreload.js")

var cancelExemptCommands = ["seen", "gmc"]
var commandFunctions = {"test": testExport, "info":info, "gmc":gamemodeCreative,
 "seen": seen, "ntphere":ntphere, "ntp":ntp, "tphere":ntphere, "tp":ntp, "sbreload":sbreload}

var commandList = Object.keys(commandFunctions);

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
            //print((commandName === exemptCMD) + exemptCMD)
            if(commandName === exemptCMD){
                return false
            }
    }
    return true
}

function commandProcesser(event, player, commandName, args, managerapi, nhplugin){

    if(checkIfCommand(commandName)){
        if(checkIfCancelled(commandName)){
            event.setCancelled(true)
        }
        var func = commandFunctions[commandName]
        func(player, commandName, args, managerapi, event, nhplugin) //Pass the event
    }
}