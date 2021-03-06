package com.josephineelder.marsroving;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class RoverStorageTest {

    IRoverStorage storage;

    @Before
    public void setUp() {
        storage = new RoverStorage();
    }

    @Test
    public void set_selected_rover_with_rover_name_returns_selected_rover_object() {
        List<Rover> rovers = new ArrayList<>();
        rovers.add(new Rover("", "", "", new ArrayList<Camera>()));
        Rover expectedRover = new Rover("expectedRover", "", "", new ArrayList<Camera>());
        rovers.add(expectedRover);

        storage.setKnownRovers(rovers);
        Rover actualRover = storage.setSelectedRover("expectedRover");

        assertThat(actualRover, is(equalTo(expectedRover)));
    }

    @Test
    public void get_selected_rover_returns_rover_previously_set() {
        List<Rover> rovers = new ArrayList<>();
        rovers.add(new Rover("", "", "", new ArrayList<Camera>()));
        Rover selectedRover = new Rover("selectedRover", "", "", new ArrayList<Camera>());
        rovers.add(selectedRover);

        storage.setKnownRovers(rovers);
        storage.setSelectedRover("selectedRover");
        Rover actualRover = storage.getSelectedRover();

        assertThat(actualRover, is(equalTo(selectedRover)));
    }

}