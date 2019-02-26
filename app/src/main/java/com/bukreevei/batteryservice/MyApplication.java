package com.bukreevei.batteryservice;

import android.app.Activity;
import android.app.Application;

public class MyApplication extends Application {
    private Activity mCurrentActivity;

    public Activity getCurrentActivity() {
        return mCurrentActivity;
    }

    public void setCurrentActivity(Activity mCurrentActivity) {
        this.mCurrentActivity = mCurrentActivity;
    }
}
