package com.example.skbnetwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SKBMainMenu extends AppCompatActivity {

    TextView CongigurationOption, HeadOfficeMenu, SiteOfficeMenu, StoreMenu, OpenMenu, Quit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skbmain_menu);

        CongigurationOption = findViewById(R.id.textView1);
        HeadOfficeMenu = findViewById(R.id.textView2);
        SiteOfficeMenu = findViewById(R.id.textView3);
        StoreMenu = findViewById(R.id.textView4);
        OpenMenu = findViewById(R.id.textView5);
        Quit = findViewById(R.id.textView6);

        //Set the orientation to Portrait for this screen
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        CongigurationOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // Intent i = new Intent(SKBMainMenu.this, com.example.skbnetwork.HeadOfficeMenu.class);
                Intent i = new Intent(SKBMainMenu.this, LoginScreenOne.class);
                startActivity(i);

            }
        });

        HeadOfficeMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(SKBMainMenu.this, com.example.skbnetwork.HeadOfficeMenu.class);
                startActivity(i);

            }
        });

        SiteOfficeMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SKBMainMenu.this, com.example.skbnetwork.SiteOfficeMenu.class);
                startActivity(i);
            }
        });


        StoreMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SKBMainMenu.this, StoreMenu.class);
                startActivity(i);
            }
        });




        Quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
                System.exit(0);
            }
        });


    }
}