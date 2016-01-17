package com.example.nitro.tasteme;

import android.app.Application;

import com.parse.Parse;

// Define class for initializing Parse
public class App extends Application {
    private static final String applicationId = "8IuiYL4O1BYRWvDgSWV97l5zet2RxIrtwv9k8D3c";
    private static final String clientId = "kYYqldFfSdoyoGEUYo81TDbzEibYfyrkywcipxS6";

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, applicationId, clientId);
    }
}
