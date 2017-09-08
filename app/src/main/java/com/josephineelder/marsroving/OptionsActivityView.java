package com.josephineelder.marsroving;

import java.util.List;

public interface OptionsActivityView {

    void displayMessage(String message);
    void showRovers(List<String> rovers);
    void showCameras(List<String> cameras);

}
