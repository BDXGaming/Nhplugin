package me.bdx.nhplugin.events;

import com.google.common.io.ByteArrayDataInput;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class BungeeReceiveEvent extends Event {
    private String subchannel;
    private ByteArrayDataInput in;
    private static final HandlerList HANDLERS = new HandlerList();

    /**
     * Creates an instance of the custom event for receiving BungeeCord messages
     */
    public BungeeReceiveEvent(String subchannel, ByteArrayDataInput in){
        this.subchannel = subchannel;
        this.in = in;
    }

    /**
     * Gets the subchannel of the bungeecord event
     * @return The string of the subchannel
     */
    public String getSubchannel(){
        return subchannel;
    }

    /**
     * Gets the data in
     * @return ByteArrayDataInput of the input
     */
    public ByteArrayDataInput getDataIn(){
        return this.in;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    /**
     * Gets the name of the event
     * @return The string name of the event
     */
    public String getEventName(){
        return "bungeeReceiveEvent";
    }
}
