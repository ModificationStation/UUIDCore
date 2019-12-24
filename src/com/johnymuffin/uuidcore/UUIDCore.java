package com.johnymuffin.uuidcore;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

import static com.johnymuffin.uuidcore.UUIDMethods.getUUIDFromNameMojang;


public class UUIDCore extends JavaPlugin {
    private Logger log;
    public static UUIDCore plugin;

    private static Map<String, UUID> playerUUIDs = new HashMap();


    @Override
    public void onEnable() {
        log = this.getServer().getLogger();
        log.info("[UUIDCore] Enabling");
        plugin = this;
        final UUIDPlayerListener UPL = new UUIDPlayerListener(plugin);
        getServer().getPluginManager().registerEvent(Event.Type.PLAYER_JOIN, UPL, Event.Priority.Highest, plugin);
        if(!Bukkit.getServer().getOnlineMode()) {
            log.info("[UUIDCore] This plugin is designed for servers running Online Mode, please remove this unless you are doing development :)");
        }

    }

    @Override
    public void onDisable() {
        log.info("[UUIDCore] Disabling");
        playerUUIDs = new HashMap();

    }


    public static Map<String, UUID> getUUIDs() {
        return playerUUIDs;
    }

    public Logger getLog() {
        return log;
    }

    public static UUIDCore getPlugin() {
        return plugin;
    }

    public UUID getUserUUID(String s, Boolean b) {
        //Boolean B is if method will contact Mojang
        if(UUIDCore.getUUIDs().containsKey(s)) {
            UUID playerUUID = UUIDCore.getUUIDs().get(s);
            if(playerUUID != null) {
                return playerUUID;
            }
        }
        if(b) {
            try {
                UUID playerUUID = getUUIDFromNameMojang(s);
                return playerUUID;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }
}
