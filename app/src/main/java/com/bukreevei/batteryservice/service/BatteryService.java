package com.bukreevei.batteryservice.service;


import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.bukreevei.batteryservice.MainActivity;
import com.bukreevei.batteryservice.MyApplication;

import java.util.concurrent.TimeUnit;

public class BatteryService extends Service {
    private static final String TAG = "BatteryService";
    public BatteryService() {
    }

    @Override
    public final void onCreate() {
        super.onCreate();
        Toast.makeText(this, "Служба запущена", Toast.LENGTH_SHORT).show();

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public final int onStartCommand(final Intent intent, int flags, int startId) {
        Log.d(TAG, "Стартовала служба");
        final MyApplication myApplication = ((MyApplication) getApplicationContext());
        final MainActivity currentActivity = (MainActivity) myApplication.getCurrentActivity();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(5);
                        final IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
                        final Intent batteryStatus = registerReceiver(null, ifilter);
                        final int status = batteryStatus.getIntExtra("level", -1);
                        Log.d(TAG, String.valueOf(status));
                        final int minCharge = intent.getIntExtra("percents", 10);
                        if (status <= minCharge) {
                            currentActivity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    makeToast(currentActivity);
                                }
                            });
                        }
                    } catch (InterruptedException e) {
                        Log.e(TAG, e.getLocalizedMessage());
                    }
                }
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public final void onDestroy() {
        Log.d(TAG, "Сервис остновлен");
        Toast.makeText(this, "Сервис остновлен", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    private void makeToast(final Context activity) {
        Toast.makeText(activity, "Низкий заряд батареи",
                Toast.LENGTH_SHORT).show();
    }


}
