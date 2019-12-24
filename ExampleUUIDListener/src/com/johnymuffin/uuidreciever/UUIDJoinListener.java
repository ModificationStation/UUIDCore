package com.johnymuffin.uuidreciever;

import com.johnymuffin.uuidcore.event.PlayerUUIDEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.CustomEventListener;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;

public class UUIDJoinListener extends CustomEventListener implements Listener {

    public UUIDJoinListener(UUIDReciever uuidReciever) {

    }

    @Override
    public void onCustomEvent(Event event) {
        if(event instanceof PlayerUUIDEvent) {
            Bukkit.getServer().getLogger().info("Received UUID from Event: " + ((PlayerUUIDEvent) event).getPlayer().getName() + " | " + ((PlayerUUIDEvent) event).getPlayerUUID().toString());
        }
    }
}
