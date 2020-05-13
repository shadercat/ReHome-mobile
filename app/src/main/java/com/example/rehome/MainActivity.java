package com.example.rehome;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.rehome.ui.dashboard.DashboardFragment;
import com.example.rehome.ui.home.HomeFragment;
import com.example.rehome.ui.notifications.NotificationsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


public class MainActivity extends AppCompatActivity {

    final DashboardFragment dashboardFragment = new DashboardFragment();
    final HomeFragment homeFragment = new HomeFragment();
    final NotificationsFragment notificationsFragment = new NotificationsFragment();

    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = homeFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fm.beginTransaction().hide(active).show(homeFragment).commit();
                    active = homeFragment;
                    return true;
                case R.id.navigation_dashboard:
                    fm.beginTransaction().hide(active).show(dashboardFragment).commit();
                    active = dashboardFragment;
                    return true;
                case R.id.navigation_notifications:
                    fm.beginTransaction().hide(active).show(notificationsFragment).commit();
                    active = notificationsFragment;
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm.beginTransaction().add(R.id.main_frame, homeFragment, "1").show(homeFragment).commit();
        fm.beginTransaction().add(R.id.main_frame, dashboardFragment, "2").hide(dashboardFragment).commit();
        fm.beginTransaction().add(R.id.main_frame, notificationsFragment, "3").hide(notificationsFragment).commit();

        BottomNavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
    }

}
