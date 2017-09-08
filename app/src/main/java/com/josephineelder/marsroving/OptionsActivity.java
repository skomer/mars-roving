package com.josephineelder.marsroving;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class OptionsActivity extends AppCompatActivity implements OptionsActivityView, AdapterView.OnItemSelectedListener {

    Spinner roversSpinner;
    Spinner camerasSpinner;
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

        presenter = new OptionsActivityPresenter(this, new JsonParser(), new HttpConnector());
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

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        switch (parent.getId()) {
            case R.id.rovers_spinner:
                presenter.roverSelected(pos);
                break;
            case R.id.cameras_spinner:
//                presenter.cameraSelected(pos);
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
