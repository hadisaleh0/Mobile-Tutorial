package com.example.mobiletutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText edUsername,edEmail,edPassword,edConfirm;
    Button btn;
    TextView ExsitingUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edUsername = findViewById(R.id.editTextRegName);
        edEmail = findViewById(R.id.editTextRegEmail);
        edPassword = findViewById(R.id.editTextRegPasword);
        edConfirm = findViewById(R.id.editTextRegConfirmPasword);
        btn = findViewById(R.id.buttonRegister);
        ExsitingUser = findViewById(R.id.textViewExistingUser);


        ExsitingUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edUsername.getText().toString();
                String email = edEmail.getText().toString();
                String password = edPassword.getText().toString();
                String confirm = edConfirm.getText().toString();
                DataBase db = new DataBase(getApplicationContext(),"HealthCare",null,1);

                if(username.length()==0 || email.length()==0 || password.length()==0 || confirm.length()==0){
                    Toast.makeText(getApplicationContext(),"Please Fill All Details",Toast.LENGTH_SHORT).show();
                }
                else {
                    if (password.compareTo(confirm) == 0) {
                        if (isvalid(password)) {
                            db.register(username,email,password);
                            Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Password must contain ate least 8 characters,having letter,digit,and special symbol", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Make Sure To Confirm Passwords",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
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