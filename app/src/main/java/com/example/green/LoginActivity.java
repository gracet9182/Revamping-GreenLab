package com.example.green;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText emailEditText;
    private EditText passwordEditText;
    private FirebaseDatabase database;
    private final String CHANNEL_ID = "personal_notifications";
    private final int NOTIFICATION_ID = 001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = FirebaseDatabase.getInstance("https://hackathon-ffdbf.firebaseio.com/");
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);

        // Authentication commencement.
        mAuth = FirebaseAuth.getInstance();

        //This is the the login
        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.login();
            }
        });

        // This is setting up the firebaseDatabase and it inputs message as the key and Hello world
        // as the value.
        findViewById(R.id.sign_up).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.createNotification();
                LoginActivity.this.register();
            }
        });

    }

    private void register(){
        mAuth.createUserWithEmailAndPassword(emailEditText.getText().toString(), passwordEditText.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Intent newIntent = new Intent(LoginActivity.this, HomeActivity.class);
                            newIntent.putExtra("username", emailEditText.getText().toString());
                            newIntent.putExtra("password", passwordEditText.getText().toString());
                            newIntent.putExtra("balance", "0");
                            startActivity(newIntent);
                            // getUid will never be null since it will only enter if it has registered a new user
                            DatabaseReference myRef = database.getReference(mAuth.getCurrentUser().getUid());
                            HashMap<String, Object> myMap = new HashMap<String, Object>();
                            myMap.put("Balance","0");
                            myRef.updateChildren(myMap);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    private void login(){
                mAuth.signInWithEmailAndPassword(emailEditText.getText().toString(), passwordEditText.getText().toString())
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            // Read from the database
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference myRef = database.getReference(mAuth.getCurrentUser().getUid()).child("Balance");

                            myRef.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    // This method is called once with the initial value and again
                                    // whenever data at this location is updated.
                                    Intent newIntent = new Intent(LoginActivity.this, HomeActivity.class);
                                    newIntent.putExtra("username", emailEditText.getText().toString());
                                    newIntent.putExtra("password", passwordEditText.getText().toString());
                                    newIntent.putExtra("balance", dataSnapshot.getValue(String.class));
                                    startActivity(newIntent);
                                }

                                @Override
                                public void onCancelled(DatabaseError error) {
                                    // Failed to read value
                                }
                            });
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginActivity.this, task.getException().getMessage(),
                                    Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }
    private void createNotification(){
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "tutorialspoint_01";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            @SuppressLint("WrongConstant") NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_MAX);
            // Configure the notification channel.
            notificationChannel.setDescription("Sample Channel description");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
        notificationBuilder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                //.setPriority(Notification.PRIORITY_MAX)
                .setContentTitle("Welcome!")
                .setContentText("Let's remind ourselves to be environmentally friendly.")
                .setContentInfo("Information");
        notificationManager.notify(1, notificationBuilder.build());
        System.out.println("HEREEEEEEEEE2");
    }
}
