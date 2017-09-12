package com.josephineelder.marsroving;

public class UrlBuilder implements UrlBuilding {

    public String buildUrl(Rover selectedRover, String date, String camera) {
        String basePath = "https://mars-photos.herokuapp.com/api/v1/rovers/";
        String path = basePath
                + selectedRover.name
                + "/photos?";

        if (null != date && !"".equals(date)) {
            path += "earth_date=" + date;
        }

        if (null != camera && !"".equals(camera)) {
            path += ""
                    + "&camera="
                    + camera;
        }

        return path;
    }

    public String buildUrlWithEarthDate(String roverName, String selectedDate, String camera) {

        return "";
    }



}
