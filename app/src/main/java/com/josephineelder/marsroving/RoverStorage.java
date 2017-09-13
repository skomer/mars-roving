package com.josephineelder.marsroving;

import java.util.ArrayList;
import java.util.List;

public class RoverStorage implements IRoverStorage {

    Rover selectedRover;
    List<Rover> knownRovers;

    public void setKnownRovers(List<Rover> rovers) {
        knownRovers = rovers;
    }

    public Rover setSelectedRover(String roverName) {
        for (Rover rover : knownRovers) {
            if (rover.name.equals(roverName)) {
                selectedRover = rover;
            }
        }

        return selectedRover;
    }

    public Rover getSelectedRover() {

        return new Rover("", "", "", new ArrayList<Camera>());
    }

}
