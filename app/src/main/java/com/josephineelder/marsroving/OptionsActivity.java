package com.josephineelder.marsroving;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class OptionsActivity extends AppCompatActivity implements OptionsActivityView, AdapterView.OnItemSelectedListener, Button.OnClickListener {

    Spinner roversSpinner;
    Spinner camerasSpinner;
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
        getPhotosButton = (Button) findViewById(R.id.get_photos_button);
        getPhotosButton.setOnClickListener(this);

        presenter = new OptionsActivityPresenter(this, new JsonParser(), new HttpConnector(), new UrlBuilder());
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
        String roverName = roversSpinner.getSelectedItem().toString();
        String cameraName = camerasSpinner.getTransitionName();

        presenter.getPhotosButtonTapped(roverName, "", cameraName);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        switch (parent.getId()) {
            case R.id.rovers_spinner:
                presenter.roverSelected(parent.getItemAtPosition(pos).toString());
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

}
