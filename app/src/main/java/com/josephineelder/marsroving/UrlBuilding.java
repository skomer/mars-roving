package com.josephineelder.marsroving;

public interface UrlBuilding {

    String buildUrl(Rover selectedRover, String date, String camera);

    String buildUrlWithEarthDate(String roverName, String selectedDate, String camera);

}
