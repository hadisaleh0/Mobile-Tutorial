package com.example.mobiletutorial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DataBase extends SQLiteOpenHelper {


    private static final String DATABASE_NAME ="Vaccination.db";
    private static final int DATABASE_VERSION = 4;

    // Parent Table
    private static final String SQL_CREATE_PARENT_TABLE =
            "CREATE TABLE IF NOT EXISTS parent (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "username TEXT," +
                    "email TEXT," +
                    "password TEXT," +
                    "parentProfile blob," +
                    "NbOfChildren INTEGER)";

    // Children Table
    private static final String SQL_CREATE_CHILDREN_TABLE =
            "CREATE TABLE IF NOT EXISTS child (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "parent_id INTEGER," +
                    "firstname TEXT," +
                    "lastname TEXT," +
                    "mothername TEXT," +
                    "DateOfBirth TEXT," +
                    "gender TEXT," +
                    "bloodGroup TEXT," +
                    "PlaceOfBirth TEXT," +
                    "CompletedVaccines TEXT)";

    public DataBase(Context context){
            super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_PARENT_TABLE);
        db.execSQL(SQL_CREATE_CHILDREN_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
