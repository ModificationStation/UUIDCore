package com.johnymuffin.uuidcore.event;

import com.johnymuffin.uuidcore.models.UUIDStatus;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

public class callPlayerUUIDEvent {

    public static void callUUIDLogin(Player p, UUID u, boolean s) {
        final PlayerUUIDEvent event = new PlayerUUIDEvent(p, u, s);
        Bukkit.getServer().getPluginManager().callEvent(event);
    }


}