package com.johnymuffin.uuidcore.event;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

public class callPlayerUUIDEvent {

    public static void callUUIDLogin(Player p, UUID u) {
        final PlayerUUIDEvent event = new PlayerUUIDEvent(p, u);
        Bukkit.getServer().getPluginManager().callEvent(event);
    }


}