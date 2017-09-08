package com.josephineelder.marsroving;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OptionsActivityPresenterTest {

    private OptionsActivityView view;
    private JsonParsing parser;
    private HttpConnecting httpConnector;
    private OptionsActivityPresenter presenter;

    @Before
    public void setUp() {
        view = mock(OptionsActivityView.class);
        parser = mock(JsonParsing.class);
        httpConnector = mock(HttpConnecting.class);
        presenter = new OptionsActivityPresenter(view, parser, httpConnector);
    }

    @Test
    public void on_view_onResume_presenter_tells_http_connector_to_make_request() {
        presenter.onResume();

        verify(httpConnector).doRequest(any(String.class), any(HttpCallback.class));
    }

    @Test
    public void presenter_passes_this_url_to_connector_for_request() {
        presenter.onResume();

        verify(httpConnector).doRequest(eq("https://mars-photos.herokuapp.com/api/v1/rovers/"), any(HttpCallback.class));
    }

    @Test
    public void on_callback_providing_empty_string_json_presenter_does_not_tell_parser_to_parse() {
        ArgumentCaptor<HttpCallback> captor = ArgumentCaptor.forClass(HttpCallback.class);

        presenter.onResume();
        verify(httpConnector).doRequest(any(String.class), captor.capture());
        captor.getValue().success("");

        verify(parser, times(0)).getRovers(any(String.class));
    }

    @Test
    public void on_callback_providing_null_json_presenter_does_not_tell_parser_to_parse() {
        ArgumentCaptor<HttpCallback> captor = ArgumentCaptor.forClass(HttpCallback.class);

        presenter.onResume();
        verify(httpConnector).doRequest(any(String.class), captor.capture());
        captor.getValue().success(null);

        verify(parser, times(0)).getRovers(any(String.class));
    }

    @Test
    public void on_callback_providing_empty_json_view_displays_message() {
        ArgumentCaptor<HttpCallback> captor = ArgumentCaptor.forClass(HttpCallback.class);

        presenter.onResume();
        verify(httpConnector).doRequest(any(String.class), captor.capture());
        captor.getValue().success("");

        verify(view).displayMessage("No rovers available");
    }

    @Test
    public void on_callback_providing_null_json_view_displays_message() {
        ArgumentCaptor<HttpCallback> captor = ArgumentCaptor.forClass(HttpCallback.class);

        presenter.onResume();
        verify(httpConnector).doRequest(any(String.class), captor.capture());
        captor.getValue().success(null);

        verify(view).displayMessage("No rovers available");
    }

    @Test
    public void on_callback_success_with_json_presenter_tells_view_to_show_rovers() {
        ArgumentCaptor<HttpCallback> captor = ArgumentCaptor.forClass(HttpCallback.class);
        List<Rover> rovers = new ArrayList<>();

        presenter.onResume();
        verify(httpConnector).doRequest(any(String.class), captor.capture());

        when(parser.getRovers("json")).thenReturn(rovers);
        captor.getValue().success("json");

        verify(view).showRovers(anyListOf(String.class));
    }

    @Test

        ArgumentCaptor<HttpCallback> captor = ArgumentCaptor.forClass(HttpCallback.class);
        presenter.onResume();
        verify(httpConnector).doRequest(any(String.class), captor.capture());
        when(parser.getRovers("json")).thenReturn(rovers);

        captor.getValue().success("json");

        List<String> expectedCameraNames = new ArrayList<>();
        expectedCameraNames.add("camera");

        presenter.roverSelected(0);

        verify(view).showCameras(expectedCameraNames);
    }

}