package com.josephineelder.marsroving;

import java.util.List;

public class OptionsActivityPresenter {

    OptionsActivityView view;
    JsonParsing parser;
    HttpConnecting httpConnector;

    public OptionsActivityPresenter(OptionsActivityView view, JsonParsing parser, HttpConnecting httpConnector) {
        this.view = view;
        this.parser = parser;
        this.httpConnector = httpConnector;
    }

    public void onResume() {
        httpConnector.doRequest("https://mars-photos.herokuapp.com/api/v1/rovers/", new HttpCallback() {
            @Override
            public void success(String json) {
                List<String> rovers = parser.getRovers(json);
                view.showRovers(rovers);
            }
            @Override
            public void failure(String responseCode) {
                view.displayMessage("Request failed with response code" + responseCode);
            }

        });
    }

}
