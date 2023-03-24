package com.example.climber;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class RecyclerClimbs extends AppCompatActivity {

    RecyclerView recyclerView;
    ClimbAdapter climbAdapter;
    ClimbSQLiteHelper climbSQLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_climbs);

        climbSQLiteHelper = new ClimbSQLiteHelper(getApplicationContext());
        climbAdapter = new ClimbAdapter(climbSQLiteHelper);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(climbAdapter);
    }
}