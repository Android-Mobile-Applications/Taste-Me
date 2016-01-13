package com.example.nitro.tasteme;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "8IuiYL4O1BYRWvDgSWV97l5zet2RxIrtwv9k8D3c", "kYYqldFfSdoyoGEUYo81TDbzEibYfyrkywcipxS6");
    }
}
