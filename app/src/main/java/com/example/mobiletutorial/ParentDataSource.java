package com.example.mobiletutorial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Calendar;

public class ParentDataSource {

    SQLiteDatabase database;

    DataBase dbHelper;

    public ParentDataSource(Context context){ dbHelper = new DataBase(context);}




    public void open() throws SQLException{
            database = dbHelper.getWritableDatabase();
    }

    public void close() {dbHelper.close();}


    public boolean insertParent(Parent p) {
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

    public boolean updateParentPhoto(Parent p) {
        boolean didSucceed = false;
        try {
            String UserName = p.getUserName();
            ContentValues updatedValues = new ContentValues();
            updatedValues.put("username", p.getUserName());
            updatedValues.put("password", p.getPassword());
            updatedValues.put("email", p.getEmail());
            updatedValues.put("NbOfChildren", p.getNbOfChildren());
            if (p.getParentPhoto() != null) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                p.getParentPhoto().compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] photo = baos.toByteArray();
                updatedValues.put("parentProfile", photo);
            }
            didSucceed = database.update("parent", updatedValues, "username = " + UserName, null) > 0;
        } catch (Exception ignored) {
        }
        return didSucceed;
    }

    public Parent getSpecificParent(int Id) {
        Parent p = new Parent();

        Cursor cursor = database.rawQuery("SELECT * FROM parent WHERE id = ?", new String[] {String.valueOf(Id)});

        //Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            p.setParentId(cursor.getInt(0));
            p.setUserName(cursor.getString(1));
            p.setEmail(cursor.getString(2));
            p.setPassword(cursor.getString(3));
            p.setNbOfChildren(cursor.getInt(5));
            byte[] photo = cursor.getBlob(4);
            if (photo != null) {
                ByteArrayInputStream bais = new ByteArrayInputStream(photo);
                Bitmap contactPhoto = BitmapFactory.decodeStream(bais);
                p.setParentPhoto(contactPhoto);
            }
        }
        cursor.close();
        return p;
    }

//    public int updateParentPhoto(String username, Bitmap photo) {
//        ContentValues values = new ContentValues();
//        values.put("photo", getBytesFromBitmap(photo));
//
//        // Add null check for database
//        if (database != null) {
//            return database.update("parent", values, "username = ?", new String[]{username});
//        } else {
//            Log.e("ParentDataSource", "Database is null");
//            return 0;
//        }
//    }


    public boolean updateParent(Parent p) {
        boolean didSucceed = false;
        try {
            open();
            long rowID = p.getParentId();
            Log.e("hi", String.valueOf(rowID));
            Log.e("hi", String.valueOf(rowID));
            Log.e("hi", String.valueOf(rowID));
            Log.e("hi", String.valueOf(rowID));
            ContentValues updatedValues = new ContentValues();
            updatedValues.put("username", p.getUserName());
            Log.e("hi",p.getUserName());
            updatedValues.put("email", p.getEmail());
            updatedValues.put("password", p.getPassword());
            updatedValues.put("NbOfChildren", p.getNbOfChildren());
            if (p.getParentPhoto() != null) {
                Log.e("photo",p.getParentPhoto().toString());
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                p.getParentPhoto().compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] photo = baos.toByteArray();
                updatedValues.put("parentProfile", photo);
            }
            didSucceed = database.update("parent", updatedValues, "id = " + rowID, null) > 0;
        } catch (Exception ignored) {
        }finally {
            close();
        }
        return didSucceed;
    }

    private byte[] getBytesFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }


}
