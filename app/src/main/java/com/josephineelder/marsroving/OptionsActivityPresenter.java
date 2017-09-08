package com.josephineelder.marsroving;

import java.util.ArrayList;
import java.util.List;

public class OptionsActivityPresenter {

    private OptionsActivityView view;
    private JsonParsing parser;
    private HttpConnecting httpConnector;
    List<Rover> rovers;

    public OptionsActivityPresenter(OptionsActivityView view, JsonParsing parser, HttpConnecting httpConnector) {
        this.view = view;
        this.parser = parser;
        this.httpConnector = httpConnector;
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

    public void roverSelected(int position) {
        Rover rover = rovers.get(position);
        List<String> roverNames = new ArrayList<>();

        for (int i = 0; i < rover.cameras.size(); i++) {
            roverNames.add(rover.cameras.get(i).fullName);
        }

        view.showCameras(roverNames);
    }

    public void getPhotosButtonTapped(String roverName, String camera, String date) {
        String basePath = "https://mars-photos.herokuapp.com/api/v1/rovers/";
        String path = basePath + roverName + "/photos?camera=" + camera + "&earth_date=" + date;

        httpConnector.doRequest(path, new HttpCallback() {
            @Override
            public void success(String json) {


            }
            @Override
            public void failure(String responseCode) {


            }
        });
    }

}
