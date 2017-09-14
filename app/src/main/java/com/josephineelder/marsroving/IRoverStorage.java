package com.josephineelder.marsroving;

import java.util.List;

public interface IRoverStorage {
    void setKnownRovers(List<Rover> rovers);
    Rover setSelectedRover(String roverName);
    Rover getSelectedRover();
}
