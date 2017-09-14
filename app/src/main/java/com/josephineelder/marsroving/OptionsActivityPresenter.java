package com.josephineelder.marsroving;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OptionsActivityPresenter {

    private OptionsActivityView view;
    private JsonParsing parser;
    private HttpConnecting httpConnector;
    private UrlBuilding urlBuilder;
    private IRoverStorage roverStorage;

    public OptionsActivityPresenter(OptionsActivityView view, JsonParsing parser, HttpConnecting httpConnector, IRoverStorage roverStorage, UrlBuilding urlBuilder) {
        this.view = view;
        this.parser = parser;
        this.httpConnector = httpConnector;
        this.roverStorage = roverStorage;
        this.urlBuilder = urlBuilder;
    }

    public void onResume() {
        httpConnector.doRequest("https://mars-photos.herokuapp.com/api/v1/rovers/", new HttpCallback() {
            @Override
            public void success(String json) {
                if (null != json && !"".equals(json)) {
                    final List<Rover> rovers = parser.getRovers(json);
                    roverStorage.setKnownRovers(rovers);
                    List<String> roverNames = new ArrayList<>();
                    roverNames.add("");
                    for (int i = 0; i < rovers.size(); i++) {
                        roverNames.add(rovers.get(i).name);
                    }
                    view.showRovers(roverNames);
                } else {
                    view.displayMessage("No rovers available");
                }
            }
            @Override
            public void failure(String responseCode) {
                System.out.println("Request failed with response code" + responseCode);
                view.displayMessage("No rovers available");
            }

        });
    }

    public void roverSelected(String roverName) {
        List<String> cameraNames = new ArrayList<>();
        Rover selectedRover = roverStorage.setSelectedRover(roverName);

        for (Camera camera : selectedRover.cameras) {
            cameraNames.add(camera.fullName);
        }

        view.showCameras(cameraNames);
    }

    public void getPhotosButtonTapped(String date, String camera) {
        Rover selectedRover = roverStorage.getSelectedRover();

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("max_sol", selectedRover.maxSol);
        if (!"".equals(date)) {
            queryParams.put("earth_date", date);
        }
        if (!"".equals(camera)) {
            queryParams.put("camera", camera);
        }

        String path = urlBuilder.buildUrl(selectedRover.name, queryParams);

        httpConnector.doRequest(path, new HttpCallback() {
            @Override
            public void success(String json) {
                view.displayMessage("Success with json\n" + json);
            }
            @Override
            public void failure(String responseCode) {
                view.displayMessage("Failure with code\n" + responseCode);
            }
        });
    }

}
