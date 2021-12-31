load("scripts/utils/dataQueue.js")

function customPacketReceiveEvent(event, managerapi, nhplugin){
    var packet = event.getCustomPacket()
    packet = JSON.parse(packet)

    if(packet["type"] === "customPacket-string"){
        var cp = packet['custompacket']
        var user = cp.split(" ")
        user = user[1]
        addJoinQueue(user,[cp] , nhplugin)
    }

}