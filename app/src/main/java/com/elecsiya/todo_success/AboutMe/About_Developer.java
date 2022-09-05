package com.elecsiya.todo_success.AboutMe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.elecsiya.todo_success.Pomodoro.TaskName;
import com.elecsiya.todo_success.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class About_Developer extends AppCompatActivity {
    ImageView youtube1, telegram1, facebook1, instagram1;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_developer);
        youtube1 = findViewById(R.id.youtube1);
        telegram1 = findViewById(R.id.telegram1);
        facebook1 = findViewById(R.id.facebook1);
        instagram1 = findViewById(R.id.Instagram1);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        loadAds();

        youtube1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/c/ElecSiya")));
                Toast.makeText(About_Developer.this, "Subscribe our youtube channel", Toast.LENGTH_SHORT).show();

            }
        });

        telegram1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://telegram.me/ElecSiya")));
                Toast.makeText(About_Developer.this, "Join our Telegram Channel", Toast.LENGTH_SHORT).show();

            }
        });

        facebook1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/elecsiyapage/")));
                Toast.makeText(About_Developer.this, "Follow our facebook page", Toast.LENGTH_SHORT).show();

            }
        });

        instagram1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/elecsiya/")));
                Toast.makeText(About_Developer.this, "Follow our Instagram page", Toast.LENGTH_SHORT).show();


            }
        });
    }

    void loadAds() {


        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this, getString(R.string.InterstitialAds), adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;

                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        mInterstitialAd = null;
                    }
                });
    }

    @Override
    public void onBackPressed() {
        if (mInterstitialAd != null) {
            mInterstitialAd.show(About_Developer.this);
            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent();

                    mInterstitialAd = null;
                    About_Developer.super.onBackPressed();
                }
            });

        } else {

            super.onBackPressed();
        }
    }
}



