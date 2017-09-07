package com.josephineelder.marsroving;

import java.util.List;

public class Rover {

    public final String name;
    public final List<String> cameras;

    public Rover(String name, List<String> cameras) {
        this.name = name;
        this.cameras = cameras;
    }

}
