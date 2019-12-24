package com.johnymuffin.uuidcore.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

import java.util.UUID;

public class PlayerUUIDEvent extends Event implements Cancellable {
    private boolean isCancelled = false;
    private Player player;
    private java.util.UUID UUID;
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
