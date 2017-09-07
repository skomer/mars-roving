package com.josephineelder.marsroving;

public interface HttpCallback {

    void success(String json);
    void failure(String responseCode);

}
