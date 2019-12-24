package com.johnymuffin.uuidcore;

import java.util.UUID;

import static com.evilmidget38.UUIDFetcher.getUUIDOf;

public class UUIDMethods {

    public static UUID getUUIDFromNameMojang(String playerName) throws Exception {
        //Get UUID from Mojang API
        UUID UUID = null;
        UUID = getUUIDOf(playerName);
        return UUID;
    }


}
