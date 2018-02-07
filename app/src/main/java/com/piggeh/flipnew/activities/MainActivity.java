package com.piggeh.flipnew.activities;

import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.piggeh.flipnew.R;
import com.piggeh.flipnew.classes.BottomNavigationViewHelper;
import com.piggeh.flipnew.fragments.DiceFragment;

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
        frameFab = findViewById(R.id.frame_floating_action_button);
        frameFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onFABClick();
            }
        });

        if (getSupportFragmentManager().findFragmentById(R.id.frame_fragment_container) == null){
            //set fragment into frame
            FragmentTransaction testTransaction = getSupportFragmentManager().beginTransaction();
            testTransaction.replace(R.id.frame_fragment_container, ((Fragment) new DiceFragment()));
            testTransaction.commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_activity, menu);
        return true;
    }

    private void onFABClick(){
        ((DiceFragment) getSupportFragmentManager().findFragmentById(R.id.frame_fragment_container)).onButtonPressed();
    }
}
