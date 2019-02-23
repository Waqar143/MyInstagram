package com.example.signup;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("8yxbQsj40oZsNo1Ul8EMSPrKNd63dfTcJWxywI7y")
                // if defined
                .clientKey("Y7Yb4zFuw8w5IjVkk47ajzdt43jFsCwCIlCJlpaJ")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
