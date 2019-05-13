package com.example.sqlite_crud;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.bottomnavigation.LabelVisibilityMode;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomBar;
    FragmentManager manager;
    ConstraintLayout ActivityLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityLayout = (ConstraintLayout)findViewById(R.id.root_Layout);

        bottomBar = (BottomNavigationView)findViewById(R.id.Bottom_bar);
        bottomBar.setOnNavigationItemSelectedListener(navListener);

        KeyboardIsUp();

        manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.fragment_container, new seeDataFragment()).commit();
    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.insert:
                    manager.beginTransaction().replace(R.id.fragment_container, new InsertFragment()).commit();
                    break;
                case R.id.delete:
                    manager.beginTransaction().replace(R.id.fragment_container, new DeleteFragment()).commit();
                    break;
                case R.id.update:
                    manager.beginTransaction().replace(R.id.fragment_container, new UpdateFragment()).commit();
                    break;
                case R.id.see_data:
                    manager.beginTransaction().replace(R.id.fragment_container, new seeDataFragment()).commit();
                    break;

            }
            return true;
        }
    };

    public void KeyboardIsUp(){
        getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                Rect r = new Rect();
                getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
                int screenHeight = getWindow().getDecorView().getRootView().getHeight();

                int keypadHeight = screenHeight - r.bottom;

                if (keypadHeight > screenHeight * 0.15) {
                    //Keyboard is opened
                    bottomBar.setVisibility(View.GONE);
                }
                else {
                    // keyboard is closed
                    bottomBar.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
