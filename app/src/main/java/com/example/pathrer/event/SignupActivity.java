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

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignupActivity extends AppCompatActivity {
    protected EditText mname;
    protected EditText memail;
    protected EditText mpass;
    protected EditText mphone;
    protected Button mbutton;
    protected ProgressDialog pdia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //initialize
        mname = (EditText)findViewById(R.id.nameid);
        memail = (EditText)findViewById(R.id.emailid);
        mpass = (EditText)findViewById(R.id.passid);
        mphone = (EditText)findViewById(R.id.phoneid);
        mbutton = (Button)findViewById(R.id.sbuttonid);

        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = mname.getText().toString().trim();
                if(TextUtils.isEmpty(username)) {
                    mname.setError("Enter Username");
                    return;
                }
                String email = memail.getText().toString().trim();
                if(TextUtils.isEmpty(email)) {
                    memail.setError("Enter valid E-mail");
                    return;
                }
                String password = mpass.getText().toString().trim();
                if(TextUtils.isEmpty(password)) {
                    mpass.setError("Enter Password");
                    return;
                }
                String phone = mphone.getText().toString().trim();
                if(TextUtils.isEmpty(phone)) {
                    mphone.setError("Enter Phone number");
                    return;
                }


                SignupActivity.this.pdia = ProgressDialog.show(SignupActivity.this, null, "Signing in.... ");

                ParseUser user = new ParseUser();
                user.setUsername(username);
                user.setPassword(password);
                user.setEmail(email);
                user.put("phone", phone);


                user.signUpInBackground(new SignUpCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            // Hooray! Let them use the app now.
                            Toast.makeText(SignupActivity.this, "Registered!!!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignupActivity.this, MainActivity.class));
                            finish();

                        } else {
                            // Sign up didn't succeed. Look at the ParseException
                            // to figure out what went wrong
                            pdia.dismiss();
                            Toast.makeText(SignupActivity.this, "Plz check User nmae and password", Toast.LENGTH_SHORT).show();
                        }
                    }
                });





            }
        });



    }

}
