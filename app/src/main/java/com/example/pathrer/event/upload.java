package com.example.pathrer.event;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;


public class upload extends AppCompatActivity {

    protected EditText title;
    protected EditText place,price;
    protected Button b1;
    protected ProgressDialog pd1;
    protected Bitmap yourbitmap;

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

           /* Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();*/

            /*image.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            yourbitmap = BitmapFactory.decodeFile(picturePath);*/
            /*ByteArrayOutputStream stream = new ByteArrayOutputStream();
            // Compress image to lower quality scale 1 - 100
            yourbitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] image = stream.toByteArray();*/
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

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent =new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent,RESULT_LOAD_IMAGE);//load only one image
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload.this.pd1 = ProgressDialog.show(upload.this, null, "Uploading...");
                ParseObject conv = new ParseObject("convention");
                String tl = title.getText().toString().trim();
                String pl = place.getText().toString().trim();
                String pr = price.getText().toString().trim();
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
                conv.put("owner_detail",user);
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


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
