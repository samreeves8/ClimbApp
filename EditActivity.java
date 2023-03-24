package com.example.climber;

import static android.app.PendingIntent.getActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class EditActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    ClimbSQLiteHelper climbSQLiteHelper;
    EditText editTextName;
    EditText editTextGrade;
    TextView textViewDate;
    ImageView imageView;
    Button buttonAdd;
    Button buttonDate;
    Bitmap bitmap;
    Double longitude;
    Double latitude;
    LocationListener locationListener;
    Location lastLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        climbSQLiteHelper = new ClimbSQLiteHelper(getApplicationContext());
        climbSQLiteHelper.getReadableDatabase();

        Intent intent = getIntent();
        bitmap = (Bitmap) intent.getParcelableExtra("bitmap");
        longitude = intent.getDoubleExtra("longitude", 0);
        latitude = intent.getDoubleExtra("latitude",0);

        buttonAdd = findViewById(R.id.addButton);
        buttonDate = findViewById(R.id.dateButton);
        textViewDate = findViewById(R.id.textViewDate);
        editTextName = findViewById(R.id.editTextName);
        editTextGrade = findViewById(R.id.editTextGrade);
        imageView = findViewById(R.id.imageViewEdit);
        imageView.setImageBitmap(bitmap);
    }

    public void onClickDatePicker(View view){
        DateFragment dateFragment = new DateFragment(this);
        dateFragment.show(getSupportFragmentManager(), null);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        String msg = String.format("%d/%d/%d", month, day, year);
        textViewDate.setText(msg);
    }

    public void submit(View view){
        Log.v("HELP", "HELP");

        String name = editTextName.getText().toString();
        String grade = editTextGrade.getText().toString();
        String date = textViewDate.getText().toString();
        String img = name + ".png";
        Double lat = latitude;
        Double lon = longitude;
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = openFileOutput(name + ".png", Context.MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
        climbSQLiteHelper.insert(name, grade, img, String.valueOf(lat), String.valueOf(lon), date);
        finish();
    }
}