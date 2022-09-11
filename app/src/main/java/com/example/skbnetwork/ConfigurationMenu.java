package com.example.skbnetwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ConfigurationMenu extends AppCompatActivity {

    TextView employeeAccess, LoginDemoScreen, OpenForNow, Quit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration_menu);

        //Set the orientation to Portrait for this screen
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        employeeAccess = findViewById(R.id.textView130);
        LoginDemoScreen = findViewById(R.id.textView131);
        OpenForNow = findViewById(R.id.textView132);
        Quit = findViewById(R.id.textView133);


        employeeAccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ConfigurationMenu.this, EntitlementDb.class);  // Build the access for User
                startActivity(i); //NotDeliveredOn1stSep2022

            }
        });

        LoginDemoScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ConfigurationMenu.this, LoginScreenOne.class);  // Login screen for OTP
                //Intent i = new Intent(SKBMainMenu.this, EntitlementDb.class); // TO add access record
                // Intent i = new Intent(SKBMainMenu.this, ApplicationTestingProgram.class); // Test program
                startActivity(i); //NotDeliveredOn1stSep2022

            }
        });

        OpenForNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ConfigurationMenu.this, ApplicationTestingProgram.class); // Test program
                startActivity(i); //NotDeliveredOn1stSep2022

            }
        });


        Quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // finish();
                finishAffinity();
                System.exit(0);
            }
        });

    }
}