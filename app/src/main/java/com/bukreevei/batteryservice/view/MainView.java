package com.bukreevei.batteryservice.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.bukreevei.batteryservice.MainActivity;
import com.bukreevei.batteryservice.R;
import com.bukreevei.batteryservice.service.BatteryService;

public class MainView extends FrameLayout {
    private final EditText mPercent;
    private final Button mButton;
    private final Button mButtonStop;
    public MainView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(getContext()).inflate(R.layout.main_view, this);
        mButton = findViewById(R.id.button);
        mPercent = findViewById(R.id.percent);
        mButtonStop = findViewById(R.id.stopButton);

        mButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonListener();
            }
        });

        mButtonStop.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                stopServiceButtonListener();
            }
        });


    }

    private void buttonListener() {
        final Intent intent = new Intent(getContext(), BatteryService.class);
        intent.putExtra("percents", Integer.parseInt(mPercent.getText().toString()));
        final MainActivity mainActivity = ((MainActivity) getContext());
        mainActivity.startService(intent);
    }

    private void stopServiceButtonListener() {
        final Intent intent = new Intent(getContext(), BatteryService.class);
        final MainActivity mainActivity = ((MainActivity) getContext());
        mainActivity.stopService(intent);
    }
}
