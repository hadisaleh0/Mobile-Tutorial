package com.example.mobiletutorial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class VaxActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vax);

        CardView Influenza = findViewById(R.id.cardInfluenza);
        Influenza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VaxActivity.this,VaxDetailsActivity.class);
                intent.putExtra("type","Influenza");
                startActivity(intent);
            }
        });

        CardView Hpv = findViewById(R.id.cardHpvVax);
        Hpv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VaxActivity.this,VaxDetailsActivity.class);
                intent.putExtra("type","Hpv");
                startActivity(intent);
            }
        });

        CardView Polio = findViewById(R.id.cardPolioVax);
        Polio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VaxActivity.this,VaxDetailsActivity.class);
                intent.putExtra("type","Polio");
                startActivity(intent);
            }
        });

        CardView Mumps = findViewById(R.id.cardMumpsVax);
        Mumps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VaxActivity.this,VaxDetailsActivity.class);
                intent.putExtra("type","Mumps");
                startActivity(intent);
            }
        });

        CardView BCG = findViewById(R.id.cardBCGVax);
        BCG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VaxActivity.this,VaxDetailsActivity.class);
                intent.putExtra("type","BCG");
                startActivity(intent);
            }
        });

        CardView back = findViewById(R.id.cardExit);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VaxActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });


    }
}