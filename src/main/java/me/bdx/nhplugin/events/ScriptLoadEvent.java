package me.bdx.nhplugin.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ScriptLoadEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();

    public ScriptLoadEvent(){
        super(false);
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }


}
