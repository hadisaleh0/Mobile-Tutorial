package com.example.mobiletutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class VaxDetailsActivity extends AppCompatActivity {

    private String[] InfluenzaVax_details =
            {
              "Vax Name : Influenza","Age Indication: 2 Years","License Number: PEI.H.11808.01.1"
            };
    private String[] HpvVax_details =
            {
                    "Vax Name : Hpv","Age Indication: 2 month","License Number: PEI.H.11768.01.3"
            };
    private String[] PolioVax_details =
            {
                    "Vax Name : Polio","Age Indication: 1 month","License Number: PEI.H.34553.06.7"
            };
    private String[] MumpsVax_details =
            {
                    "Vax Name : Mumps","Age Indication: 1 Years","License Number: PEI.H.85556.12.1"
            };
    private String[] BCGVax_details =
            {
                    "Vax Name : BCG","Age Indication: 8 month","License Number: PEI.H.34566.11.5"
            };

    TextView tv;
    Button back;

    String[] Vax_details = {};
    ArrayList list;
    HashMap<String, String> item;
    SimpleAdapter sa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vax_details);

        tv = findViewById(R.id.textViewVaxTitle);
        back = findViewById(R.id.btnBack);
        Intent intent = getIntent();
        String title = intent.getStringExtra("type");
        tv.setText(title);

        if(title.compareTo("Influenza")==0){
            Vax_details = InfluenzaVax_details;
        } else if (title.compareTo("Hpv")==0) {
            Vax_details = HpvVax_details;
        } else if (title.compareTo("Polio")==0) {
            Vax_details = PolioVax_details;
        } else if (title.compareTo("Mumps")==0) {
            Vax_details = MumpsVax_details;
        } else if (title.compareTo("BCG")==0) {
            Vax_details = BCGVax_details;
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VaxDetailsActivity.this,VaxActivity.class);
                startActivity(intent);
            }
        });

        list = new ArrayList();
        for(int i=0;i<Vax_details.length;i++){
             item = new HashMap<String, String>();
             item.put("line1",Vax_details[i]);
             list.add(item);
        }
        sa= new SimpleAdapter(this,list,R.layout.multi_lines,new String[]{"line1","line2","line3"},
                                      new int[]{R.id.line_a,R.id.line_b,R.id.line_c});

        ListView lst = findViewById(R.id.listViewVaxDetails);
        lst.setAdapter(sa);

    }



}