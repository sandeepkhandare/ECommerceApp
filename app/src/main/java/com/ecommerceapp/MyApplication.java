package com.ecommerceapp;

import android.app.Application;
import com.ecommerceapp.data.source.remote.RetrofitClient;
import timber.log.Timber;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
        RetrofitClient.createRetrofitInstance();

    }
}
