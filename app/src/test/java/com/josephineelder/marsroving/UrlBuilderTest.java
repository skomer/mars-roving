package com.josephineelder.marsroving;

import org.junit.Before;
import org.junit.Test;

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
        String expectedUrl = "(https://mars-photos.herokuapp.com/api/v1/rovers/roverName/photos?.*)";
        String actualUrl = builder.buildUrl("roverName", "", "");

        assertThat(actualUrl, matchesPattern(expectedUrl));
    }

    @Test
    public void when_camera_provided_builder_constructs_path_with_camera() {
        String expectedUrl = "(https://mars-photos.herokuapp.com/api/v1/rovers/roverName/photos?.*.&camera=camera)";
        String actualUrl = builder.buildUrl("roverName", "", "camera");

        assertThat(actualUrl, matchesPattern(expectedUrl));
    }

    @Test
    public void when_camera_not_provided_builder_constructs_path_with_no_camera() {
        String actualUrl = builder.buildUrl("roverName", "", "");

        assertFalse(actualUrl.contains("&camera="));
    }

    @Test
    public void when_date_provided_builder_constructs_path_with_date() {
        String expectedUrl = "https://mars-photos.herokuapp.com/api/v1/rovers/roverName/photos?earth_date=2017-01-01";
        String actualUrl = builder.buildUrl("roverName", "2017-01-01", "");

        assertEquals(expectedUrl, actualUrl);
    }

}