package com.example.sqlite_crud;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class InsertFragment extends Fragment implements View.OnClickListener{

    private EditText user_email,user_name,user_password;
    private Button Insert_btn;
    DatabaseHandler mDatabase;

    public InsertFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.insert_fragment,container,false);

        mDatabase = new DatabaseHandler(getContext());
        mDatabase.getReadableDatabase();

        user_name = (EditText)view.findViewById(R.id.UserName);
        user_email = (EditText)view.findViewById(R.id.UserEmail);
        user_password = (EditText)view.findViewById(R.id.UserPassword);
        Insert_btn = (Button)view.findViewById(R.id.insert_btn);
        Insert_btn.setOnClickListener(this);

        return view;

    }

    @Override
    public void onClick(View v) {
        if(v == Insert_btn) {
            if (mDatabase.checkUserExist(user_email.getText().toString()) == true) {
                Toast.makeText(getContext(), "User already exist...", Toast.LENGTH_LONG).show();
            } else {
                if ((mDatabase.addUser(user_name.getText().toString(), user_email.getText().toString(), user_password.getText().toString())) == -1) {
                    Toast.makeText(getContext(), "Something went wrong!\nPlease try again...", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), "Data Insertion Successful...", Toast.LENGTH_LONG).show();
                }
            }
        }

    }
}
