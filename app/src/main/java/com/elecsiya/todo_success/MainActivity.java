package com.elecsiya.todo_success;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.elecsiya.todo_success.AboutMe.About_Developer;
import com.elecsiya.todo_success.Digital.Digital_Clock;
import com.elecsiya.todo_success.Pomodoro.TaskName;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {
    Button createTasks, allTaskList, Pomodoro;
    ImageView menu, update_software;
    MenuBuilder menuBuilder;
    LottieAnimationView lottieAnimationView;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createTasks = findViewById(R.id.todo_task);
        allTaskList = findViewById(R.id.allTask);
        Pomodoro = findViewById(R.id.startPomodoro);
        lottieAnimationView = findViewById(R.id.DC);
        lottieAnimationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Digital_Clock.class));
            }
        });


        update_software = findViewById(R.id.update);
        menu = findViewById(R.id.menu);
        menuBuilder = new MenuBuilder(this);
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.menu_item, menuBuilder);


        createTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent creteTask = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivity(creteTask);
            }
        });

        allTaskList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent taskList = new Intent(MainActivity.this, AllTasksList.class);
                startActivity(taskList);
            }
        });

        Pomodoro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(MainActivity.this, TaskName.class);
                startActivity(p);
            }
        });


        //-----------------------------Menu------------------------->

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MenuPopupHelper menuPopupHelper = new MenuPopupHelper(MainActivity.this, menuBuilder, view);
                menuPopupHelper.setForceShowIcon(true);
                menuBuilder.setCallback(new MenuBuilder.Callback() {
                    @Override
                    public boolean onMenuItemSelected(@NonNull MenuBuilder menu, @NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.more_apps:
                                Toast.makeText(MainActivity.this, "More Useful apps", Toast.LENGTH_SHORT).show();
                            final String id = "8521293142789889400";
                            try {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/dev?id="+id)));
                            }catch (Exception e){
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/dev?id="+id)));
                            }
                                return true;

                            case R.id.rating:
                                Toast.makeText(MainActivity.this, "Please rate us", Toast.LENGTH_SHORT).show();
                                //get app package name
                                final String package_name = "com.elecsiya.todo_success";

                                //Launch play store
                                try {
                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + package_name)));
                                } catch (Exception e) {
                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + package_name)));
                                }

                                return true;

                            case R.id.youtube:
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/c/ElecSiya")));
                                Toast.makeText(MainActivity.this, "Subscribe our youtube channel", Toast.LENGTH_SHORT).show();
                                return true;

                            case R.id.facebook:
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/elecsiyapage/")));
                                Toast.makeText(MainActivity.this, "Follow our facebook page", Toast.LENGTH_SHORT).show();
                                return true;

                            case R.id.Instagram:
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/elecsiya/")));
                                Toast.makeText(MainActivity.this, "Follow our Instagram page", Toast.LENGTH_SHORT).show();

                                return true;

                            case R.id.telegram:
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://telegram.me/ElecSiya")));
                                Toast.makeText(MainActivity.this, "Join our Telegram Channel", Toast.LENGTH_SHORT).show();
                                return true;


                            case R.id.linkedin:
                                Toast.makeText(MainActivity.this, "follow our linkedin profile", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/arifalfaz98/")));
                                return true;

                            case R.id.policy:
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://sites.google.com/view/todo-success/home")));
                                Toast.makeText(MainActivity.this, "Read our policy", Toast.LENGTH_SHORT).show();
                                return true;

                            case R.id.about_us:
                                Toast.makeText(MainActivity.this, "About developer", Toast.LENGTH_SHORT).show();
                                Intent about = new Intent(MainActivity.this, About_Developer.class);
                                startActivity(about);
                                return true;
                            case R.id.clockMe:
                                startActivity(new Intent(MainActivity.this,Digital_Clock.class));
                                return true;


                            default:
                                return false;
                        }
                    }

                    @Override
                    public void onMenuModeChange(@NonNull MenuBuilder menu) {

                    }
                });
                menuPopupHelper.show();
            }
        });

        //-----------------------------Update--------------------------->

        update_software.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new androidx.appcompat.app.AlertDialog.Builder(MainActivity.this)
                        .setIcon(R.drawable.update)
                        .setTitle("Check new Update (free)")
                        .setMessage("1. Kindly check new feature updates .\n2. New Free Feature is Available.\n3. Check our new update every months.")
                        .setPositiveButton("Update", (dialog, which) -> {

                            //get app package name
                            final String package_name = "com.elecsiya.todo_success";

                            //Launch play store
                            try {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + package_name)));
                            } catch (Exception e) {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + package_name)));
                            }
                        })
                        .setNeutralButton("Rate us now!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, "Thank You!", Toast.LENGTH_SHORT).show();

                                //get app package name
                                final String package_name = "com.elecsiya.todo_success";

                                //Launch play store
                                try {
                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + package_name)));
                                } catch (Exception e) {
                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + package_name)));
                                }

                            }
                        })

                        .setNegativeButton("Later", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, "Next time!", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .setCancelable(true)
                        .show();
            }
        });

        //--------------------------- Update------------------------------>


    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setNeutralButton("Rate us", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        final String name = "com.elecsiya.todo_success";

                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + name)));
                        } catch (Exception e) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + name)));
                        }

                        Toast.makeText(getApplicationContext(), "Thanks for supporting ", Toast.LENGTH_SHORT).show();
                    }
                })
                .setIcon(R.drawable.bye)
                .setTitle("I’m incomplete without you.")
                .setMessage("Are you sure you want to exit?")
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