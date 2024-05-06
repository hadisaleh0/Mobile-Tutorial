package com.example.mobiletutorial;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.io.ByteArrayOutputStream;

public class ListActivity extends AppCompatActivity {

    Parent currentParent;
    final int PERMISSION_REQUEST_CAMERA = 103;
    final int PERMISSION_REQUEST_PHONE = 102;
    ImageView parentProfile,backButton;
    Button logOut;

    SQLiteDatabase database;
    DataBase dbHelper;
    ParentDataSource dataSource;


    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }


    ActivityResultLauncher<Intent> activityResultLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if (result.getResultCode() == RESULT_OK) {
                                Intent data = result.getData();
                                Bitmap photo = (Bitmap) data.getExtras().get("data");
                                float density = ListActivity.this.getResources().getDisplayMetrics().density;
                                int dp = 140;
                                int pixels = (int) ((dp * density) + 0.7);
                                Bitmap scaledPhoto = Bitmap.createScaledBitmap(
                                        photo, pixels, pixels, true);
                                parentProfile.setImageBitmap(scaledPhoto);
//                                SharedPreferences sharedprefernces = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
//                                String userName = sharedprefernces.getString("username","").toString();
//                                 dataSource.updateParentPhoto(currentParent);

//                                boolean didSucceed = false;
//                                String username = currentParent.getUserName();
//                                currentParent.setParentPhoto(scaledPhoto);
//                                ContentValues initialValues = new ContentValues();
//                                if (currentParent.getParentPhoto() != null) {
//                                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                                    currentParent.getParentPhoto().compress(Bitmap.CompressFormat.PNG, 100, baos);
//                                    byte[] ProfilePhoto = baos.toByteArray();
//                                    initialValues.put("parentProfile", ProfilePhoto);
//                                }
//                                didSucceed = database.update("parent", initialValues, "_id = " + username, null) > 0;
                            }
                        }
                    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        initLayouts();
        initCameraButton();
        GoBackToHome();
        LogOut();


        SharedPreferences sharedprefernces = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String userName = sharedprefernces.getString("username","").toString();

        ParentDataSource ds = new ParentDataSource(this);
        ds.open();
        currentParent = ds.getSpecificParent(userName);
//        ds.close();
    }


    private void initCameraButton() {
        parentProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(ListActivity.this,
                        android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    takePhoto();
                } else {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(
                            ListActivity.this, android.Manifest.permission.CAMERA)) {
                        Snackbar.make(findViewById(R.id.ListSettings),
                                        "The app needs permission to take photo",
                                        Snackbar.LENGTH_INDEFINITE)
                                .setAction("Ok", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Log.d("MainActivity Camera permission", "");
                                        ActivityCompat.requestPermissions(ListActivity.this,
                                                new String[]{android.Manifest.permission.CAMERA}, PERMISSION_REQUEST_CAMERA);
                                    }
                                }).show();
                    } else {
                        Log.d("MainActivity Camera permission", "");
                        ActivityCompat.requestPermissions(ListActivity.this,
                                new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CAMERA);
                    }
                }
            }
        });
    }

    private void takePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        activityResultLauncher.launch(intent);
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST_PHONE: {
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "You may now make phone calls from this app",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this,
                            "You will not be able to make phone calls from this app",
                            Toast.LENGTH_LONG).show();
                }
                break;
            }
            case PERMISSION_REQUEST_CAMERA:
                if (grantResults.length > 0
                ) {
                    takePhoto();
                } else {
                    Toast.makeText(this,
                            "You will not be able to save contact photo from this app",
                            Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    private void GoBackToHome(){
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(ListActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void LogOut(){

        SharedPreferences sharedprefernces = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedprefernces.edit();
                editor.clear();
                editor.apply();
                startActivity(new Intent(ListActivity.this,LoginActivity.class));
            }
        });
    }

    private void initLayouts(){
        parentProfile = findViewById(R.id.contactProfileImage);
        backButton = findViewById(R.id.imageViewBackToHomeFromSettings);
        logOut = findViewById(R.id.buttonLogOut);
    }
}