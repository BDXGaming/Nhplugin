load("scripts/utils/onlinePlayers.js")

/**
 * Sends the given player to the given servername using ConnectOther
 * @param {*} managerapi ManagerAPI
 * @param {*} server String
 * @param {*} nhplugin Nhplugin
 * @param {*} player String
 */
function sendPlayer(managerapi, server, nhplugin, player){
    var out = ByteStreams.newDataOutput();
    var sender = getFirstOnline()
    out.writeUTF("ConnectOther");
    out.writeUTF(player)
    out.writeUTF(server);
    sender.sendPluginMessage(nhplugin, "BungeeCord", out.toByteArray());
}