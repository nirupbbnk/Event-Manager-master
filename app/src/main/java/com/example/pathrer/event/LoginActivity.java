package com.example.pathrer.event;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseUser;
import com.parse.ParseException;




public class LoginActivity extends AppCompatActivity {

    protected EditText muser;
    protected EditText mpass;
    protected Button mbuttton;
    protected Button msignbuttton;
    protected ProgressDialog pdia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        muser = (EditText)findViewById(R.id.loginemailid);
        mpass = (EditText)findViewById(R.id.passid);
        mbuttton = (Button)findViewById(R.id.loginid);
        msignbuttton = (Button)findViewById(R.id.signupid);

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
                if(TextUtils.isEmpty(username)) {
                    muser.setError("Enter Username");
                    return;
                }
                String password = mpass.getText().toString().trim();
                if(TextUtils.isEmpty(password)) {
                    mpass.setError("Enter Password");
                    return;
                }

                //parse login
                LoginActivity.this.pdia = ProgressDialog.show(LoginActivity.this,null,"Logging in..... ",true);
                //showdialog();

                ParseUser.logInInBackground(username, password, new LogInCallback() {
                    public void done(ParseUser user, ParseException e) {
                        if (user != null) {
                            // Hooray! The user is logged in.
                            Toast.makeText(LoginActivity.this, "logged up!!!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();

                        } else {
//
                            pdia.dismiss();
                            Toast.makeText(LoginActivity.this, "wrong username or password!!!", Toast.LENGTH_SHORT).show();


                        }
                    }
                });
            }


        });

    }

}
