package me.bdx.nhplugin.files;

import java.util.ArrayList;
import java.util.HashMap;

public class DataQueue {

    private HashMap<String, ArrayList<String>> joinQueue;
    private HashMap<String, String> generalDataQueue;

    public DataQueue(){
        joinQueue = new HashMap<>();
        generalDataQueue = new HashMap<>();
    }

    /**
     * Gets the current join data queue hashmap
     * @return hashmap
     */
    public HashMap<String, ArrayList<String>> getJoinQueue(){
        return joinQueue;
    }

    /**
     * Adds the key-value pair to the join queue
     * @param key String
     * @param value ArrayList
     */
    public void addJoinQueue(String key, ArrayList<String> value){
        joinQueue.put(key, value);
    }

    /**
     * Removes the given key-value from the join queue
     * @param key String
     * @param value ArrayList
     */
    public void removeJoinQueue(String key, ArrayList<String> value){
        joinQueue.remove(key, value);
    }

    /**
     * Removes the given key from the hashmap
     * @param key String
     */
    public void removeJoinQueue(String key){
        joinQueue.remove(key);
    }

    /**
     * Gets the general data queue
     * @return The hashmap of the general data queue
     */
    public HashMap<String, String> getGeneralDataQueue() {
        return generalDataQueue;
    }

    /**
     * Sets the general data queue to the given hashmap
     * @param generalDataQueue HashMap of String and String
     */
    public void setGeneralDataQueue(HashMap<String, String> generalDataQueue) {
        this.generalDataQueue = generalDataQueue;
    }

    /**
     * Adds the given key-value pair to the general data queue
     * @param key String
     * @param value String
     */
    public void addGeneralQueue(String key, String value){
        generalDataQueue.put(key,value);
    }
}
