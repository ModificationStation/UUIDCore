package com.johnymuffin.uuidcore;

import org.bukkit.entity.Player;

import java.util.BitSet;
import java.util.UUID;

import static com.johnymuffin.uuidcore.UUIDMethods.getUUIDFromNameMojang;
import static com.johnymuffin.uuidcore.event.callPlayerUUIDEvent.callUUIDLogin;

public class UUIDAPI {

    public static UUID getUserUUID(Player p, Boolean b) {
        if(p == null) {
            return null;
        }
        return getUserUUID(p.getName(), b);
    }

    public static UUID getUserUUID(String s, Boolean b) {
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
                UUIDCore.getUUIDs().put(s, playerUUID);
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
