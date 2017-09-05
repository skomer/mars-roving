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
        List<String> actualList = parser.getRovers("");

        assertThat(actualList.size(), is(equalTo(0)));
    }

    @Test
    public void on_receiving_null_json_parser_returns_empty_arraylist() {
        JsonParser parser = new JsonParser();
        List<String> actualList = parser.getRovers(null);

        assertThat(actualList.size(), is(equalTo(0)));
    }

    @Test
    public void parses_to_list_of_rovers() {
        JsonParser parser = new JsonParser();

        List<String> actualRovers = parser.getRovers(json);
        List<String> expectedRovers = new ArrayList<>();
        expectedRovers.add("Opportunity");
        expectedRovers.add("Spirit");

        assertThat(expectedRovers, is(equalTo(actualRovers)));
    }

    String json = "{\"rovers\": [{ \"id\": 6, \"name\": \"Opportunity\"\",}, { \"id\": 7,\"name\": \"Spirit\",}]}";

}