package com.example.studentscompanion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    ArrayList<HashMap<String,Object>> items =new ArrayList<HashMap<String,Object>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        toolbar = findViewById(R.id.TOOLBAR_LAYOUT);
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_navigation_drawer);

        final PackageManager pm = getPackageManager();
        List<PackageInfo> packs = pm.getInstalledPackages(0);
        for (PackageInfo pi : packs) {
            if( pi.packageName.toString().toLowerCase().contains("calcul")){
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("appName", pi.applicationInfo.loadLabel(pm));
                map.put("packageName", pi.packageName);
                items.add(map);
            }
        }

        findViewById(R.id.alarm_textview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent openClockIntent = new Intent(AlarmClock.ACTION_SET_ALARM);
                openClockIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(openClockIntent);
            }
        });
        findViewById(R.id.calci_textview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(items.size()>=1){
                    String packageName = (String) items.get(0).get("packageName");
                    Intent i = pm.getLaunchIntentForPackage(packageName);
                    if (i != null)
                        startActivity(i);
                }

            }
        });
        findViewById(R.id.browse_textview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.cet.edu.in/"));
                startActivity(intent);
            }
        });
        findViewById(R.id.fb_textview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/groups/cetinformation/"));
                startActivity(intent);
            }
        });
        findViewById(R.id.email_textview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("plain/text");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Emailing link");
                intent.putExtra(Intent.EXTRA_TEXT, "Link is \n" +
                        "This is the body of hte message");
                startActivity(Intent.createChooser(intent, ""));
            }
        });
        findViewById(R.id.call_textview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:6742386075"));
                startActivity(intent);
            }
        });




        drawerLayout = findViewById(R.id.drawer_dashboard_layout);
        navigationView = findViewById(R.id.navigation_drawer_dashboard);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId())
                {
                    case R.id.Academic_section:
                        menuItem.setChecked(true);
                        drawerLayout.closeDrawers();
                        startActivity(new Intent(DashboardActivity.this,AcademicActivity.class));
                        finish();
                        return true;
                    case R.id.Syllabus:
                        menuItem.setChecked(true);
                        drawerLayout.closeDrawers();
                        startActivity(new Intent(DashboardActivity.this,SyllabusActivity.class));
                        finish();
                        return true;
                    case R.id.todo:
                        menuItem.setChecked(true);
                        drawerLayout.closeDrawers();
                        startActivity(new Intent(DashboardActivity.this,TodolistActivity.class));
                        finish();
                        return true;
                    case R.id.fine:
                        menuItem.setChecked(true);
                        startActivity(new Intent(DashboardActivity.this,fineCalculatorActivity.class));
                        drawerLayout.closeDrawers();
                        finish();
                        return true;
                    case R.id.time_table:
                        menuItem.setChecked(true);
                        startActivity(new Intent(DashboardActivity.this,timetableActivity.class));
                        drawerLayout.closeDrawers();
                        finish();
                        return true;
                    case R.id.logout:
                        menuItem.setChecked(true);
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(DashboardActivity.this,LoginActivity.class));
                        finish();
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
