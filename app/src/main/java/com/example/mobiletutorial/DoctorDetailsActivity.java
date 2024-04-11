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

public class DoctorDetailsActivity extends AppCompatActivity {

    private String[][] docotr_details1 =
            {
                    {"Doctor Name : Ajit Saste", "Hospital Address : Pimpri", "Exp : 5 years", "Mobile No:343434343","600"},
                    {"Doctor Name : Prasad Pawar", "Hospital Address : Nigdi", "Exp : 15 years", "Mobile No:343434343","600"},
                    {"Doctor Name : Swapnil kale ", "Hospital Address : Pune", "Exp : 4 years", "Mobile No:343434343","600"},
                    {"Doctor Name : Deepak Deshmuk", "Hospital Address : Chinchwad", "Exp : 9 years", "Mobile No:343434343","600"},
                    {"Doctor Name : Ashok Panda", "Hospital Address : Katraj", "Exp : 6 years", "Mobile No:343434343","600"}
            };
    private String[][] docotr_details2 =
            {
                    {"Doctor Name : bb Saste", "Hospital Address : Pimpri", "Exp : 5 years", "Mobile No:343434343","600"},
                    {"Doctor Name : bb Pawar", "Hospital Address : Nigdi", "Exp : 15 years", "Mobile No:343434343","600"},
                    {"Doctor Name : bb kale ", "Hospital Address : Pune", "Exp : 4 years", "Mobile No:343434343","600"},
                    {"Doctor Name : bb Deshmuk", "Hospital Address : Chinchwad", "Exp : 9 years", "Mobile No:343434343","600"},
                    {"Doctor Name : bb Panda", "Hospital Address : Katraj", "Exp : 6 years", "Mobile No:343434343","600"}
            };
    private String[][] docotr_details3 =
            {
                    {"Doctor Name : ss Saste", "Hospital Address : Pimpri", "Exp : 5 years", "Mobile No:343434343","600"},
                    {"Doctor Name : dd Pawar", "Hospital Address : Nigdi", "Exp : 15 years", "Mobile No:343434343","600"},
                    {"Doctor Name : ss kale ", "Hospital Address : Pune", "Exp : 4 years", "Mobile No:343434343","600"},
                    {"Doctor Name : vv Deshmuk", "Hospital Address : Chinchwad", "Exp : 9 years", "Mobile No:343434343","600"},
                    {"Doctor Name : aa Panda", "Hospital Address : Katraj", "Exp : 6 years", "Mobile No:343434343","600"}
            };
    private String[][] docotr_details4 =
            {
                    {"Doctor Name : hsen Saste", "Hospital Address : Pimpri", "Exp : 5 years", "Mobile No:343434343","600"},
                    {"Doctor Name : mhmd Pawar", "Hospital Address : Nigdi", "Exp : 15 years", "Mobile No:343434343","600"},
                    {"Doctor Name : moosa kale ", "Hospital Address : Pune", "Exp : 4 years", "Mobile No:343434343","600"},
                    {"Doctor Name : mahdi Deshmuk", "Hospital Address : Chinchwad", "Exp : 9 years", "Mobile No:343434343","600"},
                    {"Doctor Name : hsn Panda", "Hospital Address : Katraj", "Exp : 6 years", "Mobile No:343434343","600"}
            };
    private String[][] docotr_details5 =
            {
                    {"Doctor Name : jaafar saleh", "Hospital Address : Pimpri", "Exp : 5 years", "Mobile No:343434343","600"},
                    {"Doctor Name : hasan Pawar", "Hospital Address : Nigdi", "Exp : 15 years", "Mobile No:343434343","600"},
                    {"Doctor Name : hadi kale ", "Hospital Address : Pune", "Exp : 4 years", "Mobile No:343434343","600"},
                    {"Doctor Name : ali Deshmuk", "Hospital Address : Chinchwad", "Exp : 9 years", "Mobile No:343434343","600"},
                    {"Doctor Name : abbas Panda", "Hospital Address : Katraj", "Exp : 6 years", "Mobile No:343434343","600"}
            };

    String [][] doctor_detals ={};
    HashMap<String,String> item;
    TextView tv;
    Button btn;
    ArrayList list;
    SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        tv=findViewById(R.id.textViewDDTitle);
        btn=findViewById(R.id.buttonDDBack);

        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        if(title.compareTo("Family Physicians")==0)
            doctor_detals = docotr_details1;
        else if(title.compareTo("Dietician")==0)
            doctor_detals = docotr_details2;
        else if(title.compareTo("Dentist")==0)
            doctor_detals = docotr_details3;
        else if(title.compareTo("Surgeon")==0)
            doctor_detals = docotr_details4;
        else if(title.compareTo("Cardiologists")==0)
            doctor_detals = docotr_details5;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorDetailsActivity.this,FindDoctorActivity.class));
            }
        });

        list= new ArrayList<>();

        for (int i =0;i<doctor_detals.length;i++){
            item = new HashMap<String,String>();
            item.put("line1", doctor_detals[i][0]);
            item.put("line2", doctor_detals[i][1]);
            item.put("line3", doctor_detals[i][2]);
            item.put("line4", doctor_detals[i][3]);
            item.put("line5", "Cons Fees:"+doctor_detals[i][4]+"/$");
            list.add(item);
        }

        sa = new SimpleAdapter(this,list,R.layout.multi_lines,new String[]{"line1","line2","line3","lime4","line5"},new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});

        ListView lst =findViewById(R.id.listViewDD);
        lst.setAdapter(sa);
    }
}