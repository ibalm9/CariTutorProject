package com.example.finalproject.Splashcreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.example.finalproject.Activity.MainActivity;
import com.example.finalproject.R;

public class SplashScreen extends AppCompatActivity {
    private int SLEEP_TIMER=3; //sleep timer
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        requestWindowFeature(Window.FEATURE_NO_TITLE);//no tool bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//takes two parameter flags and masks

        setContentView(R.layout.activity_splash_screen);
      //  getSupportActionBar().hide();

        LogoLauncher logoLauncher = new LogoLauncher();
        logoLauncher.start();
    }

    //display logo
    private class LogoLauncher extends Thread {

        public void run() {
            try {
                sleep(1000 * SLEEP_TIMER);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            Intent intent = new Intent(SplashScreen.this, MainActivity.class);
            startActivity(intent);
            SplashScreen.this.finish();
        }

    }
}