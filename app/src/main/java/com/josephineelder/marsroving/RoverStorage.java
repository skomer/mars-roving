package com.josephineelder.marsroving;

import java.util.ArrayList;
import java.util.List;

public class RoverStorage implements IRoverStorage {

    Rover selectedRover;
    List<Rover> rovers;

    public void setKnownRovers(List<Rover> rovers) {

    }

    public Rover setSelectedRover(String roverName) {

        return new Rover("", "", "", new ArrayList<Camera>());
    }

    public Rover getSelectedRover() {

        return new Rover("", "", "", new ArrayList<Camera>());
    }

}
