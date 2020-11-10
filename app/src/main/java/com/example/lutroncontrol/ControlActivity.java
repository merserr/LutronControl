package com.example.lutroncontrol;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ControlActivity extends AppCompatActivity {

    private static final String LOG_TAG = "ControlActivity";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.control);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        TextView room_name;
        room_name = findViewById(R.id.textView);

        Log.d(LOG_TAG, "ControlActivity");

        Intent intent1 = getIntent();
        String room = intent1.getStringExtra(MainActivity.ROOM_NAME);

        room_name.setText("Room " + room);


    }
}