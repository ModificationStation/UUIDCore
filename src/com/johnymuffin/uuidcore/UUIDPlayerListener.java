package com.johnymuffin.uuidcore;

import org.bukkit.Bukkit;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;

import java.util.UUID;

import static com.johnymuffin.uuidcore.UUIDMethods.getUUIDFromNameMojang;
import static com.johnymuffin.uuidcore.event.callPlayerUUIDEvent.callUUIDLogin;

public class UUIDPlayerListener extends PlayerListener {
    private UUIDCore plugin;

    public UUIDPlayerListener(UUIDCore p) {
        plugin = p;
    }


    @Override
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (event.getPlayer() == null) {
            return;
        }

        if (plugin.getUUIDs().containsKey(event.getPlayer().getName())) {
            UUID playerUUID = plugin.getUUIDs().get(event.getPlayer().getName());
            if (playerUUID != null) {
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
                UUID playerUUID = null;
                try {
                    playerUUID = getUUIDFromNameMojang(event.getPlayer().getName());
                } catch (Exception e) {
                    //Unable to get UUID Info
                    playerUUID = null;
                } finally {
                    UUID finalPlayerUUID = playerUUID;
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                        @Override
                        public void run() {
                            callUUIDLogin(event.getPlayer(), finalPlayerUUID);
                            if(finalPlayerUUID == null) {
                                Bukkit.getServer().getLogger().info("[UUIDCore] UUID Fetch from Mojang failed for : " + event.getPlayer().getName());
                            } else {
                                Bukkit.getServer().getLogger().info("[UUIDCore] Fetched UUID from Mojang for: " + event.getPlayer().getName() + " | " + finalPlayerUUID.toString());
                                plugin.getUUIDs().put(event.getPlayer().getName(), finalPlayerUUID);
                            }

                        }
                    }, 0L);
                }
            }
        }, 0L);


    }


}
