package com.example.skbnetwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ConfigurationMenu extends AppCompatActivity {

    TextView employeeAccess, LoginDemoScreen, OpenForNow, testing02, AppMonitoring, Quit;
    ImageView employeeAccessI, LoginDemoScreenI, OpenForNowI, testing02I, AppMonitoringI, QuitI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration_menu);
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"white\">" + getString(R.string.app_name_conf) + "</font>"));


        //Set the orientation to Portrait for this screen
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        employeeAccess = findViewById(R.id.textView130);
        LoginDemoScreen = findViewById(R.id.textView131);
        OpenForNow = findViewById(R.id.textView132);
        testing02 = findViewById(R.id.textView1322);
        AppMonitoring = findViewById(R.id.textView87);
        Quit = findViewById(R.id.textView6);

        employeeAccessI = findViewById(R.id.imageView28);
        LoginDemoScreenI = findViewById(R.id.imageView29);
        OpenForNowI = findViewById(R.id.imageView30);
        testing02I = findViewById(R.id.imageView302);
        AppMonitoringI = findViewById(R.id.imageView31);
        QuitI = findViewById(R.id.textView133);


        employeeAccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ConfigurationMenu.this, EntitlementDb.class);  // Build the access for User
                startActivity(i);

            }
        });

        employeeAccessI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ConfigurationMenu.this, EntitlementDb.class);  // Build the access for User
                startActivity(i);

            }
        });

        LoginDemoScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ConfigurationMenu.this, LoginScreenOne.class);  // Login screen for OTP
                //Intent i = new Intent(SKBMainMenu.this, EntitlementDb.class); // TO add access record
                // Intent i = new Intent(SKBMainMenu.this, ApplicationTestingProgram.class); // Test program
                startActivity(i); //NotDeliveredOn18thSep2022

            }
        });


        LoginDemoScreenI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ConfigurationMenu.this, LoginScreenOne.class);  // Login screen for OTP
                //Intent i = new Intent(SKBMainMenu.this, EntitlementDb.class); // TO add access record
                // Intent i = new Intent(SKBMainMenu.this, ApplicationTestingProgram.class); // Test program
                startActivity(i); //NotDeliveredOn18thSep2022

            }
        });

        OpenForNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ConfigurationMenu.this, ApplicationTestingProgram.class); // Test program
                startActivity(i); //NotDeliveredOn18thSep2022

            }
        });

        OpenForNowI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ConfigurationMenu.this, ApplicationTestingProgram.class); // Test program
                startActivity(i); //NotDeliveredOn18thSep2022

            }
        });

        testing02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ConfigurationMenu.this, LogInUsingPhoneNumber.class); // Test program
                startActivity(i); //NotDeliveredOn18thSep2022
            }
        });

        testing02I.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ConfigurationMenu.this, ApplicationTestingProgram02.class); // Test program
                //NotDeliveredOn18thSep2022 startActivity(i);  //NotDeliveredOn18thSep2022
            }
        });


        AppMonitoring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ConfigurationMenu.this, ApplicationManitoringLog.class);
                startActivity(i); //NotDeliveredOn18thSep2022
            }
        });

        AppMonitoringI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ConfigurationMenu.this, ApplicationManitoringLog.class);
                startActivity(i); //NotDeliveredOn18thSep2022
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

        QuitI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // finish();
                finishAffinity();
                System.exit(0);
            }
        });

    }
}