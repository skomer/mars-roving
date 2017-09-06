package com.josephineelder.marsroving;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class JsonParser {

    private final Gson gson = new Gson();

    public List<String> getRovers(String json) {
        List<String> roversFromJson = new ArrayList<>();

        if (!json.equals("")) {
            RootJson rootJson = gson.fromJson(json, RootJson.class);
            List<Rover> rootRovers = rootJson.getRovers();

            for (int i = 0; i < rootRovers.size(); i++) {
                roversFromJson.add(rootRovers.get(i).name);
            }
        }
        return roversFromJson;
    }

    private static class RootJson {
        List<Rover> rovers;

        public List<Rover> getRovers() {
            return rovers;
        }
    }

    private static class Rover {
        String name;
    }

}
