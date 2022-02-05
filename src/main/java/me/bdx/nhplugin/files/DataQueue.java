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

    public HashMap<String, ArrayList<String>> getJoinQueue(){
        return joinQueue;
    }

    public void addJoinQueue(String key, ArrayList<String> value){
        joinQueue.put(key, value);
    }

    public void removeJoinQueue(String key, ArrayList<String> value){
        joinQueue.remove(key, value);
    }

    public void removeJoinQueue(String key){
        joinQueue.remove(key);
    }

    public HashMap<String, String> getGeneralDataQueue() {
        return generalDataQueue;
    }

    public void setGeneralDataQueue(HashMap<String, String> generalDataQueue) {
        this.generalDataQueue = generalDataQueue;
    }

    public void addGeneralQueue(String key, String value){
        generalDataQueue.put(key,value);
    }
}
