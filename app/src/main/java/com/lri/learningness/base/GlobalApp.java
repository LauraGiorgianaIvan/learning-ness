package com.lri.learningness.base;

import android.content.Context;
import androidx.multidex.MultiDexApplication;

public class GlobalApp extends MultiDexApplication {
    private static GlobalApp INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }

    public static Context appContext() {
        return INSTANCE;
    }

}
