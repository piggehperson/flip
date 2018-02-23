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
import android.widget.Toast;

import com.piggeh.flipnew.R;
import com.piggeh.flipnew.classes.BottomNavigationViewHelper;
import com.piggeh.flipnew.fragments.ButtonFragment;
import com.piggeh.flipnew.fragments.CoinFragment;
import com.piggeh.flipnew.fragments.DiceFragment;
import com.piggeh.flipnew.fragments.ListFragment;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton frameFab;
    private int appMode;/*0 for dice, 1 for coin, 2 for list, 3 for custom dice*/
    private MenuItem menuAdd;

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
        menuAdd = menu.findItem(R.id.menu_add);
        return true;
    }

    private void onFABClick(){
        ((ButtonFragment) getSupportFragmentManager().findFragmentById(R.id.frame_fragment_container)).onButtonPressed();
    }

    private boolean switchMode(int mode){
        switch (mode){
            default: return false;
            case 0:
                appMode = 0;
                FragmentTransaction diceTransaction = getSupportFragmentManager().beginTransaction();
                diceTransaction.replace(R.id.frame_fragment_container, ((Fragment) new DiceFragment()));
                diceTransaction.commit();
                invalidateOptionsMenu();
                return true;
            case 1:
                appMode = 1;
                FragmentTransaction coinTransaction = getSupportFragmentManager().beginTransaction();
                coinTransaction.replace(R.id.frame_fragment_container, ((Fragment) new CoinFragment()));
                coinTransaction.commit();

                invalidateOptionsMenu();
                return true;
            case 2:
                appMode = 2;
                FragmentTransaction listTransaction = getSupportFragmentManager().beginTransaction();
                listTransaction.replace(R.id.frame_fragment_container, ((Fragment) new ListFragment()));
                listTransaction.commit();
                invalidateOptionsMenu();
                return true;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            default: return false;
            case R.id.menu_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivityForResult(intent, 0);
                return true;
            case R.id.menu_add:
                Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
                return true;
        }
    }

    public boolean onPrepareOptionsMenu(Menu menu)
    {
        if (appMode == 2){
            menu.findItem(R.id.menu_add).setVisible(true);
        } else{
            menu.findItem(R.id.menu_add).setVisible(false);
        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        recreate();
    }
}
