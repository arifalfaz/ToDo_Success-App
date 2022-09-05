package com.elecsiya.todo_success.Pomodoro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.elecsiya.todo_success.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class pomodoro extends AppCompatActivity {

    SeekBar seekBar;
    TextView textView_time, value1;
    String string1;
    Button startBtn;
    CountDownTimer countDownTimer;
    MediaPlayer mediaPlayer, mediaPlayer2;
    Boolean counterIsActive = false;
    ImageView imageView;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pomodoro);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

//----------------BannerAds----------------------------------------->
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
//----------------BannerAds----------------------------------------->



        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        imageView = findViewById(R.id.image_view);
        seekBar = findViewById(R.id.seekbar_timer);
        textView_time = findViewById(R.id.timer_textView);
        startBtn = findViewById(R.id.start_btn);
        value1 = findViewById(R.id.taskName);
        string1 = getIntent().getExtras().getString("value");
        value1.setText(string1);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setVisibility(View.INVISIBLE);
                startBtn.setVisibility(View.VISIBLE);
            }
        });

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.task_completed);
        seekBar.setMax(3600);     //-> maximum time
        seekBar.setProgress(1200); //-> starting time

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                update(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {


            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void start_timer(View view) {
        if (counterIsActive == false) {
            counterIsActive = true;
            seekBar.setEnabled(false);
            startBtn.setText("STOP");
            countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                    update((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    reset();
                    if (mediaPlayer != null)
                        mediaPlayer.start();
                    Toast.makeText(getApplicationContext(), "Congratulation", Toast.LENGTH_SHORT).show();
                    mediaPlayer2 = MediaPlayer.create(getApplicationContext(), R.raw.reward_music);
                    mediaPlayer2.start();
                    imageView.setVisibility(view.VISIBLE);
                    startBtn.setVisibility(View.INVISIBLE);
                    //vibration
                    Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    long[] pattern = {100, 300, 300, 300, 500, 500};
                    v.vibrate(pattern, -1);

                }
            }.start();
        } else {
            reset();
        }
    }

    private void reset() {
        textView_time.setText("20:00");
        seekBar.setProgress(1200);
        countDownTimer.cancel();
        startBtn.setText("START");
        seekBar.setEnabled(true);
        counterIsActive = false;


    }

    private void update(int progress) {
        int minutes = progress / 60;
        int seconds = progress % 60;
        String secondsFinal = "";

        if (seconds <= 9) {
            secondsFinal = "0" + seconds;
        } else {
            secondsFinal = "" + seconds;
        }
        seekBar.setProgress(progress);
        textView_time.setText("" + minutes + ":" + secondsFinal);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (counterIsActive) {
            countDownTimer.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (counterIsActive) {
            countDownTimer.start();
        }
    }

    @Override
    public void onBackPressed() {
        new android.app.AlertDialog.Builder(this)
                .setIcon(R.drawable.robot)
                .setTitle("Are you sure you want to exit?")
                .setMessage("Please wait... until the Pomodoro is complete ")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("Not now!", null)
                .show();
    }

}
