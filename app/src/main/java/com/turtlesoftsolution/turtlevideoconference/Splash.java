package com.turtlesoftsolution.turtlevideoconference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.turtlesoftsolution.turtlevideoconference.constent;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
         new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);
            }
        },constent.SPLASH_SCREEN_TIME);
    }
}
