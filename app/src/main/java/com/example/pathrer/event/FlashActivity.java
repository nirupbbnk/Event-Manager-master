package com.example.pathrer.event;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.parse.Parse;
import com.parse.ParseUser;

public class FlashActivity extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Parse.enableLocalDatastore(this);
        Parse.initialize(this,"PU9YBsWaTPFSp3HgqcNSejxAIaPJLJzGDYON6zu7","BsYpCxHooGKAShLXamzNMztqXrl7YVCBJTodLkhn");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);




        new Handler().postDelayed(new Runnable() {

			/*
			 * Showing splash screen with a timer. This will be useful when you
			 * want to show case your app logo / company
			 */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
//             Intent i = new Intent(FlashActivity.this, LoginActivity.class);
//                startActivity(i);


                ParseUser currentUser = ParseUser.getCurrentUser();
           if (currentUser != null) {
            // do stuff with the user
               startActivity(new Intent(FlashActivity.this, MainActivity.class));
              } else {
            // show the signup or login screen
               startActivity(new Intent(FlashActivity.this, LoginActivity.class));
            }
                // close this activity

                finish();
            }
        }, SPLASH_TIME_OUT);


    }

}


