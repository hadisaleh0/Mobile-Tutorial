package com.example.mobiletutorial;



import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText edUsername,edPassword;
    Button btn;
    TextView tv;
    Context context;
    DataBase dbHelper;
    SQLiteDatabase db;
    Parent parent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        edUsername = findViewById(R.id.editTextName);
        edPassword = findViewById(R.id.editTextPasword);
        btn = findViewById(R.id.buttonLogin);
        tv = findViewById(R.id.textViewNewUser);

        dbHelper = new DataBase(this);
        db = dbHelper.getReadableDatabase();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                loginParent();

            }
        });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

    }


    private void loginParent() {
        String username = edUsername.getText().toString().trim();
        String password = edPassword.getText().toString().trim();


        Cursor cursor = db.rawQuery("SELECT * FROM parent WHERE username = ? AND password = ?",
                new String[]{username, password});

        if (cursor.moveToFirst()) {
            int parentId = cursor.getInt(0);
            SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("username", username);
            editor.putInt("id",parentId);
            editor.apply();


            // User exists, proceed to home activity or wherever you want
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            finish();
        } else {
            // User does not exist or invalid credentials, show error message
            Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
        }

        cursor.close();
    }


}