load(fileLocationBase+"events/scriptLoadEvent.js")

// This is the Map of Event Name Strings to functions which will handle the events
var events = {"ScriptLoadEvent":scriptLoadEvent}

// A list of the String names for all listened to events
var eventList = Object.keys(events);

/**
 * Checks if the given event is one currently handled
 * @param {*} event
 * @returns
 */
function checkIfEvent(event){
    for(var i=0; i<=eventList.length; i++){
        var e = eventList[i]
        if(event.getEventName() === e){
            return true;
        }
    }
    return false;
}

/**
 * The main funciton which calls the event handler functions
 * @param {*} event
 * @param {*} nhplugin
 */
function eventHandler(event, nhplugin){

    if(checkIfEvent(event)){
        var func = events[event.getEventName()]
        func(event, nhplugin)
    }

}