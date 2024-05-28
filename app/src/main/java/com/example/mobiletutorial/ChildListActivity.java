package com.example.mobiletutorial;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.ArrayList;

public class ChildListActivity extends AppCompatActivity {

    ImageButton  newChildImageButton,BackToHome;
    RecyclerView childrenRecyclerView;
    SwitchMaterial deleteSwitch;
    ParentAdapter adapter;
    ArrayList<Children> childData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_list);
        initLayoutComponents();
        initRecyclerView();
        initDeleteSwitch();
        initNewContactImageButton();
        BackToHome();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateRecyclerView();
    }

    private void updateRecyclerView() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        int parentId = sharedPreferences.getInt("id", 0);
        ChildDataSource dataSource = new ChildDataSource(this);
        try {
            dataSource.open();
            childData = dataSource.getAllChildren(parentId);
            dataSource.close();
            if (childData != null && !childData.isEmpty()) {
                adapter = new ParentAdapter(childData, this);
                childrenRecyclerView.setAdapter(adapter);
            } else {
                Intent intent = new Intent(ChildListActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error Retrieving Data", Toast.LENGTH_LONG).show();
        }
    }

    private void initNewContactImageButton() {
        newChildImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChildListActivity.this,
                        AddChildrenActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initDeleteSwitch() {
        deleteSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                adapter.setDeleting(isChecked);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void initRecyclerView() {
        childrenRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ParentAdapter(new ArrayList<>(), this);
        childrenRecyclerView.setAdapter(adapter);
        updateRecyclerView();
    }

    private void BackToHome(){
        BackToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChildListActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });
    }



    private void initLayoutComponents() {
        childrenRecyclerView = findViewById(R.id.rvChild);
        deleteSwitch = findViewById(R.id.switchDelete);
        newChildImageButton = findViewById(R.id.imageButtonNewChild);
        BackToHome = findViewById(R.id.imageButtonBackToHome);

    }
}