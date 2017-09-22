package com.example.arsenko.test1;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewActivity extends AppCompatActivity{

    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private List<RecyclerItem> listItems;
//    private RecyclerView.LayoutManager myLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recycle);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();

        for(int i=0; i<10; i++){
            listItems.add(new RecyclerItem("kikabu" + (i+1), "TO ye shos'-tam-shos' pro shos'-tam-shos' pid nomerom " + (i+1)));
        }

        adapter = new MyAdapter(listItems, this);
        recyclerView.setAdapter(adapter);
    }
}
