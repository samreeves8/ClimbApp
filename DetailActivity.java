package com.example.climber;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;


public class DetailActivity extends AppCompatActivity {


    ImageView imageView;
    TextView textViewDetailName;
    TextView textViewDetailGrade;
    TextView textViewDetailDate;
    TextView textViewDetailLon;
    TextView textViewDetailLat;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageView = findViewById(R.id.imageViewDetail);
        textViewDetailDate = findViewById(R.id.textViewDetailDate);
        textViewDetailGrade = findViewById(R.id.textViewDetailGrade);
        textViewDetailName = findViewById(R.id.textViewDetailName);
        textViewDetailLon = findViewById(R.id.textViewDetailLon);
        textViewDetailLat = findViewById(R.id.textViewDetailLat);

        ClimbSQLiteHelper climbSQLiteHelper = new ClimbSQLiteHelper(getApplicationContext());
        SQLiteDatabase sqLiteDatabase = climbSQLiteHelper.getReadableDatabase();

        int pos = getIntent().getIntExtra("position", 0);

        Cursor cursor = sqLiteDatabase.query("climb", new String[] {"name", "grade", "img", "longitude","latitude", "date"}, null, null, null, null, null);
        if (cursor.moveToPosition(pos)){
            Bitmap bitmap = climbSQLiteHelper.getBitmap(pos);
            imageView.setImageBitmap(bitmap);
            textViewDetailName.setText(cursor.getString(0));
            textViewDetailGrade.setText(cursor.getString(1));
            textViewDetailDate.setText(cursor.getString((5)));
            textViewDetailLat.setText(cursor.getString(4));
            textViewDetailLon.setText(cursor.getString(3));
        }
    }
}