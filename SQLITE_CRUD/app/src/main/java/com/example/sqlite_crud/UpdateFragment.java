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

import java.util.List;

public class UpdateFragment extends Fragment implements View.OnClickListener{

    private EditText user_email,user_name,user_password;
    private Button Update_btn,Next_btn;
    private TextView nameText,passwordText;
    DatabaseHandler mDatabase;
    String total_users = "";
    public UpdateFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.update_fragment,container,false);
        user_email = (EditText)view.findViewById(R.id.email_on_update);
        user_name = (EditText)view.findViewById(R.id.name_on_update);
        user_password = (EditText)view.findViewById(R.id.password_on_update);
        nameText = (TextView)view.findViewById(R.id.nameText);
        passwordText = (TextView)view.findViewById(R.id.passwordText);
        Update_btn = (Button)view.findViewById(R.id.update_data_btn);
        Update_btn.setOnClickListener(this);
        Next_btn = (Button)view.findViewById(R.id.next_btn);
        Next_btn.setOnClickListener(this);
        mDatabase = new DatabaseHandler(getContext());
        mDatabase.getReadableDatabase();

        return view;

    }

    @Override
    public void onClick(View v) {
        if(v==Update_btn){
            if(mDatabase.updateUserData(user_email.getText().toString(),user_name.getText().toString(),user_password.getText().toString())>0){
                Toast.makeText(getContext(),"User Updated...",Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(getContext(),"Something went wrong!\nPlease try again...",Toast.LENGTH_LONG).show();
            }
        }
        else if(v==Next_btn) {
            if(mDatabase.checkUserExist(user_email.getText().toString())==true){
                getUserData();
            }
            else {
                Toast.makeText(getContext(),"User does not exist...",Toast.LENGTH_LONG).show();
            }

        }
    }


    public void getUserData(){
        List<Users> usersList = mDatabase.getSpecificUser(user_email.getText().toString());
        for(Users ur : usersList){
            user_name.setText(ur.getName());
            user_password.setText(ur.getPassword());
            Next_btn.setVisibility(View.GONE);
            user_name.setVisibility(View.VISIBLE);
            user_password.setVisibility(View.VISIBLE);
            Update_btn.setVisibility(View.VISIBLE);
            nameText.setVisibility(View.VISIBLE);
            passwordText.setVisibility(View.VISIBLE);
        }

    }
}
