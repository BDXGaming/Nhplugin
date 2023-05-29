/**
 * This function is called every time the scripts are loaded/reloaded. It registers the events which are being listened.
 * Any run-on-start programs or commands are here.
 * @param {*} event
 * @param {*} nhplugin
 */
function scriptLoadEvent(event, nhplugin){

    nhplugin.listenedEvents.clear()
    var events = eventList
    var listenedEvents = nhplugin.getListenedEvents()

    for(var i =0; i<events.length; i++){

        if(!(nhplugin.getListenedEvents().contains(events[i]))){
            nhplugin.addListenedEvent(events[i])
        }

    }

    for(var i =0; i<listenedEvents.size; i++){

        var event = listenedEvents.get(i)

        if(!(events.includes(event))){
            nhplugin.removeListenedEvent(event)
        }

    }
}