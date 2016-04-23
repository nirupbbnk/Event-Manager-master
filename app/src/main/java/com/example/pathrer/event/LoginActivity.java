package com.example.pathrer.event;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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


        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            // fetch data
        } else {
            Snackbar snackbar = Snackbar
                    .make(findViewById(R.id.coordinatorlayout), "No internet connection!", Snackbar.LENGTH_LONG)
                    .setAction("RETRY", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
                        }
                    });

// Changing message text color
            snackbar.setActionTextColor(Color.RED);

// Changing action button text color
            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.YELLOW);
            snackbar.show();
        }




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
