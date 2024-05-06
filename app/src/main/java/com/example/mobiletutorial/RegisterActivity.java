package com.example.mobiletutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText edUsername,edEmail,edPassword,edConfirm,nbOfChildren;

    Button btn;
    TextView ExistingUser;

    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edUsername = findViewById(R.id.editTextRegName);
        edEmail = findViewById(R.id.editTextRegEmail);
        edPassword = findViewById(R.id.editTextRegPasword);
        edConfirm = findViewById(R.id.editTextRegConfirmPasword);
        nbOfChildren = findViewById(R.id.editTextNumberOfChildren);
        btn = findViewById(R.id.buttonRegister);
        ExistingUser = findViewById(R.id.textViewExistingUser);

        DataBase dbHelper = new DataBase(this);
        db = dbHelper.getWritableDatabase();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
        ExistingUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });


    }

    private void registerUser() {
        String username = edUsername.getText().toString().trim();
        String email = edEmail.getText().toString().trim();
        String password = edPassword.getText().toString().trim();
        String confpassword = edConfirm.getText().toString().trim();
        String NbOfChildren = nbOfChildren.getText().toString().trim();

        // Check if username or email is empty
        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confpassword.isEmpty() || NbOfChildren.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if the username already exists in the database
        Cursor cursor = db.rawQuery("SELECT * FROM parent WHERE username = ?", new String[]{username});
        if (cursor.moveToFirst()) {
            Toast.makeText(this, "Parent already exists", Toast.LENGTH_SHORT).show();
            cursor.close();
            return;
        }
        cursor.close();

        if(!isvalid(password)){
            Toast.makeText(this, "The Password is not Valid", Toast.LENGTH_SHORT).show();
            edPassword.setText("");
            edConfirm.setText("");
            return;
        }

        // Insert the new user into the database
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("email", email);
        values.put("password", password);
        values.put("NbOfChildren", NbOfChildren);

        long newRowId = db.insert("parent", null, values);
        if (newRowId != -1) {
            Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show();
        }
        Log.d("Registration", "Username: " + username);
        Log.d("Registration", "Email: " + email);
        Log.d("Registration", "Password: " + password);
        Log.d("Registration", "New row ID: " + newRowId);
    }

    public static boolean isvalid(String passwordhere){
        int f1=0,f2=0,f3=0;

        if(passwordhere.length()<8){
            return false;
        }
        else{
            for (int i=0; i<passwordhere.length();i++){
                if(Character.isLetter(passwordhere.charAt(i))){
                    f1=1;
                }
            }
            for(int j=0; j<passwordhere.length();j++){
                if(Character.isDigit(passwordhere.charAt(j))){
                    f2=1;
                }
            }
            for(int s=0;s<passwordhere.length();s++){
                char c=passwordhere.charAt(s);
                if(c>=33 &&c<=46 || c==64){
                    f3=1;
                }
            }
            if(f1==1 && f2==1 && f3==1){
                return true;
            }
        }
        return false;
    }


}