package com.johnymuffin.uuidcore.event;

import org.bukkit.entity.Player;

import java.util.UUID;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

class PlayerUUIDEvent extends Event implements Cancellable{
    private boolean isCancelled = false;
    private Player player;
    private UUID UUID;
    public PlayerUUIDEvent(Player p, UUID u) {
        super("UUIDCoreLoginEvent");
        UUID = u;
        player = p;

    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        isCancelled = b;

    }

    public Player getPlayer() {
        return player;
    }

    public UUID getPlayerUUID() {
        return UUID;
    }

}

public class callPlayerUUIDEvent {

    public static void callUUIDLogin(Player p, UUID u) {
        final PlayerUUIDEvent event = new PlayerUUIDEvent(p, u);

    }


}