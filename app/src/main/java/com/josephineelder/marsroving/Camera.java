package com.josephineelder.marsroving;

import com.google.gson.annotations.SerializedName;

public class Camera {

    public final String name;
    @SerializedName("full_name")
    public final String fullName;

    public Camera(String name, String fullName) {
        this.name = name;
        this.fullName = fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Camera camera = (Camera) o;

        if (name != null ? !name.equals(camera.name) : camera.name != null) return false;
        return fullName != null ? fullName.equals(camera.fullName) : camera.fullName == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        return result;
    }
}
