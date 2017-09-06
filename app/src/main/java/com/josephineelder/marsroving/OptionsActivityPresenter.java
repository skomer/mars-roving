package com.josephineelder.marsroving;

import java.util.ArrayList;
import java.util.List;

public class OptionsActivityPresenter {

    OptionsActivityView view;
    JsonParsing parser;

    public OptionsActivityPresenter(OptionsActivityView view, JsonParsing parser) {
        this.view = view;
        this.parser = parser;
    }

    public void onResume() {
        List<String> rovers = new ArrayList<>();
        view.showRovers(rovers);
    }


}
