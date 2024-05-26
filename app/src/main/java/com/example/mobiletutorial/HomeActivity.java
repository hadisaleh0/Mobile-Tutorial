package com.example.mobiletutorial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {


    DataBase dbHelper;
    SQLiteDatabase database;
    TextView wlc;
    CardView VaxInfo,childreninfo;
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

        Toast.makeText(getApplicationContext(),"Welcome "+username,Toast.LENGTH_SHORT).show();

        wlc.setText("Welcome " +username);


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


        VaxInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,VaxActivity.class);
                startActivity(intent);
            }
        });

        childreninfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,ChildListActivity.class);
                intent.putExtra("parentId",parentId);
                startActivity(intent);
            }
        });

    }

    private void initlayouts(){

        wlc = findViewById(R.id.titleHome);
        VaxInfo = findViewById(R.id.cardVaxInfo);
        childreninfo = findViewById(R.id.cardChildrenInfo);
        list = findViewById(R.id.imageSettingsList);
        newchild = findViewById(R.id.imageAddChild);
    }
}