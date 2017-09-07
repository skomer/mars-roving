package com.josephineelder.marsroving;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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
    
}