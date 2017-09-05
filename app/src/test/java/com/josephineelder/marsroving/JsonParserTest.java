package com.josephineelder.marsroving;

import org.junit.Test;

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


}