package com.example.mobiletutorial;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.DialogFragment;

public class EditProfileDialog extends DialogFragment {
    Button save,cancel;

    EditText changeUsername,changeEmail,changeNbofChildren;

    private EditProfileDialog.ProfileDialogListener listener;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (EditProfileDialog.ProfileDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement PrefixDialogListener");
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        final View view = inflater.inflate(R.layout.edit_profile_dialog,container);
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
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username,email,nbOfChildren;

                username = changeUsername.getText().toString().trim();
                email = changeEmail.getText().toString().trim();
                nbOfChildren = changeNbofChildren.getText().toString().trim();
                listener.ProfileSave(username,email,nbOfChildren);

                dismiss();
            }
        });

    }

    private void iniLayout(View view){
        changeUsername = view.findViewById(R.id.editTextChangeUserName);
        changeEmail = view.findViewById(R.id.editTextChangeEmail);
        changeNbofChildren = view.findViewById(R.id.editTextChangeNumberOfChildren);
        cancel = view.findViewById(R.id.buttonCancelChange);
        save = view.findViewById(R.id.buttonSaveChange);

    }

    public interface ProfileDialogListener {
        void ProfileSave(String user,String email,String nbOfChildren);
    }

}
