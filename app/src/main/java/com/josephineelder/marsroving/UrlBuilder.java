package com.josephineelder.marsroving;

public class UrlBuilder implements UrlBuilding {

    public String buildUrl(String roverName, String date, String camera) {
        String basePath = "https://mars-photos.herokuapp.com/api/v1/rovers/";
        String path = basePath
                + roverName
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




}
