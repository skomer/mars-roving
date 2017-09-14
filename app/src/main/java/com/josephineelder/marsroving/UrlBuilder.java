package com.josephineelder.marsroving;

import java.util.Map;

public class UrlBuilder implements UrlBuilding {

    public String buildUrl(String roverName, Map<String, String> queryParams) {
        String basePath = "https://mars-photos.herokuapp.com/api/v1/rovers/";
        String path = basePath
                + roverName
                + "/photos?";

        String selectedDate = queryParams.get("earth_date");

        if (null != selectedDate) {
            path += "earth_date=" + selectedDate;
        }

        String selectedCamera = queryParams.get("camera");
        if (null != selectedCamera) {
            path += "&camera=" + selectedCamera;
        }

        return path;
    }

}
