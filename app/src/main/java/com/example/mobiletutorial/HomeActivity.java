package com.example.mobiletutorial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {


    DataBase dbHelper;
    SQLiteDatabase database;

    TextView wlc;
    CardView exit;
    ImageView list,newchild;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initlayouts();

        dbHelper = DataBase.getInstance(this);
        dbHelper.openDatabase();
        database = dbHelper.getDatabase();

        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String username = sharedPreferences.getString("username","").toString();
        int parentId = sharedPreferences.getInt("id",0);
//        @SuppressLint("Recycle") Cursor cursor = database.rawQuery("SELECT * FROM parent WHERE username = ?", new String[] {username});
//        int Id = cursor.getInt(0);
//        int Id=0;
//        if (database != null) {
//            @SuppressLint("Recycle") Cursor cursor = database.rawQuery("SELECT * FROM parent WHERE username = ?", new String[] {username});
//            if (cursor != null) {
//                if (cursor.moveToFirst()) {
//                    do {
//                         Id = cursor.getInt(0);
//                    } while (cursor.moveToNext());
//                }
//            }
//            cursor.close();
//        }
//        editor.putInt("id",Id);
        Toast.makeText(getApplicationContext(),"Welcome "+username,Toast.LENGTH_SHORT).show();

        wlc.setText("Welcome " +username);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                startActivity(new Intent(HomeActivity.this,LoginActivity.class));
            }
        });

        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,ListActivity.class);
                startActivity(intent);
            }
        });

        newchild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,AddChildrenActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initlayouts(){

        wlc = findViewById(R.id.titleHome);
        exit = findViewById(R.id.cardExit);
        list = findViewById(R.id.imageSettingsList);
        newchild = findViewById(R.id.imageAddChild);
    }
}