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

public class DeleteFragment extends Fragment implements View.OnClickListener{

    private EditText delete_email;
    private Button delete_btn;
    DatabaseHandler mDatabase;
    public DeleteFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.delete_fragment,container,false);
        delete_email = (EditText)view.findViewById(R.id.email_on_delete);
        delete_btn = (Button) view.findViewById(R.id.delete_data_btn);
        delete_btn.setOnClickListener(this);

        mDatabase = new DatabaseHandler(getContext());
        mDatabase.getReadableDatabase();

        return view;

    }

    @Override
    public void onClick(View v) {
        if(v== delete_btn){
            if(mDatabase.deleteUser(delete_email.getText().toString())!=1){
                Toast.makeText(getContext(),"User does not exist...",Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(getContext(), "User Deleted...", Toast.LENGTH_LONG).show();
            }
        }
    }
}
