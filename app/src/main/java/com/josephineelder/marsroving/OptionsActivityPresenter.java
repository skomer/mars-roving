package com.josephineelder.marsroving;

import java.util.ArrayList;
import java.util.List;

public class OptionsActivityPresenter {

    private OptionsActivityView view;
    private JsonParsing parser;
    private HttpConnecting httpConnector;
    private UrlBuilding urlBuilder;
    private List<Rover> rovers;
//    private Rover selectedRover;

    public OptionsActivityPresenter(OptionsActivityView view, JsonParsing parser, HttpConnecting httpConnector, UrlBuilding urlBuilder) {
        this.view = view;
        this.parser = parser;
        this.httpConnector = httpConnector;
        this.urlBuilder = urlBuilder;
    }

    public void onResume() {
        httpConnector.doRequest("https://mars-photos.herokuapp.com/api/v1/rovers/", new HttpCallback() {
            @Override
            public void success(String json) {
                if (null != json && !"".equals(json)) {
                    rovers = parser.getRovers(json);
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

        for (int i = 0; i < rovers.size(); i++) {
            if ((rovers.get(i).name).equals(roverName)) {
//                selectedRover = rovers.get(i);
                for (int j = 0; j < rovers.get(i).cameras.size(); j++) {
                    cameraNames.add(rovers.get(i).cameras.get(j).fullName);
                }
                break;
            }
        }

        view.showCameras(cameraNames);
    }

    public void getPhotosButtonTapped(String roverName, String date, String camera) {
        String path = urlBuilder.buildUrlWithEarthDate(roverName, date, camera);

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
