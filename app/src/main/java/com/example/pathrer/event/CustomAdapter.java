package com.example.pathrer.event;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

        public MyViewHolder(View itemview) {
            super(itemview);
            title = (TextView) itemview.findViewById(R.id.titleid);
            place = (TextView) itemview.findViewById(R.id.placeid);
            price = (TextView) itemview.findViewById(R.id.priceid);
            image = (ImageView) itemview.findViewById(R.id.image);
            btn = (TextView) itemview.findViewById(R.id.btnid);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "inside viewholder position = " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
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