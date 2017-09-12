package com.josephineelder.marsroving;

import java.util.Map;

public interface UrlBuilding {

    String buildUrl(String roverName, Map<String, String> queryParams);

}
