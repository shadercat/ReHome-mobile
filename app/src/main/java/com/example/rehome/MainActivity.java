package com.example.rehome;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.rehome.ui.actions.ActionsFragment;
import com.example.rehome.ui.devices.DevicesFragment;
import com.example.rehome.ui.groups.GroupsFragment;
import com.example.rehome.ui.user.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    final GroupsFragment groupsFragment = new GroupsFragment();
    final DevicesFragment devicesFragment = new DevicesFragment();
    final ActionsFragment actionsFragment = new ActionsFragment();
    final UserFragment userFragment = new UserFragment();

    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = devicesFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_devices:
                    fm.beginTransaction().hide(active).show(devicesFragment).commit();
                    active = devicesFragment;
                    return true;
                case R.id.navigation_groups:
                    fm.beginTransaction().hide(active).show(groupsFragment).commit();
                    active = groupsFragment;
                    return true;
                case R.id.navigation_actions:
                    fm.beginTransaction().hide(active).show(actionsFragment).commit();
                    active = actionsFragment;
                    return true;
                case R.id.navigation_user:
                    fm.beginTransaction().hide(active).show(userFragment).commit();
                    active = userFragment;
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            fm.beginTransaction().add(R.id.main_frame, devicesFragment, "1").show(devicesFragment).commit();
            fm.beginTransaction().add(R.id.main_frame, groupsFragment, "2").hide(groupsFragment).commit();
            fm.beginTransaction().add(R.id.main_frame, actionsFragment, "3").hide(actionsFragment).commit();
            fm.beginTransaction().add(R.id.main_frame, userFragment, "4").hide(userFragment).commit();
        }

        BottomNavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
    }

}
