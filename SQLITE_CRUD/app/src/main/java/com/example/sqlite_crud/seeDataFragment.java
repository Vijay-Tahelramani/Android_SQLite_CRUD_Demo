package com.example.sqlite_crud;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class seeDataFragment extends Fragment {
    private TextView Data_textView;
    DatabaseHandler mDatabase;
    String total_users = "";

    public seeDataFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.see_data_fragment,container,false);
        Data_textView = (TextView)view.findViewById(R.id.Data_View);
        Data_textView.setMovementMethod(new ScrollingMovementMethod());

        mDatabase = new DatabaseHandler(getContext());
        mDatabase.getReadableDatabase();
        getUserData();

        return view;

    }

    public void getUserData(){
        List<Users> usersList = mDatabase.getAllUsers();
        for(Users ur : usersList){
            String log = "Id: "+ur.getId()+"\nName: "+ur.getName()+"\nEmail: "+ur.getEmail()+"\nPassword: "+ur.getPassword();
            total_users+=log+"\n\n";

        }

        if(total_users.equals("")){
            Data_textView.setText("No records available!\nPlease insert some data....");
        }
        else {
            Data_textView.setText(total_users);
        }
    }
}
