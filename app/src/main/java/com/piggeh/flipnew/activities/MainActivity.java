package com.piggeh.flipnew.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
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
import android.view.MenuItem;
import android.view.View;

import com.piggeh.flipnew.R;
import com.piggeh.flipnew.classes.BottomNavigationViewHelper;
import com.piggeh.flipnew.fragments.ButtonFragment;
import com.piggeh.flipnew.fragments.CoinFragment;
import com.piggeh.flipnew.fragments.DiceFragment;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton frameFab;
    private int appMode;/*0 for dice, 1 for coin, 2 for list, 3 for custom dice*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        switch (sharedPrefs.getString("theme", "system")){
            default:
                AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                break;
            case "dark":
                AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_YES);
                break;
            case "light":
                AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_NO);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set up frame stuff
        setSupportActionBar((Toolbar) findViewById(R.id.frame_toolbar));
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.frame_bottomNav);
        BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    default: return false;
                    case R.id.menu_nav_dice:
                        return switchMode(0);
                    case R.id.menu_nav_coin:
                        return switchMode(1);
                    case R.id.menu_nav_list:
                        return switchMode(2);
                    case R.id.menu_nav_custom_dice:
                        return switchMode(3);
                }
            }
        });
        frameFab = findViewById(R.id.frame_floating_action_button);
        frameFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onFABClick();
            }
        });

        if (getSupportFragmentManager().findFragmentById(R.id.frame_fragment_container) == null){
            //set fragment into frame
            switchMode(0);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("appMode", appMode);

    }

    @Override
    public void onRestoreInstanceState(Bundle outState) {
        super.onRestoreInstanceState(outState);
        outState.getInt("appMode", 0);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_activity, menu);
        return true;
    }

    private void onFABClick(){
        ((ButtonFragment) getSupportFragmentManager().findFragmentById(R.id.frame_fragment_container)).onButtonPressed();
    }

    private boolean switchMode(int mode){
        switch (mode){
            default: return false;
            case 0:
                FragmentTransaction diceTransaction = getSupportFragmentManager().beginTransaction();
                diceTransaction.replace(R.id.frame_fragment_container, ((Fragment) new DiceFragment()));
                diceTransaction.commit();
                appMode = 0;
                return true;
            case 1:
                FragmentTransaction coinTransaction = getSupportFragmentManager().beginTransaction();
                coinTransaction.replace(R.id.frame_fragment_container, ((Fragment) new CoinFragment()));
                coinTransaction.commit();
                appMode = 1;
                return true;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            default: return false;
            case R.id.menu_settings:

                return true;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        recreate();
    }
}
