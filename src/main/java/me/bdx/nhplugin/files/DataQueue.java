package me.bdx.nhplugin.files;

import java.util.ArrayList;
import java.util.HashMap;

public class DataQueue {

    private HashMap<String, ArrayList<String>> joinQueue;

    public DataQueue(){
        joinQueue = new HashMap<>();
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
}
