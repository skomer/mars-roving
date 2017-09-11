package com.josephineelder.marsroving;

public class UrlBuilder implements UrlBuilding {

    public String buildUrl(String roverName, String date, String camera) {

        String basePath = "https://mars-photos.herokuapp.com/api/v1/rovers/";
        String path;
        if (null != camera && !"".equals(camera)) {
            path = basePath
                    + roverName
                    + "/photos?"
                    + ""
                    + "&camera="
                    + camera;
        } else {
            path = basePath
                    + roverName
                    + "/photos?";
        }

        return path;
    }


}
