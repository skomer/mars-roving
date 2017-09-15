package com.josephineelder.marsroving;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

public class OptionsActivity extends AppCompatActivity implements OptionsActivityView, AdapterView.OnItemSelectedListener, Button.OnClickListener {

    Spinner roversSpinner;
    Spinner camerasSpinner;
    TextView dateTextView;
    Button datePickerButton;
    Button getPhotosButton;
    OptionsActivityPresenter presenter;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        roversSpinner = (Spinner) findViewById(R.id.rovers_spinner);
        roversSpinner.setOnItemSelectedListener(this);
        camerasSpinner = (Spinner) findViewById(R.id.cameras_spinner);
        camerasSpinner.setOnItemSelectedListener(this);
        dateTextView = (TextView) findViewById(R.id.date_textview);
        datePickerButton = (Button) findViewById(R.id.select_date_button);
        datePickerButton.setOnClickListener(this);
        getPhotosButton = (Button) findViewById(R.id.get_photos_button);
        getPhotosButton.setOnClickListener(this);

        presenter = new OptionsActivityPresenter(this, new JsonParser(), new HttpConnector(), new RoverStorage(), new UrlBuilder());
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    public void showRovers(final List<String> roverNames) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ArrayAdapter adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, roverNames) {};
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                roversSpinner.setAdapter(adapter);
            }
        });
    }

    public void showCameras(final List<String> cameras) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ArrayAdapter adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, cameras) {};
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                camerasSpinner.setAdapter(adapter);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.get_photos_button:
                String cameraName = camerasSpinner.getTransitionName();
                presenter.getPhotosButtonTapped("", cameraName);
                break;
            case R.id.select_date_button:
                showDatePickerDialog(view);
                break;
        }
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        switch (parent.getId()) {
            case R.id.rovers_spinner:
                String roverName = parent.getItemAtPosition(pos).toString();

                if (!"".equals(roverName)) {
                    presenter.roverSelected(parent.getItemAtPosition(pos).toString());
                }
                break;
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {}

    public void displayMessage(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void showDatePickerDialog(View view) {
        Fragmenting newFragment = new DatePickerFragment();

        newFragment.returnValue("", new DatePickerCallback() {
            @Override
            public void success(String date) {
                displayMessage(date);
            }
            @Override
            public void failure() {

            }

        });

    }

}
