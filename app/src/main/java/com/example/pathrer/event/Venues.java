package com.example.pathrer.event;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Venues extends Fragment{

    public Venues() {
        // Required empty public constructor
    }

    List<ParseObject> score;

    protected View v;
    protected ListView list;
    protected ImageView img;
    protected  List<ParseObject> mlist;
    protected RecyclerView mRecyclerView;
    protected CustomAdapter mAdapter;
    public static List<ListItems> dbObjects = new ArrayList<>();
   @Override
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       //initialize datasets
       ParseQuery<ParseObject> query = new ParseQuery<>("convention");
       query.include("user");
       query.findInBackground(new FindCallback<ParseObject>() {
           @Override
           public void done(List<ParseObject> list, ParseException e) {
               if (e == null) {

                   for(ParseObject obj : list){
                       ListItems event = new ListItems(obj.getString("hall_name"),obj.getString("city"),obj.getString("price"),obj.getParseFile("image"));
                       dbObjects.add(event);
                   }
                  // MainActivity.this.listview.setAdapter(new CustomAdapter(MainActivity.this, list));
               }
           }
       });

   }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_venues, container, false);
        // BEGIN_INCLUDE(initializeRecyclerView)
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.activity_venues, container, false);
        //layout
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new CustomAdapter(dbObjects);
        // Set CustomAdapter as the adapter for RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        Log.d("HI","hi");
        // END_INCLUDE(initializeRecyclerView)
        return rootView;
    }

}