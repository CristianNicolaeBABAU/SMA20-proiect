package com.upt.cti.vlad.udrescu.booking.dabasehelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DBHelper extends SQLiteOpenHelper {
    Context context;

    public DBHelper(Context context){
        super(context, "Booking.db", null, 1);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table userdetails(name TEXT primary key, phoneno INTEGER, timestamp DATE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists userdetails");
    }

    public Boolean insertUserData(String name, Integer phone_number){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //columns name
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phoneno", phone_number);
        long result = sqLiteDatabase.insert("userdetails", null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Boolean updateUserData(String name, Integer phone_number){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        //columns name
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phoneno", phone_number);
        contentValues.put("timestamp", getDateTime());
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from userdetails where name = ?", new String[]{name});
        if (cursor.getCount() > 0){
            long result = sqLiteDatabase.update("userdetails", contentValues, "name=?", new String[]{name});
            if (result == -1)
                return false;
            else {
                Toast.makeText(context, "Your details has been updated.", Toast.LENGTH_SHORT).show();
                return true;
            }
        }
        else{
            long result = sqLiteDatabase.insert("userdetails", null, contentValues);
            if (result == -1)
                return false;
            else {
                Toast.makeText(context, "You have been registered.", Toast.LENGTH_SHORT).show();
                return true;
            }
        }
    }

    public Boolean deleteUserData(String name, Integer phone_number){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phoneno", phone_number);
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from userdetails where name = ?", new String[]{name});
        if (cursor.getCount() > 0){
            long result = sqLiteDatabase.delete("userdetails", "name=?", new String[]{name});
            if (result == -1)
                return false;
            else
                return true;
        }
        else {
            return false;
        }
    }

    public Cursor getData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from userdetails", null);
        return cursor;
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}
