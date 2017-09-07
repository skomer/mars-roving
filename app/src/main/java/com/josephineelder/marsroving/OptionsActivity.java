package com.josephineelder.marsroving;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class OptionsActivity extends AppCompatActivity implements OptionsActivityView {

    TextView roverNames;
    OptionsActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        roverNames = (TextView) findViewById(R.id.roverNames);

        presenter = new OptionsActivityPresenter(this, new JsonParser(), new HttpConnector());
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    public void showRovers(List<String> rovers) {
        roverNames.setText(rovers.toString());
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
