package com.example.mobiletutorial;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.util.Log;

import java.io.ByteArrayOutputStream;

public class ParentDataSource {

    SQLiteDatabase database;
    DataBase dbHelper;

    public ParentDataSource(Context context){ dbHelper = new DataBase(context);}


    public void open() throws SQLException{
            database = dbHelper.getWritableDatabase();
    }

    public void close() {dbHelper.close();}


    public boolean insertContact(Parent p) {
        boolean didSucceed = false;
        try {
            ContentValues initialValues = new ContentValues();
            initialValues.put("userName", p.getUserName());
            initialValues.put("email", p.getEmail());
            initialValues.put("password", p.getPassword());
            initialValues.put("NbOfChildren", p.getNbOfChildren());
            didSucceed = database.insert("parent", null, initialValues) > 0;
        } catch (Exception e) {
            Log.d("My Database", "Something went wrong!");
        }
        return didSucceed;
    }




}
