package com.example.mobiletutorial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.text.format.DateFormat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddChildrenActivity extends AppCompatActivity implements CustomDialog.SaveDateListener, GenderDialog.GenderDialogListener{

    Children child;
    TextView birthdayText,TextGender,FirstName,LastName,MotherName,PlaceOfBirth,BloodGroup;
    Button btnGender,btnBirth,btnSave;
    ImageView BackToHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_children);

        SharedPreferences sharedprefernces = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        int parentId  = sharedprefernces.getInt("id",0);

        child = new Children();

        child.setParentId(parentId);

        initLayouts();
        initBirthDialog();
        initGenderDialog();
        initSaveButton();
        BackToHome();
    }




    private void initSaveButton() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean wasSuccessful = false;
                ChildDataSource dataSource = new ChildDataSource(AddChildrenActivity.this);
                String firstname = FirstName.getText().toString().trim();
                String lastname = LastName.getText().toString().trim();
                String placeOfBirth = PlaceOfBirth.getText().toString().trim();
                String bloodGoup = BloodGroup.getText().toString().trim();
                String motherName = MotherName.getText().toString().trim();
                String gender = TextGender.getText().toString().trim();
                try {
                    Log.e("hi",gender);
                    Log.e("hi",child.getDateOfBirth().toString());
                    child.setFirstName(firstname);
                    child.setLastName(lastname);
                    child.setMotherName(motherName);
                    child.setGender(gender);
                    child.setDateOfBirth(child.getDateOfBirth());
                    child.setBloodgroup(bloodGoup);
                    child.setPlaceOfBirth(placeOfBirth);
                    dataSource.open();
                    wasSuccessful = dataSource.insertChild(child);
                    Intent intent = new Intent(AddChildrenActivity.this,ChildListActivity.class);
                    startActivity(intent);
                } catch (Exception ignored) {

                }

            }
        });
    }


    private void initBirthDialog() {
        btnBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                CustomDialog dialog = new CustomDialog();
                dialog.show(fm, "Date Of Birth");
            }
        });
    }

    private void initGenderDialog(){
        btnGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                GenderDialog dialog = new GenderDialog();
                dialog.show(fm,"Gender");
            }
        });
    }


    @Override
    public void didFinishDatePickerDialog(Calendar selectedDate) {
        birthdayText.setText(DateFormat.format("dd/MM/yyyy", selectedDate));
        child.setDateOfBirth(selectedDate);
    }

    @Override
    public void GenderSave(String note) {
        if (TextGender != null) {
            TextGender.setText(note);
        } else {
            Log.e("MainActivity", "noteTextView is null");
        }
    }

    private void BackToHome(){
        BackToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddChildrenActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initLayouts(){
        birthdayText = findViewById(R.id.textViewBirthday);
        btnBirth = findViewById(R.id.buttonSelectBirthDate);
        TextGender = findViewById(R.id.textViewGender);
        FirstName = findViewById(R.id.editTextFirstName);
        LastName = findViewById(R.id.editTextLastName);
        PlaceOfBirth = findViewById(R.id.editTextPlaceOfBirth);
        BloodGroup = findViewById(R.id.editTextBloodGroup);
        MotherName = findViewById(R.id.editTextMotherName);
        btnGender = findViewById(R.id.buttonSelectGender);
        btnSave = findViewById(R.id.buttonSaveChild);
        BackToHome = findViewById(R.id.imageViewBackToHomeFromNewChild);

    }
}