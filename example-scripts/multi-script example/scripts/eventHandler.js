load("scripts/events/playercommandevent.js")
load("scripts/events/globalchatevent.js")
load("scripts/events/tabcompleteevent.js")
load("scripts/events/bungeeReceiveEvent.js")
load("scripts/events/playerJoinEvent.js")
load("scripts/events/playerChatEvent.js")
load("scripts/events/playerQuitEvent.js")
load("scripts/events/globalChatReceiveEvent.js")
load("scripts/events/customPacketReceiveEvent.js")

var events = {"PlayerCommandPreprocessEvent": PlayerCommandPreprocessEvent,
 "GlobalChatEvent":GlobalChatEvent, "TabCompleteEvent":TabCompleteEvent,
  "bungeeReceiveEvent":bungeeReceiveEvent, "PlayerJoinEvent":playerJoinEvent,
  "AsyncPlayerChatEvent":playerChatEvent, "PlayerQuitEvent":playerQuitEvent,
  "globalChatReceiveEvent":globalChatReceiveEvent, "customPacketReceiveEvent":customPacketReceiveEvent}

var eventList = Object.keys(events);

function checkIfEvent(event){
    for(var i=0; i<=eventList.length; i++){
        var e = eventList[i]
        if(event.getEventName() === e){
            return true;
        }
    }
    return false;
}

function eventHandler(event, managerapi, nhplugin){

    if(checkIfEvent(event)){
        var func = events[event.getEventName()]
        func(event, managerapi, nhplugin)
    }
    
}