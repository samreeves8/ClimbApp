package com.example.climber;

import android.graphics.Bitmap;

public class Climbs {
    public String name;
    public String grade;
    public Bitmap img;
    public String longitude;
    public String latitude;
    public String date;

    public Climbs(String name, String grade, Bitmap img, String longitude, String latitude, String date){
        this.name = name;
        this.grade = grade;
        this.img = img;
        this.longitude = longitude;
        this.latitude = latitude;
        this.date = date;
    }
}
