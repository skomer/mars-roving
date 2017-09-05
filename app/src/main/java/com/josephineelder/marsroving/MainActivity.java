package com.josephineelder.marsroving;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new AsyncTask<String, Void, Integer>() {
            @Override
            protected Integer doInBackground(String... params) {
                try {
                    String json = getRovers(params[0]);
                    displayMessage("Rovers:\n" + json);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                return 0;
            }
        }.execute("https://mars-photos.herokuapp.com/api/v1/rovers/");

    }

    private String getRovers(final String path) throws MalformedURLException {
        HttpURLConnection client = null;

        try {
            URL url = new URL(path);
            client = (HttpURLConnection) url.openConnection();

            InputStream inputStream;
            int status = client.getResponseCode();

            if (status != 200) {
                return status + "";
            } else {
                inputStream = new BufferedInputStream(client.getInputStream());
                String json = convertStreamToString(inputStream);
                return json;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        } finally {
            client.disconnect();
        }
    }

    private String convertStreamToString(InputStream inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder builder = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                builder.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder.toString();
    }

    private void displayMessage(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            }
        });
    }

}
