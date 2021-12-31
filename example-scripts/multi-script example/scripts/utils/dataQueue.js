/** Adds the given key(playerName) and Value to the joinQueue
 *  @param key The playerName
 *  @param value The Arraylist of what is to be performed when the player joins
 */
 function addJoinQueue(key, value, nhplugin){

    var a = new ArrayList()
    for(var i=0; i<value.length; i++){
        a.add(value[i])
    }
    nhplugin.getDataQueue().addJoinQueue(key, a)
}

/** Gets the join queue
 *  @param nhplugin the Nhplugin instance
 *  @return joinQueue The ArrayList join queue
*/
function getJoinQueue(nhplugin){
    return nhplugin.getDataQueue().getJoinQueue()
}

/** Checks if the given player is in the data queue
 *  @param player The name of the player being checked for
 *  @param nhplugin The Nhplugin instance
 *  @return boolean Whether or not the player is present in the joinQueue
 */
function checkIfPlayerInQueue(player, nhplugin){
    var queue = getJoinQueue(nhplugin);
    return queue.containsKey(player)
}

/** Gets the queue data for the given playerName. Assumes that playerName is a valid data key
 *  @param player The name of the player
 *  @param nhplugin The Nhplugin instance
 *  @return The queue data for the player
 */
function getQueueData(player, nhplugin){
    var queue = getJoinQueue(nhplugin);

    return queue.get(player)
}

/** Removes the given player from the joinQueue
 *  @param playerName The name of the player being removed from the Queue
 *  @param nhplugin The instance of the Nhplugin
 */
function removeFromJoinQueue(playerName, nhplugin){
    nhplugin.getDataQueue().removeJoinQueue(playerName)
}