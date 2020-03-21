package com.example.green;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import static java.sql.Types.NULL;

public class HomeActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private String SHARED_PREFS = "shared preference";
    private final String CHANNEL_ID = "personal_notifications";
    private final int NOTIFICATION_ID = 001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("this is the home Activity");
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_store, R.id.navigation_recycle, R.id.navigation_events, R.id.navigation_user)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        sharedPreferences.edit().putString("username", getUsername());
        sharedPreferences.edit().putString("password", getPassword());
        sharedPreferences.edit().putString("balance", getBalance());
    }
    private String getUsername() {
        Intent intent = getIntent();
        return intent.getStringExtra("username");
    }
    private String getPassword() {
        Intent intent = getIntent();
        return intent.getStringExtra("password");
    }
    private String getBalance() {
        Intent intent = getIntent();
        return intent.getStringExtra("balance");
    }
}