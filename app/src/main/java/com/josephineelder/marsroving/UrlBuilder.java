package com.josephineelder.marsroving;

import java.util.Map;

public class UrlBuilder implements UrlBuilding {

    public String buildUrl(String roverName, Map<String, String> queryParams) {
        String basePath = "https://mars-photos.herokuapp.com/api/v1/rovers/";
        String path = basePath
                + roverName
                + "/photos?";

//        if (null != date && !"".equals(date)) {
//            path += "earth_date=" + date;
//        }
//
//        if (null != camera && !"".equals(camera)) {
//            path += ""
//                    + "&camera="
//                    + camera;
//        }

        return path;
    }

}
