package com.johnymuffin.uuidcore;

import com.evilmidget38.NameFetcher;

import java.util.Arrays;
import java.util.UUID;

import static com.evilmidget38.UUIDFetcher.getUUIDOf;

public class UUIDMethods {

    public static UUID getUUIDFromNameMojang(String playerName) throws Exception {
        //Get UUID from Mojang API
        UUID UUID = null;
        UUID = getUUIDOf(playerName);
        return UUID;
    }

    public static String getNameFromUUIDMojang(UUID UUID) throws Exception {
        return (String) (new NameFetcher(Arrays.asList(new UUID[]{UUID}))).call().get(UUID);
    }
}
