package com.example.mobiletutorial;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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


}
