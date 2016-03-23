package com.example.pathrer.event;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private List<ListItems> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, place, price;
        public ImageView image;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.titleid);
            place = (TextView) view.findViewById(R.id.placeid);
            price = (TextView) view.findViewById(R.id.priceid);
            image = (ImageView) view.findViewById(R.id.image);
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