package com.josephineelder.marsroving;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class OptionsActivityPresenterTest {

    private OptionsActivityView view;
    private OptionsActivityPresenter presenter;

    @Before
    public void setUp() {
        view = mock(OptionsActivityView.class);
        OptionsActivityPresenter presenter = new OptionsActivityPresenter(view);
    }

    @Test
    public void on_view_onResume_presenter_makes_request_for_rovers() {
        presenter.onResume();

        verify(presenter).getRovers();
    }

}