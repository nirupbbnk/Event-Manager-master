package com.example.pathrer.event;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.ParseException;




public class LoginActivity extends AppCompatActivity {

    protected EditText muser;
    protected EditText mpass;
    protected Button mbuttton;
    protected Button msignbuttton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /* ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();*/
        muser = (EditText)findViewById(R.id.loginemailid);
        mpass = (EditText)findViewById(R.id.passid);
        mbuttton = (Button)findViewById(R.id.loginid);
        msignbuttton = (Button)findViewById(R.id.signupid);

        // [Optional] Power your app with Local Datastore. For more info, go to
        // https://parse.com/docs/android/guide#local-datastore
//        Parse.enableLocalDatastore(this);
//
//        Parse.initialize(this);


        //signup

        msignbuttton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });


        mbuttton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String username = muser.getText().toString().trim();
                String password = mpass.getText().toString().trim();

                //parse login

                ParseUser.logInInBackground(username, password, new LogInCallback() {
                    public void done(ParseUser user, ParseException e) {
                        if (user != null) {
                            // Hooray! The user is logged in.
                            Toast.makeText(LoginActivity.this, "logged up!!!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));

                        } else {
//

                            Toast.makeText(LoginActivity.this, "wrong username or password!!!", Toast.LENGTH_SHORT).show();


                            // Signup failed. Look at the ParseException to see what happened.
                        }
                    }
                });
            }


        });







    }

}
