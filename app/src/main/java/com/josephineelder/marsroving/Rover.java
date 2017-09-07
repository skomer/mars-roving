package com.josephineelder.marsroving;

import java.util.List;

public class Rover {

    public final String name;
    public final List<Camera> cameras;

    public Rover(String name, List<Camera> cameras) {
        this.name = name;
        this.cameras = cameras;
    }

}
