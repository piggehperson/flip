package com.piggeh.flipnew.activities;

import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;

import com.piggeh.flipnew.R;
import com.piggeh.flipnew.classes.BottomNavigationViewHelper;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton frameFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set up frame stuff
        setSupportActionBar((Toolbar) findViewById(R.id.frame_toolbar));
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.frame_bottomNav);
        BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);
    }
}
