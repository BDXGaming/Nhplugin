package me.bdx.nhplugin.events;

import com.google.common.io.ByteArrayDataInput;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class bungeeReceiveEvent extends Event {
    private String subchannel;
    private ByteArrayDataInput in;
    private static final HandlerList HANDLERS = new HandlerList();


    public bungeeReceiveEvent(String subchannel, ByteArrayDataInput in){
        this.subchannel = subchannel;
        this.in = in;
    }

    public String getSubchannel(){
        return subchannel;
    }

    public ByteArrayDataInput getDataIn(){
        return this.in;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public String getEventName(){
        return "bungeeReceiveEvent";
    }
}
