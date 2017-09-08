package com.josephineelder.marsroving;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class JsonParserTest {

    @Test
    public void on_receiving_empty_string_json_parser_returns_empty_arraylist() {
        JsonParser parser = new JsonParser();
        List<Rover> actualList = parser.getRovers("");

        assertThat(actualList.size(), is(equalTo(0)));
    }

    @Test
    public void on_receiving_null_json_parser_returns_empty_arraylist() {
        JsonParser parser = new JsonParser();
        List<Rover> actualList = parser.getRovers(null);

        assertThat(actualList.size(), is(equalTo(0)));
    }

    @Test
    public void parses_to_list_of_rovers() {
        JsonParser parser = new JsonParser();

        List<Rover> actualRovers = parser.getRovers(json);

        List<Rover> expectedRovers = new ArrayList<>();
        List<Camera> cameras = new ArrayList<>();
        cameras.add(new Camera("PANCAM", "Panoramic Camera"));
        expectedRovers.add(new Rover("Opportunity", "2000-01-01", "500", cameras));
        expectedRovers.add(new Rover("Spirit", "2000-01-01", "500", cameras));

        assertThat(expectedRovers, is(equalTo(actualRovers)));
    }

    private String json = "{\"rovers\": [{ \"id\": 6, \"name\": \"Opportunity\", \"landing_date\": \"2000-01-01\", \"max_sol\": \"500\", \"cameras\": [ { \"name\": \"PANCAM\", \"full_name\": \"Panoramic Camera\" } ]}, " +
            "{ \"id\": 7, \"name\": \"Spirit\", \"landing_date\": \"2000-01-01\", \"max_sol\": \"500\", \"cameras\": [ { \"name\": \"PANCAM\", \"full_name\": \"Panoramic Camera\" } ]} ]}";

}