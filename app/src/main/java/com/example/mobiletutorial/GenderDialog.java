package com.example.mobiletutorial;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.DialogFragment;

public class GenderDialog extends DialogFragment {



    Button select,cancel;

    RadioGroup radio;
    RadioButton male,female;

    private GenderDialog.GenderDialogListener listener;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (GenderDialog.GenderDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement PrefixDialogListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        final View view = inflater.inflate(R.layout.prefix_dialog,container);
        cancel = view.findViewById(R.id.buttonCancelPre);
        select = view.findViewById(R.id.buttonSelectPre);
        iniLayout(view);
        initLayouts(view);

        return view;
    }

    private void initLayouts(View view) {
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gender;
                if(male.isChecked()){
                    gender = "Male";
                } else if (female.isChecked()) {
                    gender = "Female";
                } else{
                    gender = "male";
                }
                listener.GenderSave(gender);
//                Children ct = new Children();
//                ct.setGender(gender);
                 dismiss();
            }
        });

    }

    private void iniLayout(View view){
        male = view.findViewById(R.id.radioMale);
        female = view.findViewById(R.id.radioFemale);

    }

    public interface GenderDialogListener {
        void GenderSave(String note);
    }
}