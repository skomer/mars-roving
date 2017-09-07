package com.josephineelder.marsroving;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class OptionsActivity extends AppCompatActivity implements OptionsActivityView {

    Spinner roversSpinner;
    OptionsActivityPresenter presenter;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        roversSpinner = (Spinner) findViewById(R.id.roversSpinner);
        presenter = new OptionsActivityPresenter(this, new JsonParser(), new HttpConnector());
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    public void showRovers(final List<String> rovers) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ArrayAdapter adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, rovers) {};
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                roversSpinner.setAdapter(adapter);
            }
        });
    }

    public void displayMessage(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            }
        });
    }

}
