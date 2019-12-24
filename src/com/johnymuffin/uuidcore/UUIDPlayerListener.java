package com.johnymuffin.uuidcore;

import org.bukkit.Bukkit;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;

import java.util.UUID;
import java.util.logging.Logger;

import static com.johnymuffin.uuidcore.UUIDMethods.getUUIDFromNameMojang;
import static com.johnymuffin.uuidcore.event.callPlayerUUIDEvent.callUUIDLogin;

public class UUIDPlayerListener extends PlayerListener {
    private UUIDCore plugin;

    public UUIDPlayerListener(UUIDCore p) {
        plugin = p;
    }


    @Override
    public void onPlayerJoin(PlayerJoinEvent event) {
        if(event.getPlayer() == null) {
            return;
        }

        if(plugin.getUUIDs().containsKey(event.getPlayer().getName())) {
            UUID playerUUID = plugin.getUUIDs().get(event.getPlayer().getName());
            if(playerUUID != null) {
                Bukkit.getServer().getLogger().info("[UUIDCore] Fetched UUID from Memory for: " + event.getPlayer().getName() + " | " + playerUUID.toString());
                //Run Event
                callUUIDLogin(event.getPlayer(), playerUUID);


                return;
            }
        }
        //Get UUID
        Bukkit.getServer().getLogger().info("[UUIDCore] Fetching Player UUID from Mojang for: " + event.getPlayer().getName());
        Bukkit.getServer().getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable() {

            @Override
            public void run() {
                try {
                    UUID playerUUID = getUUIDFromNameMojang(event.getPlayer().getName());
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                        @Override
                        public void run() {
                            if(event.getPlayer().isOnline()) {
                                Bukkit.getServer().getLogger().info("[UUIDCore] Fetched UUID from Mojang for: " + event.getPlayer().getName() + " | " + playerUUID.toString());
                                plugin.getUUIDs().put(event.getPlayer().getName(), playerUUID);
                                callUUIDLogin(event.getPlayer(), playerUUID);
                            }
                        }
                    }, 0L);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 0L);





    }



}
