package com.josephineelder.marsroving;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpConnector implements HttpConnecting {

    private HttpURLConnection client = null;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    public void doRequest(final String path, final HttpCallback callback) {
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(path);
                    client = (HttpURLConnection) url.openConnection();

                    InputStream inputStream;
                    int status = client.getResponseCode();

                    if (status != 200) {
                        callback.failure("" + status);
                    } else {
                        inputStream = new BufferedInputStream(client.getInputStream());
                        callback.success(convertStreamToString(inputStream));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    client.disconnect();
                }
            }
        });
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

}
