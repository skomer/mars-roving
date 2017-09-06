package com.josephineelder.marsroving;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class OptionsActivityPresenterTest {

    private OptionsActivityView view;
    private OptionsActivityPresenter presenter;

    @Before
    public void setUp() {
        view = mock(OptionsActivityView.class);
        presenter = new OptionsActivityPresenter(view);
    }

    @Test
    public void on_view_onResume_tells_view_to_show_results_of_request_for_rovers() {
        presenter.onResume();

        verify(view).showRovers(anyListOf(String.class));
    }

}