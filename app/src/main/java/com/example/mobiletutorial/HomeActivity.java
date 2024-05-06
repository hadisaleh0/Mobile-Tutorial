package com.example.mobiletutorial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    TextView wlc;
    CardView exit;
    ImageView list,newchild;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initlayouts();

        SharedPreferences sharedprefernces = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedprefernces.getString("username","").toString();
        Toast.makeText(getApplicationContext(),"Welcome "+username,Toast.LENGTH_SHORT).show();

        wlc.setText("Welcome " +username);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedprefernces.edit();
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
                Intent intent = new Intent(HomeActivity.this,AddChildActivity.class);
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