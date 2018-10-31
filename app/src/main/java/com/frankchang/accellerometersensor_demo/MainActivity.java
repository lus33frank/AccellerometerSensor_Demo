package com.frankchang.accellerometersensor_demo;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    // 畫面元件
    private TextView show;
    // 物件
    private SensorManager managers;
    private Sensor sensor;
    private sensorListener listener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        show = findViewById(R.id.tvShow);

        managers = (SensorManager) getSystemService(SENSOR_SERVICE);
        if (managers != null) {
            sensor = managers.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (sensor != null) {
            listener = new sensorListener();    // 建立監聽器
            // 註冊
            managers.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 註銷
        managers.unregisterListener(listener);
    }


    private class sensorListener implements SensorEventListener {

        @Override
        public void onSensorChanged(SensorEvent event) {
            float[] values = event.values;

            Sensor s1 = event.sensor;
            String name = s1.getName();     // 傳感器的名稱
            float power = s1.getPower();    // 傳感器的耗電量

            String sb = ("Name : " + name + "\n") +
                    "Power : " + power + "\n\n" +
                    "X : " + values[0] + "\n" +
                    "Y : " + values[1] + "\n" +
                    "Z : " + values[2] + "\n";
            show.setText(sb);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // 未使用
        }
    }

}
