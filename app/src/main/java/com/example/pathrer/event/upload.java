package com.example.pathrer.event;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class upload extends AppCompatActivity {

    protected EditText title;
    protected EditText place,price;
    protected Button b1;
    protected  boolean sel=true;
    protected int SELECT_FILE;
    protected int REQUEST_CAMERA= 1;
    protected ProgressDialog pd1;
    protected Bitmap yourbitmap;
    protected ParseObject conv;
    protected ImageView image;
    private static final int RESULT_LOAD_IMAGE = 1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RESULT_LOAD_IMAGE && resultCode==RESULT_OK && data != null){
            Uri selectedImage = data.getData();
            image.setImageURI(selectedImage);

            image.buildDrawingCache();
             yourbitmap = image.getDrawingCache();

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        title = (EditText) findViewById(R.id.utitle);
        place = (EditText) findViewById(R.id.uplace);
        price = (EditText) findViewById(R.id.uprice);
        image = (ImageView) findViewById(R.id.uimage);
        b1 = (Button) findViewById(R.id.ubutton);
        image.setImageResource(R.drawable.tumb);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent =new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);//load only one image*/
               // selectImage();
            }
        });



        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onItemSelected(AdapterView<?> arg0, View view,
                                       int position, long row_id) {
                final Intent intent;
                switch (position) {
                    case 0:
                        String t123="";
                        if (TextUtils.isEmpty(t123)) {
                            // Toast.makeText(Update.this, "plz enter title", Toast.LENGTH_LONG).show();
                            title.setError("select Department");
                            return;
                        }
                        ;
                        break;
                    case 1:
                        conv = new ParseObject("Priests");
                        sel=true;
                        break;
                    case 2:
                        conv = new ParseObject("Decors");
                        sel=true;
                        break;
                    case 3:
                        conv = new ParseObject("convention");
                        sel=true;
                        break;
                    case 4:
                        conv = new ParseObject("Catering");
                        sel=true;
                        break;
                    case 5:
                        conv = new ParseObject("Managers");
                        sel=true;
                        break;
                    // and so on
                    // .....
                    default:
                        sel=false;
                }
                //startActivity(intent);


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }

        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*ParseObject conv = new ParseObject("convention");*/
                String tl = title.getText().toString().trim();
                if (TextUtils.isEmpty(tl)) {
                    // Toast.makeText(Update.this, "plz enter title", Toast.LENGTH_LONG).show();
                    title.setError("Enter Name of  the Company");
                    return;
                }

                String pl = place.getText().toString().trim();
                if (TextUtils.isEmpty(pl)) {
                    place.setError("Enter City Name");
                    return;
                }

                String pr = price.getText().toString().trim();
                if (TextUtils.isEmpty(pr)) {
                    price.setError("Enter Price");
                    return;
                }

                ParseUser user = ParseUser.getCurrentUser();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                yourbitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] image = stream.toByteArray();

                ParseFile file = new ParseFile("image.png", image);
                file.saveInBackground();
                conv.put("hall_name", tl);
                conv.put("city", pl);
                conv.put("price", pr);
                conv.put("image", file);

                conv.put("owner_details", user);
                upload.this.pd1 = ProgressDialog.show(upload.this, null, "Uploading...");
                conv.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        upload.this.pd1.dismiss();
                        if (e == null) {
                            Toast.makeText(upload.this, "Succss in post", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(upload.this, MainActivity.class);
                            startActivity(i);
                            finish();
                        } else {
                            Toast.makeText(upload.this, "error in post", Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });


      /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

}
