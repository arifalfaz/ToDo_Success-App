package com.elecsiya.todo_success.Splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.elecsiya.todo_success.MainActivity;
import com.elecsiya.todo_success.R;

public class Splash_Screen extends AppCompatActivity {
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Splash_Screen.this, MainActivity.class));
                mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.aivoice);
                mediaPlayer.start();
                finish();
            }
        },3000);
    }

}