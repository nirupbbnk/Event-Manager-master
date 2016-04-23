package com.example.pathrer.event;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>  {

    private List<ListItems> moviesList;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, place, price;
        public ImageView image;
        public TextView btn;
        public String email;
        public String phno;
        private final Context context;
        final CharSequence[] items = {" Gmail "," Phone "};

        public MyViewHolder(final View itemview) {
            super(itemview);
            context = itemview.getContext();
            title = (TextView) itemview.findViewById(R.id.titleid);
            place = (TextView) itemview.findViewById(R.id.placeid);
            price = (TextView) itemview.findViewById(R.id.priceid);
            image = (ImageView) itemview.findViewById(R.id.image);
            btn = (TextView) itemview.findViewById(R.id.btnid);
            email="hello";

         itemView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 AlertDialog.Builder builder = new AlertDialog.Builder(context);
                 builder.setTitle("Book through..");

                 builder.setCancelable(false);
                 builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                     public void onClick(DialogInterface dialog, int item) {


                         switch (item) {
                             case 0:
                                 Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                                 sendIntent.setType("plain/text");
                                 sendIntent.setData(Uri.parse(email));
                                 sendIntent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
                                 sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Intrested to book");
                                 sendIntent.putExtra(Intent.EXTRA_TEXT, "This message is from user with above mail interested in booking your service :-)");
                                 context.startActivity(sendIntent);
                                 break;
                             case 1:
                                 // Your code when 2nd  option seletced
                                 Intent jack = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phno, null));
                                 context.startActivity(jack);

                                 break;


                         }
                         dialog.dismiss();
                     }
                 }).setNegativeButton("Cancel", null).show();
                 AlertDialog levelDialog = builder.create();
                 levelDialog.show();

                }
            });

        }

        }

    public CustomAdapter(List<ListItems> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        ListItems movie = moviesList.get(position);
        holder.title.setText(movie.getTitle());
        holder.place.setText(movie.getPlace());
        holder.price.setText(movie.getPrice());
        holder.email = movie.getEmail();
        holder.phno = movie.getPhno();
        ParseFile fileobj = movie.getImage();
        fileobj.getDataInBackground(new GetDataCallback() {
            @Override
            public void done(byte[] bytes, ParseException e) {
                if (e == null) {
                    Bitmap bit = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    holder.image.setImageBitmap(bit);
                } else {
                    Log.v("byte", "Error: " + e.getMessage());
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return moviesList.size();
    }


}