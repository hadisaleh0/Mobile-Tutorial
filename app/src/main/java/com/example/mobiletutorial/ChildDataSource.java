package com.example.mobiletutorial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class ChildDataSource {

    SQLiteDatabase database;
    DataBase dbHelper;

    public ChildDataSource(Context context){dbHelper = new DataBase(context);}

    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }

    public void close(){dbHelper.close();}

    public boolean insertChild(Children c) {
        boolean didSucceed = false;
        try {
            ContentValues initialValues = new ContentValues();
            initialValues.put("firstName", c.getFirstName());
            initialValues.put("lastName", c.getLastName());
            initialValues.put("parent_id",c.getParentId());
            initialValues.put("motherName", c.getMotherName());
            initialValues.put("gender", c.getGender());
            initialValues.put("bloodgroup", c.getBloodgroup());
            initialValues.put("PlaceOfBirth", c.getPlaceOfBirth());
//            initialValues.put("CompletedVaccines", Arrays.toString(c.getCompletedVaccines()));
            initialValues.put("DateOfBirth", String.valueOf(c.getDateOfBirth().getTimeInMillis()));
            didSucceed = database.insert("child", null, initialValues) > 0;
        } catch (Exception e) {
            Log.d("My Database", "Something went wrong!");
        }
        return didSucceed;
    }

    public ArrayList<Children> getAllContacts(int id) {
        String query = "Select * from child where parent_id = " + id;
        ArrayList<Children> children = new ArrayList<>();
        try {
            Cursor cursor = database.rawQuery(query, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Children c = new Children();
                c.setId(cursor.getInt(0));
                c.setFirstName(cursor.getString(2));
                c.setLastName(cursor.getString(3));
                cursor.moveToNext();
                children.add(c);
            }
            cursor.close();
        } catch (Exception e) {
            children = new ArrayList<>();
        }
        return children;
    }


}
