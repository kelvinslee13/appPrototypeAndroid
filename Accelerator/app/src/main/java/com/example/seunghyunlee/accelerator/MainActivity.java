package com.example.seunghyunlee.accelerator;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    TextView z_accel_view = null;

    SensorManager sensorManager = null;
    Sensor accel = null;
    long prev_time = 0;

    final static long ONE_SEC_IN_MILLS = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.z_accel_view = (TextView) findViewById(R.id.z_accel_view);

    }

    @Override
    protected void onStart() {
        super.onStart();
        this.sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        this.accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this,this.accel,SensorManager.SENSOR_DELAY_UI);

    }

    @Override
    public void onSensorChanged(SensorEvent SensorEvent) {
        long cur_time = System.currentTimeMillis();

        if(cur_time- this.prev_time > ONE_SEC_IN_MILLS){
            this.prev_time = cur_time;
            float z = SensorEvent.values[2];
            this.z_accel_view.setText(Float.toString(z));
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}
}
