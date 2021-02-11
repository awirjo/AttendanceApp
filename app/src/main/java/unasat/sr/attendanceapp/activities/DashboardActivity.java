package unasat.sr.attendanceapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import unasat.sr.attendanceapp.R;
import unasat.sr.attendanceapp.fragments.FragmentMain;
import unasat.sr.attendanceapp.fragments.FragmentSecond;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Digital Attendance");

        drawerLayout =(DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);

        // default fragment loaded here
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, new FragmentMain());
        fragmentTransaction.commit();
    }
    //avoid current navigation drawer close when pressing the "back" option
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) { // boolean is created when implementing "NavigationView.OnNavigationItemSelectedListener"
        switch (item.getItemId()) {
            case R.id.home: //when pressed load to main fragment activity
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new FragmentMain());
                fragmentTransaction.commit();
                break;
            case R.id.addStudent:
                Intent intent = new Intent(DashboardActivity.this, AddStudentActivity.class);
                startActivity(intent);
                break;
            case R.id.viewStudent:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new FragmentSecond()); //replace fragment because its already added
                fragmentTransaction.commit();
                break;
            case R.id.attendance:
                Intent intent2 = new Intent(DashboardActivity.this, AttendanceActivity.class);
                startActivity(intent2);
                break;
            case R.id.share:
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("image/jpeg");
                share.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///sdcard/path-to-your-image.jpg"));
                startActivity(Intent.createChooser(share, "Share Image"));
                break;
            case R.id.logout:
        }
        drawerLayout.closeDrawer(GravityCompat.START);// when selecting an item, closes the drawer
        return true;
    }
}