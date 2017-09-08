package com.josephineelder.marsroving;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class JsonParser implements JsonParsing {

    private final Gson gson = new Gson();

    public List<Rover> getRovers(String json) {
        List<Rover> roversFromJson = new ArrayList<>();

        if (json != null && !json.equals("")) {
            RootJson rootJson = gson.fromJson(json, RootJson.class);
            List<Rover> rovers = rootJson.getRovers();

            for (int i = 0; i < rovers.size(); i++) {
                Rover rover = rovers.get(i);
                List<Camera> cameras = new ArrayList<>();

                for (int j = 0; j < rover.cameras.size(); j++) {
                    Camera camera = new Camera(
                            rover.cameras.get(j).name,
                            rover.cameras.get(j).fullName
                    );
                    cameras.add(camera);
                }
                roversFromJson.add(new Rover(rover.name, cameras));
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

}
