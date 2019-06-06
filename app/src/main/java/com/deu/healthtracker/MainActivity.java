package com.deu.healthtracker;

import com.deu.healthtracker.core.Person;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;

import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private int RC_SIGN_IN;//it related firebase
    private boolean permissionGranted=false;
    final Fragment bmiFrag = new BmiFragment();
    final FragmentManager fm = getSupportFragmentManager();
    public static Person user = new Person();

    //buttom menu
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    setTitle("Health Tracker");
                    return loadFragment(new HomeFragment());
                case R.id.navigation_bmi:
                    setTitle("Body mass index");
                    return loadFragment(new BmiFragment());
                case R.id.navigation_eer:
                    setTitle("Estimated Energy Requirement");
                    return loadFragment(new EerFragment());
                case R.id.navigation_pht:
                    setTitle("Personal Health Tracking");
                    return loadFragment(new PhtFragment());
            }
            return false;
        }
    };
    @Override
    public void onStart() {
        super.onStart();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        Permissions.check(this/*context*/, permissions, null/*rationale*/, null/*options*/, new PermissionHandler() {
            @Override
            public void onGranted() {
                permissionGranted=true;

            }
            @Override
            public void onDenied(Context context, ArrayList<String> deniedPermissions) {
                // permission denied, block the feature.
                new AlertDialog.Builder(getApplicationContext())
                        .setTitle("Need Permission")
                        .setMessage("The Application need permissions for running. Give it?")
                        .setPositiveButton("Yes", dialogPermListener)
                        .setNegativeButton("No", dialogPermListener)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
        if(permissionGranted){
            loadFragment(new HomeFragment());
            user.pht.setAct(this);
            user.setWeight(80); user.setHeight(170); user.setAge(24);

            BottomNavigationView navView = findViewById(R.id.nav_view);
            navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        }


    }
    DialogInterface.OnClickListener dialogPermListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    //Yes button clicked
                    refresh();
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    //No button clicked
                    System.exit(0);
                    break;
            }
        }
    };
    //header menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.clearData:
                new AlertDialog.Builder(this)
                        .setTitle("Delete all data")
                        .setMessage("Are you sure?")
                        .setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

                break;
        }
        return true;
    }
    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    //Yes button clicked
                    user.pht.clearAll();
                    refresh();
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    //No button clicked
                    break;
            }
        }
    };
    public void refresh() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }
    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}
