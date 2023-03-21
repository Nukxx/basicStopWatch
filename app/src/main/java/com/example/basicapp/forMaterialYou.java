package com.example.basicapp;

import android.app.Application;

import com.google.android.material.color.DynamicColors;

public class forMaterialYou extends Application {
    public void onCreate () {
        super.onCreate();
        DynamicColors.applyToActivitiesIfAvailable(this);
    }
}
