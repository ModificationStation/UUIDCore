package com.evilmidget38;
import com.google.common.collect.ImmutableList;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class NameFetcher extends Object implements Callable<Map<UUID, String>> {
    private static final String PROFILE_URL = "https://sessionserver.mojang.com/session/minecraft/profile/";

    public NameFetcher(List<UUID> uuids) {
        this.jsonParser = new JSONParser();


        this.uuids = ImmutableList.copyOf(uuids);
    }
    private final JSONParser jsonParser; private final List<UUID> uuids;

    public Map<UUID, String> call() throws Exception {
        Map<UUID, String> uuidStringMap = new HashMap<UUID, String>();
        for (UUID uuid : this.uuids) {
            HttpURLConnection connection = (HttpURLConnection)(new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid.toString().replace("-", ""))).openConnection();
            JSONObject response = (JSONObject)this.jsonParser.parse(new InputStreamReader(connection.getInputStream()));
            String name = (String)response.get("name");
            if (name == null) {
                continue;
            }
            String cause = (String)response.get("cause");
            String errorMessage = (String)response.get("errorMessage");
            if (cause != null && cause.length() > 0) {
                throw new IllegalStateException(errorMessage);
            }
            uuidStringMap.put(uuid, name);
        }
        return uuidStringMap;
    }
}
