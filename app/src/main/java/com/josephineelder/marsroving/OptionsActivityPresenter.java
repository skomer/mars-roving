package com.josephineelder.marsroving;

import java.util.ArrayList;
import java.util.List;

public class OptionsActivityPresenter {

    OptionsActivityView view;

    public OptionsActivityPresenter(OptionsActivityView view) {
        this.view = view;
    }

    public void onResume() {
        List<String> rovers = new ArrayList<>();
        view.showRovers(rovers);
    }


}
