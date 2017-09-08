package com.josephineelder.marsroving;

import java.util.List;

public class Rover {

    public final String name;
    public final List<Camera> cameras;

    public Rover(String name, List<Camera> cameras) {
        this.name = name;
        this.cameras = cameras;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rover rover = (Rover) o;

        if (name != null ? !name.equals(rover.name) : rover.name != null) return false;
        return cameras != null ? cameras.equals(rover.cameras) : rover.cameras == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (cameras != null ? cameras.hashCode() : 0);
        return result;
    }
}
