package com.johnymuffin.uuidreciever;

import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;

public class UUIDReciever extends JavaPlugin {

    @Override
    public void onEnable() {
        final UUIDJoinListener UJL = new UUIDJoinListener(this);
        this.getServer().getPluginManager().registerEvent(Event.Type.CUSTOM_EVENT, UJL, Event.Priority.Lowest, this);
    }

    @Override
    public void onDisable() {

    }
}
