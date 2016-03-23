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
import com.parse.ParseObject;

import java.util.List;

/**
 * Created by Pathrer on 16-03-16.
 */
public class CustAdapter extends RecyclerView.Adapter<CustAdapter.ViewHolder> {
    private static final String TAG = "CustomAdapter";

    private List<ListItems> mDataSet;

    // BEGIN_INCLUDE(recyclerViewSampleViewHolder)
    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView place;
        public TextView cost;
        public ImageView imageView;


        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                }
            });
            title = (TextView) v.findViewById(R.id.titleid);
            place = (TextView) v.findViewById(R.id.placeid);
            cost = (TextView) v.findViewById(R.id.priceid);
            imageView = (ImageView) v.findViewById(R.id.image);
        }


    }
    // END_INCLUDE(recyclerViewSampleViewHolder)

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used by RecyclerView.
     */
    public CustAdapter(List<ListItems> dataSet) {
        mDataSet = dataSet;
    }

    // BEGIN_INCLUDE(recyclerViewOnCreateViewHolder)
    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v1 = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row, viewGroup, false);

        return new ViewHolder(v1);
    }
    // END_INCLUDE(recyclerViewOnCreateViewHolder)

    // BEGIN_INCLUDE(recyclerViewOnBindViewHolder)
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");

        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        Log.d("created","invoked");
        ListItems current = mDataSet.get(position);
        viewHolder.title.setText(current.getTitle());
        viewHolder.place.setText(current.getPlace());
        viewHolder.cost.setText(current.getPrice());

        ParseFile fileobj = current.getImage();
        fileobj.getDataInBackground(new GetDataCallback() {
            @Override
            public void done(byte[] bytes, ParseException e) {
                if (e == null) {
                    Bitmap bit = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    viewHolder.imageView.setImageBitmap(bit);
                } else {
                    Log.v("byte", "Error: " + e.getMessage());
                }
            }
        });
    }
    // END_INCLUDE(recyclerViewOnBindViewHolder)

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}
