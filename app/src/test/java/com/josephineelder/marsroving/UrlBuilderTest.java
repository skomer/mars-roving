package com.josephineelder.marsroving;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.matchesPattern;

public class UrlBuilderTest {

    private UrlBuilding builder;

    @Before
    public void setUp() {
        builder = new UrlBuilder();
    }

    @Test
    public void builder_constructs_path_with_this_stem() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("earth_date", "");
        queryParams.put("camera", "");

        String expectedUrl = "(https://mars-photos.herokuapp.com/api/v1/rovers/roverName/photos?.*)";
        String actualUrl = builder.buildUrl("roverName", queryParams);

        assertThat(actualUrl, matchesPattern(expectedUrl));
    }

    @Test
    public void when_camera_provided_builder_constructs_path_with_camera() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("earth_date", "");
        queryParams.put("camera", "camera");

        String expectedUrl = "(https://mars-photos.herokuapp.com/api/v1/rovers/roverName/photos?.*.&camera=camera)";
        String actualUrl = builder.buildUrl("roverName", queryParams);

        assertThat(actualUrl, matchesPattern(expectedUrl));
    }

    @Test
    public void when_camera_not_provided_builder_constructs_path_with_no_camera() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("camera", "");

        String actualUrl = builder.buildUrl("roverName", queryParams);

        assertFalse(actualUrl.contains("&camera="));
    }

    @Test
    public void when_date_provided_builder_constructs_path_with_date() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("earth_date", "2017-01-01");
        queryParams.put("camera", "");

        String expectedUrl = "https://mars-photos.herokuapp.com/api/v1/rovers/roverName/photos?earth_date=2017-01-01";
        String actualUrl = builder.buildUrl("roverName", queryParams);

        assertEquals(expectedUrl, actualUrl);
    }

}