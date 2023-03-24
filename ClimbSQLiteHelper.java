package com.example.climber;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.Nullable;

import java.io.File;

public class ClimbSQLiteHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "climb.sqlite";
    private static final int DB_VERSION = 1;
    Context context;

    public ClimbSQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create = "CREATE TABLE climb (_id INTEGER PRIMARY KEY AUTOINCREMENT, img STRING, name STRING, grade STRING, longitude String, latitude String, date String);";
        sqLiteDatabase.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public int getCount(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM climb", null);
        return cursor.getCount();
    }

    public Bitmap getBitmap(int position) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("climb", new String[]{"name", "grade", "img", "longitude", "latitude", "date"},
                null, null, null, null, null);
        if(cursor.moveToPosition(position)){
            File file = context.getFileStreamPath(cursor.getString(2));
            if (file.exists()){
                Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                return bitmap;
            }
        }
        return null;
    }

    public Climbs getClimbs(int position) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("climb", new String[]{"name", "grade", "img", "longitude", "latitude", "date"},
                null, null, null, null, null);
        if(cursor.moveToPosition(position)){
            File file = context.getFileStreamPath(cursor.getString(2));
            if (file.exists()){
                Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                Climbs climbs = new Climbs(cursor.getString(0), cursor.getString(1), bitmap, cursor.getString(3), cursor.getString(4), cursor.getString(5));
                return climbs;
            }
        }
        return null;
    }

    public void insert(String name, String grade, String img, String longitude, String latitude, String date) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("grade", grade);
        contentValues.put("img", img);
        contentValues.put("longitude", longitude);
        contentValues.put("latitude", latitude);
        contentValues.put("date", date);
        sqLiteDatabase.insert("climb", null, contentValues);
    }
}